package com.zapmap.pokemon

import com.squareup.moshi.Json

data class RemotePokemon(
    val name: String,
    val weight: Int,
    val height: Int,
    val types: List<TypeItem>,
    val sprites: Sprite
)

data class TypeItem(val type: Type)

data class Type(val name: String, val url: String)

data class Sprite(
    @Json(name = "front_default")
    val frontDefault: String
)