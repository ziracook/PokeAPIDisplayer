package com.zirac.pokelist.fake

import com.zirac.pokelist.data.NetworkPokemonRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class NetworkPokemonPhotosRepository {
    @Test
    fun getPokemonList_verifyPokemonList() = runTest {
        val repository = NetworkPokemonRepository(
            pokemonApiService = FakePokemonApiService()
        )
        assertEquals(FakeDataSource.pokemonListResponse, repository.getPokemonList())
    }

    @Test
    fun getPokemonByName_verifyPokemon() = runTest {
        val repository = NetworkPokemonRepository(
            pokemonApiService = FakePokemonApiService()
        )
        assertEquals(FakeDataSource.pokemonResponse, repository.getPokemonByName(""))
    }

    @Test
    fun getNextPokemonPage_verifyPokemonList() = runTest {
        val repository = NetworkPokemonRepository(
            pokemonApiService = FakePokemonApiService()
        )
        assertEquals(FakeDataSource.pokemonListResponse, repository.getNextPokemonPage(""))
    }
}
