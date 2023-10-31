package com.falikiali.pokemonapp.presentation.pokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falikiali.pokemonapp.domain.model.Pokemon
import com.falikiali.pokemonapp.domain.usecase.PokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(private val pokemonUseCase: PokemonUseCase): ViewModel() {

    private val _resultPokemon = MutableLiveData<List<Pokemon>>()
    val resultPokemon: LiveData<List<Pokemon>> get() = _resultPokemon

    fun getPokemon(sortType: Int?) {
        viewModelScope.launch {
            pokemonUseCase.getPokemon(sortType).collect {
                _resultPokemon.value = it
            }
        }
    }

    fun searchPokemon(search: String, sortType: Int?) {
        viewModelScope.launch {
            pokemonUseCase.searchPokemon(search, sortType).collect {
                _resultPokemon.value = it
            }
        }
    }

}