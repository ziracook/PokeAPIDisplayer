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
    @SerializedName("type")
    val element: Element
)

@Serializable
data class Element(
    val name: String,
    val url: String
)


@Serializable
data class Stat(
    @SerialName("base_stat")
    val baseStat: Int,
    val effort: Int,
    @SerializedName("stat")
    val statType: StatType
)

@Serializable
data class StatType(
    val name: String,
    val url: String
)

@Serializable
data class Sprite(
    @SerializedName("front_default")
    val url: String,
)