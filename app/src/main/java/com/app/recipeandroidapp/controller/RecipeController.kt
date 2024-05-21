package com.app.recipeandroidapp.controller

import com.app.recipeandroidapp.model.MealResponse
import com.app.recipeandroidapp.service.RecipeService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.app.recipeandroidapp.util.Constants
import com.google.gson.JsonArray
import retrofit2.Call

object RecipeController {

    val recipeService: RecipeService

    init {
        recipeService = Constants.api.create(RecipeService::class.java)
    }

    fun getAllCategories(): MealResponse? {

        return recipeService
            .getAllCategories()
            .execute()
            .body()
    }

}