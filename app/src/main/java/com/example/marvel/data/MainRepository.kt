package com.example.marvel.data

import android.content.Context
import com.example.marvel.models.Movies

class MainRepository {

    companion object {
        suspend fun fetchData(context: Context) : ArrayList<Movies> {
                return APIService.fetchData(context)
        }
    }

   // suspend fun fetchData(context: Context): ArrayList<Movies> {
    //    return APIService.fetchData(context)
   // }

}