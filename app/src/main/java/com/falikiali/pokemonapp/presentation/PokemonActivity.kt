package com.falikiali.pokemonapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.falikiali.pokemonapp.R
import com.falikiali.pokemonapp.databinding.ActivityPokemonBinding
import com.falikiali.pokemonapp.presentation.pokemon.PokemonAdapter
import com.falikiali.pokemonapp.presentation.pokemon.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonActivity : AppCompatActivity() {

    private val binding: ActivityPokemonBinding by lazy { ActivityPokemonBinding.inflate(layoutInflater) }
    private val viewModel: PokemonViewModel by viewModels()

    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpActionBar()
        viewModel.getPokemon(null)
        initRv()
        observeViewModel()
        searchPokemon()
    }

    private fun setUpActionBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Pokemon"
        }
    }

    private fun initRv() {
        pokemonAdapter = PokemonAdapter(onItemClick = {
//            val intent = Intent(this@PokemonActivity, DetailMovieActivity::class.java)
//            intent.putExtra(DetailMovieActivity.ID_MOVIE, it.id)
//            startActivity(intent)
        })

        with(binding.rvPokemon) {
            layoutManager = LinearLayoutManager(this@PokemonActivity)
            adapter = pokemonAdapter
        }
    }

    private fun observeViewModel() {
        with(viewModel) {
            resultPokemon.observe(this@PokemonActivity) {
                if (it.isNotEmpty()) {
                    pokemonAdapter.submitList(it)
                }
            }
        }
    }

    private fun searchPokemon() {
        binding.apply {
            svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    viewModel.searchPokemon(p0!!, null)
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    if (p0?.isEmpty() == true) {
                        viewModel.getPokemon(null)
                    }
                    return true
                }
            })
        }
    }

}