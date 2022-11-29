package com.example.marvel.models

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.MainActivity
import com.example.marvel.R
import com.example.marvel.data.MainRepository
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class Home : AppCompatActivity() {

    private val coroutineContext: CoroutineContext = newSingleThreadContext("uadeedemo")
    private val scope = CoroutineScope(coroutineContext)
    private val moviesQueryContext : CoroutineContext = newSingleThreadContext("query")
    private val  scopeQuery = CoroutineScope(moviesQueryContext)


    private lateinit var rvMovies : RecyclerView
    private var movies = ArrayList<Movies>()
    private lateinit var adapter : MoviesAdapter
    private val progessDialog by lazy { CustomProgressDialog(this) }
    private lateinit var tvUser : TextView
    private lateinit var searchInput: EditText



    // firebase auth
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var btnLogout : ImageButton

    // google logout
    lateinit var mGoogleSignInClient : GoogleSignInClient



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()

       // val btnExit : Button = findViewById(R.id.btnExit)


        rvMovies = findViewById<RecyclerView>(R.id.rvMovies)
        rvMovies.layoutManager = LinearLayoutManager(this)
        adapter = MoviesAdapter(movies, this)
        rvMovies.adapter = adapter

        /*btnExit.setOnClickListener{
            startActivity(Intent(this@Home, ProfileActivity::class.java))
        }*/
        onClickDetails()
        
        // handle click -> logout user
        btnLogout = findViewById(R.id.btnLogout)
        btnLogout.setOnClickListener {
            mGoogleSignInClient.signOut().addOnCompleteListener {
                firebaseAuth.signOut()
                checkUser()
            }
        }

        // Configure the Google Sign OUT
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

    }

    private fun checkUser() {
        // get current user
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null) {
            // user not logged in
//            Log.d("Login", "Usuario no registrado")
            startActivity(Intent(this@Home, MainActivity::class.java))
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
    private fun splitName(user: String): String {
        return user.split(Regex(" "))[0]
    }

    private fun onClickDetails() {

        adapter.onItemClick = { movies : Movies ->
            scope.launch {
                /*faltaria filtro por id? */
                showMoviesDetails(movies)
            }
        }
    }

    private fun showMoviesDetails(movies: Movies) {

        adapter.onItemClick = {
            val intent =   Intent(this@Home, DetailedMovies::class.java)
            intent.putExtra("movies", it)
            //intent.putExtra("thumbnail", movies.thumbnail)
            //intent.putExtra("description", movies.description)
            //intent.putExtra("modified", movies.modified)


            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val user = firebaseAuth.currentUser!!.email

        progessDialog.start("Recuperando datos ... ")
        scope.launch {
            movies = MainRepository.fetchData(this@Home)

            withContext(Dispatchers.Main) {
                Log.d("QTYITEMS", movies.size.toString())
                adapter.Update(movies)
                progessDialog.stop()
            }
        }



        var searchInput = findViewById<TextInputEditText>(R.id.inputFiltro)
        // filtro
        searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(search: CharSequence?, start: Int, before: Int, count: Int) {
                // hago una consulta a la API con lo que se busca -> Trae mas cantidad de recetas
                val ejercicios = buscarEjerciciosPorNombre(movies,search);

                updateRecipesQuery(ejercicios)
            }

            private fun buscarEjerciciosPorNombre( ejercicios: ArrayList<Movies>, search: CharSequence?): ArrayList<Movies> {
                val ejerciciosEncontrados = ArrayList<Movies>();

                for(ejercicio in ejercicios){
                    if  (ejercicio.name?.uppercase()?.contains(search.toString().uppercase()) == true) {
                        ejerciciosEncontrados.add(ejercicio)
                    }
                }
                return ejerciciosEncontrados
            }

            private fun updateRecipesQuery(ejercicios: ArrayList<Movies>) {
                scope.launch {
                    withContext(Dispatchers.Main) {
                        adapter.Update(ejercicios)
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

    }
}