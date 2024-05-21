package com.app.recipeandroidapp.service

import com.app.recipeandroidapp.model.MealResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeService {
    @GET("random.php")
    fun getRandomMeals(): Call<MealResponse>

    @GET("search.php")
    fun searchMeals(@Query("s") query: String): Call<MealResponse>

}