package com.zapmap.pokemon

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PokemonAdapter(private val callback: (RemotePokemonItem) -> Unit) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    val items = mutableListOf<RemotePokemonItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(list: List<RemotePokemonItem>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_pokemon, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        items[position].let { pokemonItem ->
            holder.textViewName.apply {
                text = pokemonItem.name.capitalize()
                setOnClickListener { callback(pokemonItem) }
            }
        }

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView = view.findViewById(R.id.textViewName)
    }

    override fun getItemCount(): Int = items.size
}