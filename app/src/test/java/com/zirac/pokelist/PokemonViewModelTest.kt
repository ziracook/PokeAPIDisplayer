package com.zirac.pokelist

import com.zirac.pokelist.fake.FakeDataSource
import com.zirac.pokelist.fake.FakePokemonRepository
import com.zirac.pokelist.rules.TestDispatcherRule
import com.zirac.pokelist.ui.PokemonListUiState
import com.zirac.pokelist.ui.PokemonUiState
import com.zirac.pokelist.ui.PokemonViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PokemonViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun getPokemonList_verifyPokemonListUiState() = runTest {
        val pokemonViewModel = PokemonViewModel(
            pokemonRepository = FakePokemonRepository("")
        )
        assertEquals(
            PokemonListUiState.Success(FakeDataSource.pokemonListResponse.results), pokemonViewModel.pokemonListUiState
        )
    }

    @Test
    fun getPokemonByName_verifyPokemonUiState_Loading() = runTest {
        val pokemonViewModel = PokemonViewModel(
            pokemonRepository = FakePokemonRepository("")
        )
        assertEquals(
            PokemonUiState.Loading, pokemonViewModel.pokemonUiState
        )
    }

    @Test
    fun getPokemonByName_verifyPokemonUiState_Success() = runTest {
        val pokemonViewModel = PokemonViewModel(
            pokemonRepository = FakePokemonRepository("")
        )
        pokemonViewModel.getPokemonByName("")
        assertEquals(
            PokemonUiState.Success(FakeDataSource.pokemonResponse), pokemonViewModel.pokemonUiState
        )
    }
}