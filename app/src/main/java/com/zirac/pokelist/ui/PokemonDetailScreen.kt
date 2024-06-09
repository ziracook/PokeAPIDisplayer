package com.zirac.pokelist.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.zirac.pokelist.network.PokemonResponse
import com.zirac.pokelist.R

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
    // Capitalize the first letter
    val name = pokemon.name.replaceFirstChar(Char::titlecaseChar)

    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxSize())
    {
        PokemonPhoto(url = pokemon.sprites.url)
        PokemonDetail("Id: ${pokemon.id}")
        PokemonDetail("Name: $name")
        PokemonDetail("Weight: ${pokemon.weight}")
        PokemonDetail("Height: ${pokemon.height}")
//        - Image
//        - Id
//        - Name
//        - Weight
//        - Height
//        - Type
//        - Stats
    }
}

@Composable
fun PokemonPhoto(url: String) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(url)
            .build(),
        contentDescription = "",
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun PokemonDetail(detail: String) {
    ElevatedButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 16.dp),
        onClick = { },
        enabled = false
    ) {
        Text(text = detail,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
            fontSize = 16.sp
        )
    }
}

@Composable
fun Loading() {

}

@Composable
fun Error() {
    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxSize())
    {
        Text(text = stringResource(id = R.string.api_error),
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
            fontSize = 20.sp
        )
    }
}