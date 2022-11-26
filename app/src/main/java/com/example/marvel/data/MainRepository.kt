package com.example.marvel.data

import android.content.Context
import com.example.marvel.models.Movies

class MainRepository {

    companion object {
        suspend fun fetchData(context: Context) : ArrayList<Movies> {
                return APIService.fetchData(context)
        }
        fun getMovies (context: Context, user: String, search: String) : ArrayList<Movies> {
            return APIService.getMovies(context, user, search)
        }
    }

   // suspend fun fetchData(context: Context): ArrayList<Movies> {
    //    return APIService.fetchData(context)
   // }

}