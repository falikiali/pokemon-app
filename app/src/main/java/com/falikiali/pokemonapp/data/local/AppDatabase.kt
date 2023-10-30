package com.falikiali.pokemonapp.data.local

import androidx.room.RoomDatabase
import com.falikiali.pokemonapp.data.local.dao.PokemonDao

abstract class AppDatabase: RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

}