package com.zirac.pokelist.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.zirac.pokelist.network.Pokemon

@Composable
fun PokemonListScreen(
    pokemonUiState: PokemonUiState,
    modifier: Modifier = Modifier
) {
    when (pokemonUiState) {
        is PokemonUiState.Success -> PokemonList(pokemonUiState.pokemon, modifier.fillMaxSize())
        is PokemonUiState.Loading -> LoadingSpinner(modifier.fillMaxSize())
        is PokemonUiState.Error -> ErrorMessage(modifier.fillMaxSize())
    }
}

@Composable
fun PokemonList(list: List<Pokemon>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(list) {pokemon ->
            // Capitalize the first letter
            val name = pokemon.name.replaceFirstChar(Char::titlecaseChar)
            Text(text = name)
        }
    }
}
@Composable
fun LoadingSpinner(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorMessage(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = "bork")
    }
}