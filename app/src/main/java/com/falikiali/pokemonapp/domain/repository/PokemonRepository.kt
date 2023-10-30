package com.falikiali.pokemonapp.domain.repository

import com.falikiali.pokemonapp.domain.model.DetailPokemon
import com.falikiali.pokemonapp.domain.model.Pokemon
import com.falikiali.pokemonapp.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    /**
     * Remote
     */
    suspend fun getAllPokemon(): Flow<ResultState<List<Pokemon>>>
    suspend fun getDetailPokemon(name: String): Flow<ResultState<DetailPokemon>>

    /**
     * Local
     */
    suspend fun insertAll(pokemon: List<Pokemon>)
    suspend fun clearDataPokemon()
    fun getPokemon(sortType: Int?): Flow<List<Pokemon>>
    fun searchPokemon(search: String, sortType: Int?): Flow<List<Pokemon>>

}