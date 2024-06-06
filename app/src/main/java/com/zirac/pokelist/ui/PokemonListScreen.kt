package com.zirac.pokelist.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zirac.pokelist.R
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
    LazyColumn (modifier = modifier
        .padding(top = 8.dp)) {
        items(list) {pokemon ->
            Pokemon(pokemon)
        }
    }
}

@Composable
fun Pokemon(pokemon: Pokemon) {
    // Capitalize the first letter
    val name = pokemon.name.replaceFirstChar(Char::titlecaseChar)

    FilledTonalButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 16.dp),
        onClick = {}
    ) {
        Text(text = name,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
            fontSize = 18.sp
        )
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
        Text(text = stringResource(id = R.string.api_error))
    }
}