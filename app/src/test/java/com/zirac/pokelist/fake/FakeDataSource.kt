package com.zirac.pokelist.fake

import com.zirac.pokelist.network.Element
import com.zirac.pokelist.network.Pokemon
import com.zirac.pokelist.network.PokemonListResponse
import com.zirac.pokelist.network.PokemonResponse
import com.zirac.pokelist.network.Sprite
import com.zirac.pokelist.network.Stat
import com.zirac.pokelist.network.StatType
import com.zirac.pokelist.network.Type

object FakeDataSource {
    private const val ID = 9999
    private const val WEIGHT = 9999
    private const val HEIGHT = 9999
    private const val SLOT1 = 9999
    private const val SLOT2 = 8888
    private const val NAME1 = "test one"
    private const val NAME2 = "test two"
    private const val URL1 = "url.1"
    private const val URL2 = "url.1"
    private const val STAT1 = 9999
    private const val STAT2 = 8888
    private const val EFFORT1 = 9999
    private const val EFFORT2 = 8888

    private val elementOne = Element(
        name = NAME1,
        url = URL1
    )
    private val elementTwo = Element(
        name = NAME2,
        url = URL2
    )

    private val statTypeOne = StatType(
        name = NAME1,
        url = URL1
    )
    private val statTypeTwo = StatType(
        name = NAME2,
        url = URL2
    )

    private val types = listOf(
        Type(slot = SLOT1, elementOne),
        Type(SLOT2, elementTwo)
    )
    private val stats = listOf(
        Stat(baseStat = STAT1, effort = EFFORT1, statType = statTypeOne),
        Stat(baseStat = STAT2, effort = EFFORT2, statType = statTypeTwo)
    )
    private val sprite = Sprite(url = URL1)

    val pokemonResponse = PokemonResponse(
        id = ID,
        name = NAME1,
        weight = WEIGHT,
        height = HEIGHT,
        types = types,
        stats = stats,
        sprites = sprite
    )

    private val pokemonList = listOf(
        Pokemon(
            name = NAME1,
            url = URL1
        ),
        Pokemon(
            name = NAME2,
            url = URL2
        )
    )

    val pokemonListResponse = PokemonListResponse(
        count = pokemonList.size,
        next = null.toString(),
        previous = null.toString(),
        results = pokemonList
    )
}