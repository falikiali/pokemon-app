package com.falikiali.pokemonapp.di

import com.falikiali.pokemonapp.domain.usecase.ImplPokemonUseCase
import com.falikiali.pokemonapp.domain.usecase.PokemonUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun providePokemonUseCase(implPokemonUseCase: ImplPokemonUseCase): PokemonUseCase

}