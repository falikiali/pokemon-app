package com.falikiali.pokemonapp.presentation.pokemon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.falikiali.pokemonapp.databinding.ListItemPokemonBinding
import com.falikiali.pokemonapp.domain.model.Pokemon

class PokemonAdapter(val onItemClick: (Pokemon) -> Unit): ListAdapter<Pokemon, PokemonAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ListItemPokemonBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Pokemon) {
            itemView.setOnClickListener { onItemClick(data) }
            binding.tvName.text = data.name
        }
    }

}