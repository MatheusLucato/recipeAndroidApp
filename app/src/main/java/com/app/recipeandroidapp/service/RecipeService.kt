package com.app.recipeandroidapp.service

import com.app.recipeandroidapp.model.MealResponse
import retrofit2.Call
import retrofit2.http.GET
interface RecipeService {
    @GET("list.php?c=list")
    fun getAllCategories(): Call<MealResponse>

}