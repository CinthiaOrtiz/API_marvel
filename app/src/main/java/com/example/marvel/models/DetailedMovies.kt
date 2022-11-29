package com.example.marvel.models

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.marvel.Home
import com.example.marvel.R

class DetailedMovies : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moviesdetailed)
        supportActionBar?.hide()

        val btnBack : Button = findViewById(R.id.buttonBack)

        val movies = intent.getParcelableExtra<Movies>("movies")

        if (movies != null) {
            Log.d("Imagen!!!!!!!!", "cualquier cosa 2")

            val name : TextView = findViewById(R.id.textTituloDescrip)
            val imageView : ImageView = findViewById(R.id.imageViewDescripcion)
            val description : TextView = findViewById(R.id.textViewDescripcion)
            val modified : TextView = findViewById(R.id.textDescripcionModifiled)

            Log.d("Imagen", movies.name!!)
            Log.d("Imagen", movies.description!!)
            Log.d("Imagen", movies.modified!!.toString())
            Log.d("Imagen", movies.thumbnail!!.toString())



            name.text = movies.name
            description.text = movies.description
            modified.text = movies.modified.toString()

            Log.d("Imagen", movies.thumbnail!!.toString())

            Glide.with(this)
                .load(movies.thumbnail.path + "." + movies.thumbnail.extension )
                .placeholder(com.google.android.material.R.drawable.ic_clock_black_24dp)
                .centerCrop()
                .into(imageView)
            Log.d("Imagen", movies.thumbnail!!.toString())



        }
        btnBack.setOnClickListener{
            startActivity(Intent(this@DetailedMovies, Home::class.java))
        }

    }

}