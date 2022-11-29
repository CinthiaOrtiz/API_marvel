package com.example.marvel.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.marvel.Home
import com.example.marvel.R

class Favorite : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)
        supportActionBar?.hide()

        val btnBack : Button = findViewById(R.id.buttonBack)



        btnBack.setOnClickListener{
            startActivity(Intent(this@Favorite, Home::class.java))
        }

    }

}