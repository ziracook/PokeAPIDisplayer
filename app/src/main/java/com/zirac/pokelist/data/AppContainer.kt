package com.zirac.pokelist.data

import com.zirac.pokelist.network.PokemonApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val pokemonRepository: PokemonRepository
}

class DefaultAppContainer : AppContainer {
    private val apiEndpoint = "https://pokeapi.co/api/v2/"

    private val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client : OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(interceptor)
    }.build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(apiEndpoint)
        .client(client)
        .build()

    private val retrofitService: PokemonApiService by lazy {
        retrofit.create(PokemonApiService::class.java)
    }

    override val pokemonRepository: PokemonRepository by lazy {
        NetworkPokemonRepository(retrofitService)
    }
}