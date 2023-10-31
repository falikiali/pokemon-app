package com.falikiali.pokemonapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.falikiali.pokemonapp.R
import com.falikiali.pokemonapp.databinding.ActivityPokemonBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonActivity : AppCompatActivity() {

    private val binding: ActivityPokemonBinding by lazy { ActivityPokemonBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpActionBar()
    }

    private fun setUpActionBar() {
        supportActionBar?.title = "Pokemon"
    }
}