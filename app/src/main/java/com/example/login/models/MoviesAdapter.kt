package com.example.login.models

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.login.R



class MoviesAdapter (var items: MutableList<Movies>, var context: Context)
    : RecyclerView.Adapter<MoviesViewHolder>() {

    var onItemClick : ((Movies) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies_item, parent,false )
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.name.text = items[position].name

        var imagenUrl = items[position].thumbnail.path + "." + items[position].thumbnail.extension
        Glide.with(context)
            .load(imagenUrl)
            .placeholder(com.google.android.material.R.drawable.ic_clock_black_24dp)
            .centerCrop()
            .into(holder.thumbnail)

        val item = items[position]
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(item)

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun Update(items_new: MutableList<Movies>) {
        items = items_new
        this.notifyDataSetChanged()
    }


}