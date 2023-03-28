package com.zapmap.pokemon

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Response

class MainActivityTest {
    private lateinit var pokemonApiService: PokemonApiService
    private lateinit var pokemonFetcher: PokemonFetcher
    private lateinit var mainActivity: MainActivity

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Before
    fun setUp() {
        pokemonApiService = Mockito.mock(PokemonApiService::class.java)
        pokemonFetcher = PokemonFetcher(pokemonApiService)
        activityScenarioRule.scenario.onActivity { mainActivity = it }
    }

    @Test
    fun testGetIdFromUrl() {
        val url = "https://pokeapi.co/api/v2/pokemon/1/"
        val id = mainActivity.getIdFromUrl(url)
        assertEquals(1, id)
    }

    @Test
    fun testRecyclerViewDisplayed() {
        onView(withId(R.id.recyclerViewPokemon)).check(matches(isDisplayed()))
    }
}