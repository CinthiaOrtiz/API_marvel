package com.example.login.models

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.login.R

class DetailedMovies : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moviesdetailed)

        val movies = intent.getParcelableExtra<moviesParcelable>("movies")
        if (movies != null) {

            val name : TextView = findViewById(R.id.textTituloDescrip)
            val imageView : ImageView = findViewById(R.id.imageViewDescripcion)

            name.text = movies.name
            imageView.setImageResource(movies.thumbnail)


        }
    }

}