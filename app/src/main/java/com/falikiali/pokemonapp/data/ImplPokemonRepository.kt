package com.falikiali.pokemonapp.data

import com.falikiali.pokemonapp.data.local.dao.PokemonDao
import com.falikiali.pokemonapp.data.remote.api.ApiService
import com.falikiali.pokemonapp.domain.model.DetailPokemon
import com.falikiali.pokemonapp.domain.model.Pokemon
import com.falikiali.pokemonapp.domain.repository.PokemonRepository
import com.falikiali.pokemonapp.utils.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import javax.inject.Inject

class ImplPokemonRepository @Inject constructor(private val apiService: ApiService, private val pokemonDao: PokemonDao): PokemonRepository {
    /**
     * Remote
     */
    override suspend fun getAllPokemon(): Flow<ResultState<List<Pokemon>>> {
        return flow {
            emit(ResultState.Loading)
            try {
                val response = apiService.getAllPokemon()
                if (response.isSuccessful) {
                    val data = response.body()!!.result.map { it.toDomain() }
                    emit(ResultState.Success(data))
                } else {
                    response.errorBody()?.let {
                        emit(ResultState.Failed("Error", response.code()))
                    }
                }
            } catch (e: HttpException) {
                emit(ResultState.Failed(e.message(), e.code()))
            } catch (e: Exception) {
                emit(ResultState.Failed(e.stackTraceToString(), 0))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getDetailPokemon(name: String): Flow<ResultState<DetailPokemon>> {
        return flow {
            emit(ResultState.Loading)
            try {
                val response = apiService.getDetailPokemon(name)
                if (response.isSuccessful) {
                    val data = response.body()!!.toDomain()
                    emit(ResultState.Success(data))
                } else {
                    response.errorBody()?.let {
                        emit(ResultState.Failed("Error", response.code()))
                    }
                }
            } catch (e: HttpException) {
                emit(ResultState.Failed(e.message(), e.code()))
            } catch (e: Throwable) {
                emit(ResultState.Failed(e.stackTraceToString(), 0))
            }
        }.flowOn(Dispatchers.IO)
    }

    /**
     * Local
     */
    override suspend fun insertAll(pokemon: List<Pokemon>) {
        return pokemonDao.insertAll(*pokemon.map { it.toEntity() }.toTypedArray())
    }

    override suspend fun clearDataPokemon() {
        return pokemonDao.clearDataPokemon()
    }

    override fun getPokemon(sortType: Int?): Flow<List<Pokemon>> {
        return pokemonDao.getPokemon(sortType).map { list ->
            list.map {
                it.toDomain()
            }
        }
    }

    override fun searchPokemon(search: String, sortType: Int?): Flow<List<Pokemon>> {
        return pokemonDao.searchPokemon("%$search%", sortType).map { list ->
            list.map {
                it.toDomain()
            }
        }
    }
}