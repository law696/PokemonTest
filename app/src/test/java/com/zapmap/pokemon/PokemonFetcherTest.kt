package com.zapmap.pokemon

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response

class PokemonFetcherTest {

    private lateinit var pokemonFetcher: PokemonFetcher

    @Mock
    private lateinit var pokemonApiService: PokemonApiService

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    private val testScope = TestCoroutineScope(testDispatcher)

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        pokemonFetcher = PokemonFetcher(pokemonApiService)
    }


    @Test
    fun test_fetchPokemons_success() = testScope.runBlockingTest {
        val pokemonList = listOf(
            RemotePokemonItem("bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/"),
            RemotePokemonItem("ivysaur", "https://pokeapi.co/api/v2/pokemon/2/"),
            RemotePokemonItem("venusaur", "https://pokeapi.co/api/v2/pokemon/3/")
        )

        `when`(pokemonApiService.fetchPokemons(limit = 50, offset = 0))
            .thenReturn(Response.success(PokemonsResponse(
                count = 3,
                next = null,
                pokemonItems = pokemonList)))

        val fetchedPokemonList = pokemonFetcher.fetchPokemons()

        assertEquals(pokemonList, fetchedPokemonList)
    }

    @Test
    fun test_fetchPokemonById_success() = testScope.runBlockingTest {
        val pokemonId = 1
        val pokemon = RemotePokemon(
            name = "bulbasaur",
            weight = 69,
            height = 7,
            types = listOf(TypeItem(Type("grass", "https://pokeapi.co/api/v2/type/12/"))),
            sprites = Sprite(frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png")
        )

        `when`(pokemonApiService.fetchPokemonById(pokemonId))
            .thenReturn(Response.success(pokemon))

        val fetchedPokemon = pokemonFetcher.fetchPokemonById(pokemonId)

        assertEquals(pokemon, fetchedPokemon)
    }
}