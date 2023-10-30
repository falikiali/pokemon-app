package com.falikiali.pokemonapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.falikiali.pokemonapp.domain.model.Pokemon

@Entity(tableName = "tbl_pokemon")
data class PokemonEntity(
    @PrimaryKey @ColumnInfo("name") val name: String
) {
    fun toDomain(): Pokemon {
        return Pokemon(name)
    }
}
