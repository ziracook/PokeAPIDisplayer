package com.zirac.pokelist.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.zirac.pokelist.network.PokemonResponse

@Composable
fun PokemonDetailScreen(pokemonUiState: PokemonUiState) {
    when (pokemonUiState) {
        is PokemonUiState.Success -> PokemonDetails(pokemon = pokemonUiState.pokemon)
        is PokemonUiState.Loading -> Loading()
        is PokemonUiState.Error -> Error()
    }
}

@Composable
fun PokemonDetails(pokemon: PokemonResponse) {
    val name = pokemon.name

    Text("hello")
}

@Composable
fun Loading() {

}

@Composable
fun Error() {

}