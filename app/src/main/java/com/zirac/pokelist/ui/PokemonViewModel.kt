package com.zirac.pokelist.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.zirac.pokelist.PokemonApplication
import com.zirac.pokelist.data.PokemonRepository
import com.zirac.pokelist.network.Pokemon
import com.zirac.pokelist.network.PokemonResponse
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface PokemonListUiState {
    data class Success(val pokemon: List<Pokemon>): PokemonListUiState
    data object Loading: PokemonListUiState
    data object Error: PokemonListUiState

}

sealed interface PokemonUiState {
    data class Success(val pokemon: PokemonResponse): PokemonUiState
    data object Loading: PokemonUiState
    data object Error: PokemonUiState

}

class PokemonViewModel(private val pokemonRepository: PokemonRepository): ViewModel() {
    var pokemonListUiState: PokemonListUiState by mutableStateOf(PokemonListUiState.Loading)
        private set
    var pokemonUiState: PokemonUiState by mutableStateOf(PokemonUiState.Loading)
        private set

    val livePokemon = MutableLiveData<List<Pokemon>>()
    private var pokemon = mutableListOf<Pokemon>()

    var next: String by mutableStateOf("")
        private set
    init {
        getPokemonList()
    }

    fun getPokemonList() {
        viewModelScope.launch {
            pokemonListUiState = try {
                val result = pokemonRepository.getPokemonList()
                pokemon.clear()
                pokemon.addAll(result.results)
                livePokemon.value = pokemon
                next = result.next
                PokemonListUiState.Success(result.results)
            } catch (_: IOException) {
                PokemonListUiState.Error
            }
        }
    }

    fun getPokemonByName(name: String) {
        viewModelScope.launch {
            pokemonUiState = try {
                val result = pokemonRepository.getPokemonByName(name)
                PokemonUiState.Success(result)
            } catch (_: IOException) {
                PokemonUiState.Error
            }
        }
    }

    fun loadMorePokemon() {
        viewModelScope.launch {
            try {
                val param = next.replace(pokemonRepository.apiEndpoint, "")
                val result = pokemonRepository.getMorePokemon()
                pokemon.addAll(result.results)
                livePokemon.value = pokemon
                next = result.next
            } catch (_: IOException) {
                pokemonListUiState = PokemonListUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PokemonApplication)
                val pokemonRepository = application.container.pokemonRepository
                PokemonViewModel(pokemonRepository = pokemonRepository)
            }
        }
    }
}