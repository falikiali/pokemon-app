package com.falikiali.pokemonapp.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.falikiali.pokemonapp.R
import com.falikiali.pokemonapp.databinding.ActivityDetailPokemonBinding
import com.falikiali.pokemonapp.utils.ResultState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPokemonActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "name"
    }

    private val binding: ActivityDetailPokemonBinding by lazy { ActivityDetailPokemonBinding.inflate(layoutInflater) }
    private val viewModel: DetailPokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpActionBar()
        getDetailPokemon()
        observeViewModel()
        actionBtnRetry()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setUpActionBar() {
        supportActionBar?.title = intent.getStringExtra(EXTRA_NAME)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getDetailPokemon() {
        val name = intent.getStringExtra(EXTRA_NAME)
        if (name != null) {
            viewModel.getDetailPokemon(name)
        }
    }

    private fun observeViewModel() {
        with(viewModel) {
            detailState.observe(this@DetailPokemonActivity) {
                binding.apply {
                    progressBar.isVisible = it is ResultState.Loading
                    btnRetry.isVisible = it is ResultState.Failed
                }

                if (it is ResultState.Success) {
                    binding.tvName.text = intent.getStringExtra(EXTRA_NAME)
                    binding.tvAbilities.text = it.data.abilities.joinToString(", ")

                    Glide.with(this@DetailPokemonActivity)
                        .load(it.data.frontDefault)
                        .into(binding.ivAvatar)
                }
            }
        }
    }

    private fun actionBtnRetry() {
        binding.btnRetry.setOnClickListener {
            getDetailPokemon()
        }
    }

}