package com.zirac.pokelist

import android.app.Application
import com.zirac.pokelist.data.AppContainer
import com.zirac.pokelist.data.DefaultAppContainer

class PokemonApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}