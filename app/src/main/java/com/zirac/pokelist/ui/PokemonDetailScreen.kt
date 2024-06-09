package com.zirac.pokelist.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.zirac.pokelist.R
import com.zirac.pokelist.network.PokemonResponse
import com.zirac.pokelist.network.Stat
import com.zirac.pokelist.network.Type
import com.zirac.pokelist.ui.theme.secondaryContainerLight

@Composable
fun PokemonDetailScreen(pokemonUiState: PokemonUiState) {
    when (pokemonUiState) {
        is PokemonUiState.Success -> PokemonDetails(pokemon = pokemonUiState.pokemon)
        is PokemonUiState.Loading -> Loading()
        is PokemonUiState.Error -> Error()
    }
}

@Composable
fun PokemonDetails(pokemon: PokemonResponse, modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .padding(8.dp)
        .fillMaxWidth()
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        PokemonName(name = pokemon.name)
        PokemonPhoto(url = pokemon.sprites.url)
        PokemonDetail("Id: ${pokemon.id}")
        PokemonDetail("Weight: ${pokemon.weight}")
        PokemonDetail("Height: ${pokemon.height}")
        PokemonStatsAndTypes(pokemon)
    }
}

@Composable
private fun PokemonStatsAndTypes(pokemon: PokemonResponse) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .background(
                color = secondaryContainerLight,
                shape = RoundedCornerShape(16.dp)),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        PokemonTypes(pokemon.types)
        PokemonStats(pokemon.stats)
    }
}

@Composable
fun PokemonName(name: String) {
    // Capitalize the first letter
    val pokemonName = name.replaceFirstChar(Char::titlecaseChar)

    Text(text = pokemonName,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 16.dp),
        fontSize = 36.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        style = TextStyle(
            brush = Brush.linearGradient(
                colors = listOf(Color.Green, Color.DarkGray, Color.Cyan)
            )
        )
    )
}

@Composable
fun PokemonPhoto(url: String) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(url)
            .build(),
        contentDescription = "",
        modifier = Modifier.fillMaxWidth(),
        contentScale = ContentScale.FillWidth
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
fun PokemonTypes(types: List<Type>) {
    val typeString = StringBuilder()
    types.forEach {
        typeString.append(it.element.name)
        typeString.append("\n")
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "\nTypes: ", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Text(typeString.toString(), textAlign = TextAlign.Center)
    }
}

@Composable
fun PokemonStats(stats: List<Stat>) {
    val statString = StringBuilder()
    stats.forEach { stat ->
        statString.append(stat.statType.name)
        statString.append("\n")
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "\nStats: ", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Text(statString.toString(), textAlign = TextAlign.Center)
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