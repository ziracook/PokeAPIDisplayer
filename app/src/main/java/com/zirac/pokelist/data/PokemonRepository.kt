package com.zirac.pokelist.data

import com.zirac.pokelist.network.PokemonApiService
import com.zirac.pokelist.network.PokemonListResponse
import com.zirac.pokelist.network.PokemonResponse

interface PokemonRepository {
    val apiEndpoint: String
    suspend fun getPokemonList(): PokemonListResponse
    suspend fun getPokemonByName(name: String): PokemonResponse
    suspend fun getNextPokemonPage(url: String): PokemonListResponse
}

class NetworkPokemonRepository(
    private val pokemonApiService: PokemonApiService
): PokemonRepository {
    override val apiEndpoint = "https://pokeapi.co/api/v2/"
    override suspend fun getPokemonList():  PokemonListResponse{
        return pokemonApiService.getPokemonList()
    }

    override suspend fun getPokemonByName(name: String):  PokemonResponse{
        return pokemonApiService.getPokemonByName(name)
    }

    override suspend fun getNextPokemonPage(url: String): PokemonListResponse {
        return pokemonApiService.getNextPokemonPage(url)
    }
}