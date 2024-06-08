package com.zirac.pokelist.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import com.zirac.pokelist.R
import com.zirac.pokelist.network.Pokemon

private lateinit var viewModel: PokemonViewModel

@Composable
fun PokemonListScreen(
    pokemonListUiState: PokemonListUiState,
    pokemonViewModel: PokemonViewModel,
    modifier: Modifier = Modifier
) {
    viewModel = pokemonViewModel
    when (pokemonListUiState) {
        is PokemonListUiState.Success -> PokemonList(pokemonViewModel.livePokemon, modifier.fillMaxSize(), pokemonViewModel)
        is PokemonListUiState.Loading -> LoadingSpinner(modifier.fillMaxSize())
        is PokemonListUiState.Error -> ErrorMessage(modifier.fillMaxSize())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonList(livePokemon: LiveData<List<Pokemon>>, modifier: Modifier = Modifier, pokemonViewModel: PokemonViewModel) {
    val pokemonList by pokemonViewModel.livePokemon.observeAsState(initial = emptyList())
    LazyColumn (modifier = modifier
        .padding(top = 8.dp)){
        items(pokemonList) { pokemon ->
            Pokemon(pokemon)
        }
    }

    //val pokemonList by livePokemon.observeAsState(initial = emptyList())
//    val listState = rememberLazyListState()
//    val atBottom: Boolean by remember {
//        derivedStateOf { !listState.canScrollForward }
//    }
//    LaunchedEffect(atBottom) {
//        if (atBottom) // load more {
//            viewModel.loadMorePokemon()
//    }

//    val pullToRefreshState = rememberPullToRefreshState()
//    Box(modifier = modifier.nestedScroll(pullToRefreshState.nestedScrollConnection)) {
//        LazyColumn (modifier = modifier
//            .padding(top = 8.dp)){
//            items(pokemonList) { pokemon ->
//                Pokemon(pokemon)
//            }
//        }
//
//        PullToRefreshContainer(
//            state = pullToRefreshState,
//            modifier = Modifier.align(Alignment.TopCenter)
//        )
//    }

}

@Composable
fun Pokemon(pokemon: Pokemon) {
    // Capitalize the first letter
    val name = pokemon.name.replaceFirstChar(Char::titlecaseChar)

    FilledTonalButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 16.dp),
        //onClick = { onPokemonClicked(pokemon) }
        onClick = {viewModel.loadMorePokemon()}
    ) {
        Text(text = name,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
            fontSize = 18.sp
        )
    }
}

private fun onPokemonClicked(pokemon: Pokemon) {
    viewModel.getPokemonByName(pokemon.name)
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