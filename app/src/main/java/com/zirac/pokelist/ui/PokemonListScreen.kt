package com.zirac.pokelist.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.res.stringResource
import com.zirac.pokelist.R

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
fun PokemonList(list: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = list)
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