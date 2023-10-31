package com.falikiali.pokemonapp.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.falikiali.pokemonapp.R
import com.falikiali.pokemonapp.databinding.ActivityMainBinding
import com.falikiali.pokemonapp.presentation.PokemonActivity
import com.falikiali.pokemonapp.utils.ResultState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.getPokemon()
        observeViewModel()
    }

    private fun observeViewModel() {
        with(viewModel) {
            mainState.observe(this@MainActivity) {
                when (it) {
                    is ResultState.Success -> {
                        insertPokemon(it.data)

                        val i = Intent(this@MainActivity, PokemonActivity::class.java)
                        startActivity(i).also { finish() }
                        overridePendingTransition(0, R.anim.fade_out)
                    }
                    is ResultState.Failed -> showSnackbar("Please try again")
                    else -> {}
                }
            }
        }
    }

    private fun showSnackbar(snackbarMessage: String) {
        Snackbar.make(binding.root, snackbarMessage, Snackbar.LENGTH_SHORT)
            .show()
    }

}