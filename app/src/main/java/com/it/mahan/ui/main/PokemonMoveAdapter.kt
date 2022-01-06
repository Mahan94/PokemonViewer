package com.it.mahan.ui.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.it.mahan.R

class PokemonMoveAdapter(private val context: Context, private val items: List<String>) :
    RecyclerView.Adapter<PokemonMoveAdapter.MoveViewHolder>() {

    class MoveViewHolder(private val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: String) {
            itemView.findViewById<TextView>(R.id.tvMoveName).text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoveViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_move, parent, false)
        return MoveViewHolder(context, view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MoveViewHolder, position: Int) {
        holder.bind(items[position])
    }
}