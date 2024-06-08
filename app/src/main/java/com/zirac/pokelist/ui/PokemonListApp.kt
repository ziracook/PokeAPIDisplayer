package com.zirac.pokelist.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zirac.pokelist.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PokemonListApp() {
    Scaffold(
        topBar = { SearchField() }
    ) { innerPadding ->
        val pokemonViewModel: PokemonViewModel = viewModel(factory = PokemonViewModel.Factory)

        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            PokemonListScreen(pokemonListUiState = pokemonViewModel.pokemonListUiState, pokemonViewModel = pokemonViewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField() {
    var searchText by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }
    SearchBar(
        query = searchText,
        onQueryChange = {searchText = it},
        onSearch = {active = false},
        active = false,
        onActiveChange = {active = it},
        modifier = Modifier
            .padding(horizontal = 6.dp)
            .fillMaxWidth(),
        leadingIcon = {Icon(imageVector = Icons.Default.Search, contentDescription = stringResource(R.string.search_icon_cd))}) {
        // When user presses enter, fetch pokemon with name
        // Navigate to pokemon page
    }
}