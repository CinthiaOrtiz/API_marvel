package com.example.login.models

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.login.R

class MoviesDetailedAdapter(var items: MutableList<Movies>, context: Context) : RecyclerView.Adapter<MoviesDetailedViewHolder>() {

    private val context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesDetailedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_moviesdetailed, parent, false)
        return MoviesDetailedViewHolder(view)

    }

    override fun onBindViewHolder(holder: MoviesDetailedViewHolder, position: Int) {
        holder.name.text = items[position].name

        var imagenUrl = items[position].thumbnail.path + "." + items[position].thumbnail.extension

        Glide.with(context)
            .load(imagenUrl)
            .placeholder(com.google.android.material.R.drawable.ic_clock_black_24dp)
            .centerCrop()
            .into(holder.thumbnail)


    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun Update(items_new: MutableList<Movies>){
        items = items_new
        this.notifyDataSetChanged()
    }
}