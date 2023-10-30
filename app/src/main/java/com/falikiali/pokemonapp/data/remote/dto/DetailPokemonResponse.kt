package com.falikiali.pokemonapp.data.remote.dto

import com.falikiali.pokemonapp.domain.model.DetailPokemon
import com.google.gson.annotations.SerializedName

data class DetailPokemonResponse(
    @field:SerializedName("abilities")
    val abilities: List<AbilitiesPokemonResponse>,

    @field:SerializedName("sprites")
    val sprites: SpritesPokemonResponse
) {
    fun toDomain(): DetailPokemon {
        return DetailPokemon(sprites.frontDefault, abilities.map { it.name })
    }
}

data class SpritesPokemonResponse(
    @field:SerializedName("front_default")
    val frontDefault: String? = null
)

data class AbilitiesPokemonResponse(
    @field:SerializedName("name")
    val name: String
)
