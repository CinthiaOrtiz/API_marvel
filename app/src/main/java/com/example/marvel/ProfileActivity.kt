package com.example.marvel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import com.example.marvel.databinding.ActivityProfileBinding
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {

    //view binding
    private lateinit var binding: ActivityProfileBinding

    // firebase auth
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var btnLogout : ImageButton

    // google logout
    lateinit var mGoogleSignInClient : GoogleSignInClient
    private lateinit var tvUser : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        // handle click -> logout user
        btnLogout = findViewById(R.id.btnLogout)
        btnLogout.setOnClickListener {
            mGoogleSignInClient.signOut().addOnCompleteListener {
                firebaseAuth.signOut()
                checkUser()
            }
        }
    }


        private fun splitName(user: String): String {
            return user.split(Regex(" "))[0]
        }

        private fun checkUser() {
            // get current user
            val firebaseUser = firebaseAuth.currentUser
            if (firebaseUser == null) {
                // user not logged in
//            Log.d("Login", "Usuario no registrado")
                startActivity(Intent(this@ProfileActivity, MainActivity::class.java))
                finish()
            }
            else {
                // user logueado
                // get user email
                val email = firebaseUser.email
                // get user
                var user = firebaseUser.displayName
                user = splitName(user!!)
                // set email
                tvUser = findViewById(R.id.tvUser)
                tvUser.text = user;
            }
        }






        /*val btnBack : Button = findViewById(R.id.buttonBack)

        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        //handle click, logout user
        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }
        btnBack.setOnClickListener{
            startActivity(Intent(this@ProfileActivity, Home::class.java))
        }*/
    }
    /*
    private fun checkUser(){
        //get current user
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null){
            //user not logged in
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        else {
            //user logged in
            //get user info
            val email = firebaseUser.email
            //set email
            binding.emailTv.text = email
        }

    }*/