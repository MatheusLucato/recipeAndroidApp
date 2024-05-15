package com.app.recipeandroidapp.controller

import com.app.recipeandroidapp.modal.Recipe
import retrofit2.Call
import retrofit2.http.GET
interface RecipeController {
    @GET("AllRecipes")
    fun getAllRecipes(): Call<List<Recipe>>

}