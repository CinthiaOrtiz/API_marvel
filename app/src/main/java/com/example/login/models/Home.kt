package com.example.login.models

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.data.MainRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class Home : AppCompatActivity() {

    private val coroutineContext: CoroutineContext = newSingleThreadContext("uadeedemo")
    private val scope = CoroutineScope(coroutineContext)
    private lateinit var rvMovies : RecyclerView
    private var movies = ArrayList<Movies>()
    private lateinit var adapter : MoviesAdapter
    private val progessDialog by lazy { CustomProgressDialog(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        rvMovies = findViewById<RecyclerView>(R.id.rvMovies)
        rvMovies.layoutManager = LinearLayoutManager(this)
        adapter = MoviesAdapter(movies, this)
        rvMovies.adapter = adapter


        onClickDetails()

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
            intent.putExtra("name", movies.name)
            //intent.putExtra("thumbnail", movies.thumbnail)
            intent.putExtra("description", movies.description)
            intent.putExtra("modified", movies.modified)


            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        progessDialog.start("Recuperando datos ... ")
        scope.launch {
            //var jsonData = MainRepository.fetchData(this@MainActivity)
            //var university = Gson().fromJson(jsonData, University::class.java)
            //Log.d("apidemo", university.toString())
            //var universities = MainRepository.fetchData(this@MainActivity)
            //Log.d("apidemo", universities.size.toString())

            movies = MainRepository.fetchData(this@Home)


            withContext(Dispatchers.Main) {
                Log.d("QTYITEMS", movies.size.toString())
                adapter.Update(movies)
                progessDialog.stop()
            }

        }
    }
}