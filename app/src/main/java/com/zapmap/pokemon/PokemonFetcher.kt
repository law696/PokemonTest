package com.zapmap.pokemon

class PokemonFetcher(private val apiService: PokemonApiService) {
    companion object {
        private const val LIMIT = 50
    }
    private var currentOffset = 0

    suspend fun fetchPokemons(): List<RemotePokemonItem> {
        val response = apiService.fetchPokemons(limit = LIMIT, offset = currentOffset)
        currentOffset += LIMIT

        return if (response.isSuccessful) {
            response.body()?.pokemonItems.orEmpty()
        } else {
            emptyList()
        }
    }

    suspend fun fetchPokemonById(id: Int): RemotePokemon? {
        val response = apiService.fetchPokemonById(id)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}