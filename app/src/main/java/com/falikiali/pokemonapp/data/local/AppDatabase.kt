package com.falikiali.pokemonapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.falikiali.pokemonapp.data.local.dao.PokemonDao
import com.falikiali.pokemonapp.data.local.entity.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

}