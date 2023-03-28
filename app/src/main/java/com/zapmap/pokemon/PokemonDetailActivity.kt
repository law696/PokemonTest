package com.zapmap.pokemon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.zapmap.pokemon.databinding.ActivityPokemonDetailBinding

class PokemonDetailActivity : AppCompatActivity() {

    private lateinit var remotePokemonItem: RemotePokemonItem
    private lateinit var binding: ActivityPokemonDetailBinding

    private val typesAdapter = TypesAdapter()
    private val pokemonApiService = Api.getApi()
    private var pokemonFetcher = PokemonFetcher(pokemonApiService)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        intent?.getStringExtra("json")?.let { json ->
            val adapter = PokemonItemJsonAdapter()
            remotePokemonItem = adapter.fromJson(json)!!
        }

        lifecycleScope.launchWhenCreated {
             val id = remotePokemonItem.url
                    .removeSuffix("/")
                    .substringAfterLast("/")
                    .toInt()
            val remotePokemon = pokemonFetcher.fetchPokemonById(id)
            displayPokemon(remotePokemon!!)
        }

        binding.recyclerViewTypes.adapter = typesAdapter
        binding.textViewPokemonName.text = remotePokemonItem.name
    }

    private fun displayPokemon(pokemon: RemotePokemon) {
        with(binding) {
            textViewPokemonName.text = pokemon.name.capitalize()
            textViewWeight.text = "${pokemon.weight}kg"
            textViewHeight.text = "${pokemon.height}cm"
            imageViewPokemonFront.load(pokemon.sprites.frontDefault)
            typesAdapter.updateItems(pokemon.types.map { it.type.name })
        }
    }
}