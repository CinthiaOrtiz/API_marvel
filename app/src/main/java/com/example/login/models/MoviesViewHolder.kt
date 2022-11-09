package com.example.login.models

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R

class MoviesViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
    val name : TextView = itemView.findViewById(R.id.lblName)
    val thumbnail : ImageView = itemView.findViewById(R.id.imageView)
}