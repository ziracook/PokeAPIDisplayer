package com.zirac.pokelist.fake

import com.zirac.pokelist.data.PokemonRepository
import com.zirac.pokelist.network.PokemonListResponse
import com.zirac.pokelist.network.PokemonResponse

class FakePokemonRepository(override val apiEndpoint: String) : PokemonRepository {
    override suspend fun getPokemonList(): PokemonListResponse {
        return FakeDataSource.pokemonListResponse
    }

    override suspend fun getPokemonByName(name: String): PokemonResponse {
        return FakeDataSource.pokemonResponse
    }

    override suspend fun getNextPokemonPage(url: String): PokemonListResponse {
        return FakeDataSource.pokemonListResponse
    }
}