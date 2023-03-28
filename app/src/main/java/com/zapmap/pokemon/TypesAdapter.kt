package com.zapmap.pokemon

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TypesAdapter : RecyclerView.Adapter<TypesAdapter.ViewHolder>() {

    private val items = mutableListOf<String>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(list: List<String>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_type, parent, false)
        return ViewHolder(itemView)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewType: TextView = view.findViewById(R.id.textViewType)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].apply {
            holder.textViewType.text = this
        }
    }

    override fun getItemCount(): Int = items.size
}