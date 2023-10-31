package com.falikiali.pokemonapp.presentation.pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.falikiali.pokemonapp.R
import com.falikiali.pokemonapp.databinding.ActivityPokemonBinding
import com.falikiali.pokemonapp.presentation.detail.DetailPokemonActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonActivity : AppCompatActivity() {

    private var querySearch: String? = null

    private val binding: ActivityPokemonBinding by lazy { ActivityPokemonBinding.inflate(layoutInflater) }
    private val viewModel: PokemonViewModel by viewModels()

    private lateinit var pokemonAdapter: PokemonAdapter
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var rbAz: RadioButton
    private lateinit var rbZa: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpActionBar()
        viewModel.getPokemon(null)
        initRv()
        observeViewModel()
        searchPokemon()
        sortData()
    }

    private fun setUpActionBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Pokemon"
        }
    }

    private fun initRv() {
        pokemonAdapter = PokemonAdapter(onItemClick = {
            val intent = Intent(this@PokemonActivity, DetailPokemonActivity::class.java)
            intent.putExtra(DetailPokemonActivity.EXTRA_NAME, it.name)
            startActivity(intent)
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
                    querySearch = p0
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    if (p0?.isEmpty() == true) {
                        viewModel.getPokemon(null)
                        querySearch = null
                    }
                    return true
                }
            })
        }
    }

    private fun sortData() {
        binding.fabSort.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.bottom_sheet_filter, null)

            rbAz = dialogView.findViewById(R.id.rb_az)
            rbZa = dialogView.findViewById(R.id.rb_za)

            bottomSheetDialog = BottomSheetDialog(this@PokemonActivity)
            bottomSheetDialog.setContentView(dialogView)
            bottomSheetDialog.show()
            
            rbAz.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    if (querySearch != null) {
                        viewModel.searchPokemon(querySearch!!, 1)
                    } else {
                        viewModel.getPokemon(1)
                    }
                }
            }

            rbZa.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    if (querySearch != null) {
                        viewModel.searchPokemon(querySearch!!, 2)
                    } else {
                        viewModel.getPokemon(2)
                    }
                }
            }
        }
    }

}