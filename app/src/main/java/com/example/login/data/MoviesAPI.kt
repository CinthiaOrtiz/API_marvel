package com.example.login.data

import com.example.login.models.Movies
import com.example.login.models.ResponseDeLaResponse
import com.example.login.models.ResponseMovies
import com.google.android.gms.common.api.internal.ApiKey
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HEAD
import retrofit2.http.Query

interface MoviesAPI {

    @GET("/v1/public/characters?apikey=497b8c099db13df2475cef64e714bc4b&hash=51a8c6188fb4776c3ec761906147d9bc&ts=1")
    fun getMovies()
    : Call<ResponseDeLaResponse>


/* kay codigo
@GET("/recipes/complexSearch")
fun getReipes (@HEAD("x-api-key") apiKey: String),
            @Query("number") number : Int) : Call<ReponseAPI>;

@GET("/recipes/{id}/informacion/")
fun getRecipebyID (@Path("id") id : Int,
                @Header ("x-api-key") apiKey: String)
    : Call<RecipeDetailModel>

    hash: String("hash"): String,,
        ts: ("ts"): String),

*/

}