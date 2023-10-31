package com.falikiali.pokemonapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falikiali.pokemonapp.domain.model.DetailPokemon
import com.falikiali.pokemonapp.domain.usecase.PokemonUseCase
import com.falikiali.pokemonapp.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPokemonViewModel @Inject constructor(private val pokemonUseCase: PokemonUseCase): ViewModel() {

    private val _detailState = MutableLiveData<ResultState<DetailPokemon>>()
    val detailState: LiveData<ResultState<DetailPokemon>> get() = _detailState

    fun getDetailPokemon(name: String) {
        viewModelScope.launch {
            pokemonUseCase.getDetailPokemon(name).collect {
                _detailState.value = it
            }
        }
    }

}