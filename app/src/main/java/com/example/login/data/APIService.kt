package com.example.login.data

import android.content.Context
import android.util.Log
import com.example.login.models.Movies
import kotlinx.coroutines.delay
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIService {
    companion object {
        const val BASE_URL = "https://gateway.marvel.com:443/"
        val hash = "51a8c6188fb4776c3ec761906147d9bc"
        val apiKey = "497b8c099db13df2475cef64e714bc4b"
        val ts = "1"

        val TAG = "Favourite"

        suspend fun fetchData(context: Context): ArrayList<Movies> {
            Log.d("API-DEMO", "Call to API Started")

            val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val api = retrofit.create(MoviesAPI::class.java)

            var result = api.getMovies().execute()

            Log.d("API-DEMO", "Call to API Finished")

            return if (result.isSuccessful) {
                Log.d("MOVIES", result.body()!!.data.results.size.toString())
                return result.body()!!.data.results
            } else {
                Log.e("apidemo", "Error al comunicar con la API")
                ArrayList<Movies>()
            }
        }
    }
}
