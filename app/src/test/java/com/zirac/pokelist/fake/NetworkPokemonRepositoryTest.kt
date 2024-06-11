package com.zirac.pokelist.fake

import com.zirac.pokelist.data.NetworkPokemonRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class NetworkPokemonPhotosRepository {
    private lateinit var repository: NetworkPokemonRepository
    @Before
    fun setup() {
        repository = NetworkPokemonRepository(
            pokemonApiService = FakePokemonApiService()
        )
    }

    @Test
    fun getPokemonList_verifyPokemonList() = runTest {
        assertEquals(FakeDataSource.pokemonListResponse, repository.getPokemonList())
    }

    @Test
    fun getPokemonByName_verifyPokemon() = runTest {
        assertEquals(FakeDataSource.pokemonResponse, repository.getPokemonByName(""))
    }

    @Test
    fun getNextPokemonPage_verifyPokemonList() = runTest {
        assertEquals(FakeDataSource.pokemonListResponse, repository.getNextPokemonPage(""))
    }
}
