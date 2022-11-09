package com.example.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login.data.MainRepository
import com.example.login.databinding.ActivityMainBinding
import com.example.login.models.CustomProgressDialog
import com.example.login.models.Home
import com.example.login.models.Movies
import com.example.login.models.MoviesAdapter
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {
    // APIsDemo universidades
    private val coroutineContext: CoroutineContext = newSingleThreadContext("main")
    private val scope = CoroutineScope(coroutineContext)

    private var movies = ArrayList<Movies>()
    private lateinit var adapter : MoviesAdapter

//    private lateinit var rvMovies : RecyclerView
    private val progressDialog by lazy { CustomProgressDialog(this) }



    //view binding
    private lateinit var binding: ActivityMainBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth

    //constants
    private companion object{
        private const val RC_SING_IN = 100
        private const val TAG = "GOOGLE_SING_IN_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "LLEGO?")

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       /* rvMovies = findViewById<RecyclerView>(R.id.rvMovies)
        rvMovies.layoutManager = LinearLayoutManager(this)
        adapter = MoviesAdapter(movies, this)
        rvMovies.adapter = adapter*/


        //configure the Google SingIn
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail() // we only ned email from foofle account
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        //Google SingIn Button, Click to begin Google SingIn
        binding.googleSingInBtn.setOnClickListener {
            //begin Google SingIn
            Log.d(TAG, "onCreate: begin Google SingIn")
            val intent = googleSignInClient.signInIntent
            startActivityForResult(intent, RC_SING_IN)
        }
    }

    private fun checkUser() {
        //check if user is logged in or not
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser != null){
            //user is already logges in
            //start profile activity
            //start profile activity
            startActivity(Intent(this@MainActivity, Home::class.java))
            finish()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //Result returned from launching the intent from GoogleSingInApi.getSignInIntent(...);
        if (requestCode == RC_SING_IN){
            Log.d(TAG,"onActivityResult: Google SingIn Intent result")
            val accountTask = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                //Google SingIn success, now auth with firebase
                val account = accountTask.getResult(ApiException::class.java)
                firebaseAuthWithGoogleAccount(account)
            }
            catch (e: Exception)  {
                //failed google SingIn
                Log.d(TAG, "onActivityResult: ${e.message}")
            }
        }
    }

    private fun firebaseAuthWithGoogleAccount(account: GoogleSignInAccount?) {
        Log.d(TAG, "firebaseAuthWithGoogleAccount: begin firebase auth with google account")

        val credential = GoogleAuthProvider.getCredential(account!!.idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener { authResult ->
                //login sucess
                Log.d(TAG, "firebaseAuthWithGoogleAccount: LoggedIn")

                //get loggedIn user
                val firebaseUser = firebaseAuth.currentUser
                //get user info
                val uid = firebaseUser!!.uid
                val email = firebaseUser!!.email

                Log.d(TAG, "firebaseAuthWithGoogleAccount: Uid: $uid")
                Log.d(TAG, "firebaseAuthWithGoogleAccount: Email: $email")


                //check if user is new or existing
                if (authResult.additionalUserInfo!!.isNewUser) {
                    //user is new - Account created
                    Log.d(TAG, "firebaseAuthWithGoogleAccount: Account created... \n$email")
                    Toast.makeText(
                        this@MainActivity,
                        "Account created... \n$email",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    //exising user -LoggedIn
                    Log.d(TAG, "firebaseAuthWithGoogleAccount: Existing user... \n$email")
                    Toast.makeText(this@MainActivity, "LoggedIn... \n$email", Toast.LENGTH_SHORT)
                        .show()
                }
                //start profile activity
                startActivity(Intent(this@MainActivity, ProfileActivity::class.java))
                finish()
            }
            .addOnFailureListener{ e ->
                //login failed
                Log.d(TAG, "firebaseAuthWithGoogleAccount: Loggin Failed due to ${e.message}")
                Toast.makeText(this@MainActivity, "Loggin Failed due to ${e.message}", Toast.LENGTH_SHORT).show()

            }
    }
    override fun onStart() {
        super.onStart()
        progressDialog.start("Recuperando Datos...")
        start(this)
    }

    fun start(context: Context){
        scope.launch{
            Log.d("API-DEMO", "recuperando")
            movies = MainRepository.fetchData(this@MainActivity)
            Log.d("API-DEMO", movies.size.toString())
            withContext(Dispatchers.Main) {
                adapter.Update(movies)
                progressDialog.stop()
            }
        }
    }



}










