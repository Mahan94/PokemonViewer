package com.it.mahan.ui.stats

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.it.mahan.databinding.ListItemStatBinding
import com.it.mahan.model.PokemonStat

class PokemonStatAdapter(private val context: Context, private val items: ArrayList<PokemonStat>) :
    RecyclerView.Adapter<PokemonStatAdapter.StatViewHolder>() {

    class StatViewHolder(
        private val binding: ListItemStatBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PokemonStat) {
            binding.apply {
                tvStatName.text = item.statDetail?.name
                tvStatBase.text = item.baseStat.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatViewHolder {
        return StatViewHolder(
            ListItemStatBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: StatViewHolder, position: Int) {
        val pokemonStat = items[position]
        holder.bind(pokemonStat)
    }

    override fun getItemCount(): Int = items.size

}