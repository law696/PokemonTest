package com.zapmap.pokemon

import com.squareup.moshi.Json

data class PokemonsResponse(
    val count: Int,
    val next: String? = null, // if this is null we've reached the end of the list
    @Json(name = "results")
    val pokemonItems: List<RemotePokemonItem>
)
