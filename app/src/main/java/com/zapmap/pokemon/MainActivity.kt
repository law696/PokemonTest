package com.zapmap.pokemon

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.zapmap.pokemon.databinding.ActivityMainBinding
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private val pokemonApiService = Api.getApi()

    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    var pokemonFetcher = PokemonFetcher(pokemonApiService)
    val pokemonAdapter = PokemonAdapter { pokemonItem ->
        onPokemonClicked(pokemonItem) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecyclerView()
        fetchPokemons()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewPokemon.apply {
            adapter = pokemonAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (!recyclerView.canScrollVertically(1)) {
                        fetchPokemons()
                    }
                }
            })
        }
    }

    fun fetchPokemons() {
        lifecycleScope.launchWhenCreated {
            val pokemonList = pokemonFetcher.fetchPokemons()
            pokemonAdapter.updateItems(pokemonList)
        }
    }

    private fun onPokemonClicked(pokemonItem: RemotePokemonItem) {
        val id = getIdFromUrl(pokemonItem.url)
        logPokemonSelectedEvent(id)
        val json = convertPokemonItemToJson(pokemonItem)
        startPokemonDetailActivity(json)
    }

    fun getIdFromUrl(url: String): Int {
        val stringId = url.removeSuffix("/").substringAfterLast("/")
        return stringId.toInt()
    }

    private fun logPokemonSelectedEvent(id: Int) {
        ZoogleAnalytics.logEvent(
            ZoogleAnalyticsEvent("pokemon_selected", mapOf("id" to id.toString()))
        )
    }

    private fun convertPokemonItemToJson(pokemonItem: RemotePokemonItem): String {
        val adapter = PokemonItemJsonAdapter()
        return adapter.toJson(pokemonItem)
    }

    private fun startPokemonDetailActivity(json: String) {
        Intent(this@MainActivity, PokemonDetailActivity::class.java).apply {
            putExtra("json", json)
            startActivity(this@apply)
        }
    }
}