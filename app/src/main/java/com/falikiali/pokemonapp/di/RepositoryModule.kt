package com.falikiali.pokemonapp.di

import com.falikiali.pokemonapp.data.ImplPokemonRepository
import com.falikiali.pokemonapp.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providePokemonRepository(implPokemonRepository: ImplPokemonRepository): PokemonRepository

}