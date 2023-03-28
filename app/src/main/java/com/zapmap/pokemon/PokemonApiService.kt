package com.zapmap.pokemon

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {

    @GET("pokemon")
    suspend fun fetchPokemons(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<PokemonsResponse>

    @GET("pokemon/{id}")
    suspend fun fetchPokemonById(
        @Path("id") id: Int,
    ): Response<RemotePokemon>
}