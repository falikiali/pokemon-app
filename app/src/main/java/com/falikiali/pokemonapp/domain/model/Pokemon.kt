package com.falikiali.pokemonapp.domain.model

import com.falikiali.pokemonapp.data.local.entity.PokemonEntity

data class Pokemon(
    val name: String
) {
    fun toEntity(): PokemonEntity {
        return PokemonEntity(name)
    }
}
