package com.falikiali.pokemonapp.domain.usecase

import com.falikiali.pokemonapp.domain.model.DetailPokemon
import com.falikiali.pokemonapp.domain.model.Pokemon
import com.falikiali.pokemonapp.domain.repository.PokemonRepository
import com.falikiali.pokemonapp.utils.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImplPokemonUseCase @Inject constructor(private val pokemonRepository: PokemonRepository): PokemonUseCase {
    /**
     * Remote
     */
    override suspend fun getAllPokemon(): Flow<ResultState<List<Pokemon>>> {
        return pokemonRepository.getAllPokemon()
    }

    override suspend fun getDetailPokemon(name: String): Flow<ResultState<DetailPokemon>> {
        return pokemonRepository.getDetailPokemon(name)
    }

    /**
     * Local
     */
    override suspend fun insertAll(pokemon: List<Pokemon>) {
        return pokemonRepository.insertAll(pokemon)
    }

    override suspend fun clearDataPokemon() {
        return pokemonRepository.clearDataPokemon()
    }

    override fun getPokemon(sortType: Int?): Flow<List<Pokemon>> {
        return pokemonRepository.getPokemon(sortType)
    }

    override fun searchPokemon(search: String, sortType: Int?): Flow<List<Pokemon>> {
        return pokemonRepository.searchPokemon(search, sortType)
    }
}