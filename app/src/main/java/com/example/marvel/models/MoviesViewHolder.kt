package com.example.marvel.models

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.R

class MoviesViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
    val name : TextView = itemView.findViewById(R.id.lblName)
    val thumbnail : ImageView = itemView.findViewById(R.id.imageView)
}