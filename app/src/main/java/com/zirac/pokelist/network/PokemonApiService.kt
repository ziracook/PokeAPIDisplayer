package com.zirac.pokelist.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonApiService {

    /*
    Returns a list of 20 pokemon and their url
    The next url for pagination
     */
    @GET("pokemon")
    suspend fun getPokemonList(): PokemonListResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(
        @Path("name") name: String
    ): PokemonResponse

    @GET
    suspend fun getNextPokemonPage(
        @Url url: String
    ): PokemonListResponse
}