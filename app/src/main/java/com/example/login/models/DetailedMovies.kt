package com.example.login.models

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.login.R

class DetailedMovies : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moviesdetailed)

        val movies = intent.getParcelableExtra<Movies>("movies")
        Log.d("Imagen!!!!!!!!", "cualquier cosa")
        if (movies != null) {
            Log.d("Imagen!!!!!!!!", "cualquier cosa 2")

            val name : TextView = findViewById(R.id.textTituloDescrip)
            val imageView : ImageView = findViewById(R.id.imageViewDescripcion)
            Log.d("Imagen", movies.name!!)

            name.text = movies.name

            Glide.with(this)
                .load(movies.thumbnail)
                .placeholder(com.google.android.material.R.drawable.ic_clock_black_24dp)
                .centerCrop()
                .into(imageView)

        }
        /* LOS DATOS LLEGAN PERO NO SE MUESTRAN HAY QUE VER EL HOLDER Y EL ADAPTER*/
    }

}