package com.falikiali.pokemonapp.data.remote.dto

import com.falikiali.pokemonapp.domain.model.Pokemon
import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @field:SerializedName("count")
    val count: Int,

    @field:SerializedName("next")
    val next: String,

    @field:SerializedName("previous")
    val previous: String? = null,

    @field:SerializedName("result")
    val result: List<ResultPokemonResponse>
)

data class ResultPokemonResponse(
    @field:SerializedName("name")
    val name: String
) {
    fun toDomain(): Pokemon {
        return Pokemon(name)
    }
}
