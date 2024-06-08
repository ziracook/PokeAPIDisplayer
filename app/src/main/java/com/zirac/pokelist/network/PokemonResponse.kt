package com.zirac.pokelist.network

import com.google.gson.annotations.SerializedName
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
    @SerializedName("sprites") val sprites: Sprite
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

@Serializable
data class Sprite(
    @SerializedName("front_default")
    val url: String,
)