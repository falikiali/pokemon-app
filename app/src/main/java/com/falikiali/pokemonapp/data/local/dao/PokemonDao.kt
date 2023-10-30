package com.falikiali.pokemonapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.falikiali.pokemonapp.data.local.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Insert
    suspend fun insertAll(vararg pokemonEntity: PokemonEntity)

    @Query("DELETE FROM tbl_pokemon")
    suspend fun clearDataPokemon()

    @Query("SELECT * FROM tbl_pokemon ORDER BY " +
        "CASE WHEN :sortType = 1 THEN name END ASC, " +
        "CASE WHEN :sortType = 2 THEN name END DESC")
    fun getPokemon(sortType: Int?): Flow<List<PokemonEntity>>

    @Query("SELECT * FROM tbl_pokemon WHERE name LIKE :search ORDER BY " +
            "CASE WHEN :sortType = 1 THEN name END ASC, " +
            "CASE WHEN :sortType = 2 THEN name END DESC")
    fun searchPokemon(search: String?, sortType: Int?): Flow<List<PokemonEntity>>

}