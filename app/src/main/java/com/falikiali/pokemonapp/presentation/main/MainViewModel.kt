package com.falikiali.pokemonapp.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falikiali.pokemonapp.domain.model.Pokemon
import com.falikiali.pokemonapp.domain.usecase.PokemonUseCase
import com.falikiali.pokemonapp.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val pokemonUseCase: PokemonUseCase): ViewModel() {

    private val _mainState = MutableLiveData<ResultState<List<Pokemon>>>()
    val mainState: LiveData<ResultState<List<Pokemon>>> get() = _mainState

    fun getPokemon() {
        viewModelScope.launch {
            pokemonUseCase.getAllPokemon().collect {
                _mainState.value = it
            }
        }
    }

    fun insertPokemon(pokemons: List<Pokemon>) {
        viewModelScope.launch {
            pokemonUseCase.clearDataPokemon()
            pokemonUseCase.insertAll(pokemons)
        }
    }

}