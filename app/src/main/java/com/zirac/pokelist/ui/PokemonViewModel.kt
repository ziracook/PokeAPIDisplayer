package com.zirac.pokelist.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zirac.pokelist.network.PokemonApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface PokemonUiState {
    data class Success(val pokemon: String): PokemonUiState
    object Loading: PokemonUiState
    object Error: PokemonUiState

}

class PokemonViewModel: ViewModel() {
    var pokemonUiState: PokemonUiState by mutableStateOf(PokemonUiState.Loading)
        private set
    init {
        getPokemon()
    }

    private fun getPokemon() {
        viewModelScope.launch {
            pokemonUiState = try {
                val result = PokemonApi.retrofitService.getPokemon()
                PokemonUiState.Success(result)
            } catch (_: IOException) {
                PokemonUiState.Error
            }
        }
    }
}