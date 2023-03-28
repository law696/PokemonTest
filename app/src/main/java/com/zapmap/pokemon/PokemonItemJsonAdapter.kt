package com.zapmap.pokemon

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson

class PokemonItemJsonAdapter : JsonAdapter<RemotePokemonItem>() {
    private val delegate = Moshi.Builder()
        .build()
        .adapter(RemotePokemonItem::class.java)

    @FromJson
    override fun fromJson(reader: JsonReader): RemotePokemonItem? {
        return delegate.fromJson(reader)
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: RemotePokemonItem?) {
       delegate.toJson(writer, value)
    }
}