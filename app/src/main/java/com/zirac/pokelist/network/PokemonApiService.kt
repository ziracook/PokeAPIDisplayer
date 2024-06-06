package com.zirac.pokelist.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val API_ENDPOINT = "https://pokeapi.co/api/v2/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(API_ENDPOINT)
    .build()

interface PokemonApiService {

    /*
    Returns a list of 20 pokemon and their url
    The next url for pagination
     */
    @GET("pokemon")
    suspend fun getPokemon(): String
}

// Retrofit singleton
object PokemonApi {
    val retrofitService : PokemonApiService by lazy {
        retrofit.create(PokemonApiService::class.java)
    }
}