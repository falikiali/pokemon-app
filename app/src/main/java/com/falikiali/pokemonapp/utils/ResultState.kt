package com.falikiali.pokemonapp.utils

sealed class ResultState<out T: Any> {
    data class Success<T: Any>(val data: T) : ResultState<T>()
    data class Failed(val error: String, val code: Int) : ResultState<Nothing>()
    data class Exception(val error: Throwable) : ResultState<Nothing>()
    object Loading : ResultState<Nothing>()
    object Empty : ResultState<Nothing>()
}