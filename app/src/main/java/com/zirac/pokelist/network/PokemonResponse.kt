package com.zirac.pokelist.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val types: List<Type>,
    val stats: List<Stat>,
)

@Serializable
data class Type(
    val slot: Int,
)


@Serializable
data class Stat(
    @SerialName("base_stat")
    val baseStat: Int,
    val effort: Int
)