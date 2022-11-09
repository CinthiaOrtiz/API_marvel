package com.example.login.data

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.login.models.Movies

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