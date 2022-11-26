package com.example.marvel.models

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.R

class MoviesDetailedViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val name : TextView = itemView.findViewById(R.id.textTituloDescrip)
    val thumbnail : ImageView = itemView.findViewById(R.id.imageViewDescripcion)
    val description : TextView = itemView.findViewById(R.id.textViewDescripcion)
    val modified : TextView = itemView.findViewById(R.id.textDescripcionModifiled)

}