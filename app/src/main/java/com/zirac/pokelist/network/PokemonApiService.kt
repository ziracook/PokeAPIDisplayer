package com.zirac.pokelist.network

import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {

    /*
    Returns a list of 20 pokemon and their url
    The next url for pagination
     */
    @GET("pokemon")
    suspend fun getPokemonList(): PokemonListResponse

    @GET("pokemon?offset=20&limit=20")
    suspend fun getPokemonList(
        @Path("parameters") parameters: String
    ): PokemonListResponse

    @GET("pokemon?offset=20&limit=20")
    suspend fun getMorePokemon(): PokemonListResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(
        @Path("name") name: String
    ): PokemonResponse
}