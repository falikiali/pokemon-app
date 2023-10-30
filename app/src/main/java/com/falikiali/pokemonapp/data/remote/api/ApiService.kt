package com.falikiali.pokemonapp.data.remote.api

import com.falikiali.pokemonapp.data.remote.dto.DetailPokemonResponse
import com.falikiali.pokemonapp.data.remote.dto.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("pokemon")
    suspend fun getAllPokemon(): Response<PokemonResponse>

    @GET("pokemon/{name}")
    suspend fun getDetailPokemon(@Path("name") name: String): Response<DetailPokemonResponse>

}