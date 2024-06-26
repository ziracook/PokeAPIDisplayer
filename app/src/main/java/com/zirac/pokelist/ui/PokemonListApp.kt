package com.zirac.pokelist.ui

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zirac.pokelist.R

/**
 * enum values that represent the screens in the app
 */
enum class PokemonScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Detail(title = R.string.detail),
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PokemonListApp(navController: NavHostController = rememberNavController()) {
    val pokemonViewModel: PokemonViewModel = viewModel(factory = PokemonViewModel.Factory)

    NavHost(
        navController = navController,
        startDestination = PokemonScreen.Start.name,
        //modifier = Modifier.padding(innerPadding)
    ) {
        composable(route = PokemonScreen.Start.name) {
            Scaffold(
                topBar = { SearchField({ navController.navigate(PokemonScreen.Detail.name) }, pokemonViewModel)}
            ) { innerPadding ->
                Column(
                    modifier = Modifier.padding(innerPadding),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    PokemonListScreen(
                        pokemonListUiState = pokemonViewModel.pokemonListUiState,
                        pokemonViewModel = pokemonViewModel,
                        onNavigateToPokemonDetailScreen = { navController.navigate(PokemonScreen.Detail.name) })
                }
            }
        }
        composable(route = PokemonScreen.Detail.name) {
            Scaffold(
                topBar = {DetailScreenTopBar(navController)}
            ) { innerPadding ->
                Column(
                    modifier = Modifier.padding(innerPadding),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    PokemonDetailScreen(pokemonUiState = pokemonViewModel.pokemonUiState)
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(onSearchClicked: () -> Unit, pokemonViewModel: PokemonViewModel) {
    var searchText by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }
    var searchFailed by rememberSaveable{ mutableStateOf(false) }
    SearchBar(
        query = searchText,
        onQueryChange = {searchText = it},
        onSearch = {
            active = false
            pokemonViewModel.getPokemonByName(it.lowercase(), onSearchClicked, {searchFailed = true})
       },
        active = false,
        onActiveChange = {active = it},
        modifier = Modifier
            .padding(horizontal = 6.dp)
            .fillMaxWidth(),
        leadingIcon = {Icon(imageVector = Icons.Default.Search, contentDescription = stringResource(R.string.search_icon_cd))}) {
    }

    if (searchFailed) {
        PokemonLoadError()
        searchFailed = false
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenTopBar(navController: NavHostController) {
    TopAppBar(
        title = { Text(text = "Pokemon Details") },
        navigationIcon = {
            if (navController.previousBackStackEntry != null) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
                }
            }
        }
    )
}

@Composable
fun PokemonLoadError() {
    val text = stringResource(id = R.string.search_error)
    val duration = Toast.LENGTH_LONG

    val toast = Toast.makeText(LocalContext.current, text, duration) // in Activity
    toast.show()
}