package com.example.marvel.models


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.marvel.MainActivity
import com.example.marvel.R

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        Log.d("Splash", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Splash", "onStart")

        //var prefs = getSharedPreferences("ar.edu.uade.debugdemo.sharedpref", Context.MODE_PRIVATE)
        //prefs.edit().putString("userName", "ngladkoff").apply()


        Handler(Looper.getMainLooper()).postDelayed({
            Log.d("Splash", "postDelayed")
            var intent = Intent(this, MainActivity::class.java)
            intent.putExtra("ID", 123456)
            startActivity(intent)
            finish()
        }, 4000)
        Log.d("Splash", "Post Handler")
    }


    override fun onPause() {
        super.onPause()
        Log.d("Splash", "función onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Splash", "función onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Splash", "función onDestroy")
    }
}