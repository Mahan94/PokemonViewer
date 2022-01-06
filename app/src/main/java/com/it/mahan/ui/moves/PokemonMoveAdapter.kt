package com.it.mahan.ui.moves

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.it.mahan.R
import com.it.mahan.databinding.ListItemMoveBinding

class PokemonMoveAdapter(private val context: Context, private val items: ArrayList<String>) :
    RecyclerView.Adapter<PokemonMoveAdapter.MoveViewHolder>() {

    class MoveViewHolder(
        private val binding: ListItemMoveBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {
            binding.apply {
                tvMoveName.text = item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoveViewHolder {
        return MoveViewHolder(
            ListItemMoveBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MoveViewHolder, position: Int) {
        holder.bind(items[position])
    }
}