package com.app.recipeandroidapp.controller

import com.app.recipeandroidapp.model.Meal
import com.app.recipeandroidapp.model.MealResponse
import com.app.recipeandroidapp.service.RecipeService
import com.app.recipeandroidapp.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import kotlinx.coroutines.awaitAll

object RecipeController {

    val recipeService: RecipeService

    init {
        recipeService = Constants.api.create(RecipeService::class.java)
    }

    suspend fun getRandomMeals(): List<MealResponse?> {
        return withContext(Dispatchers.IO) {
            (0..9).map { index ->
                async {
                    recipeService.getRandomMeals().execute().body()
                }
            }.awaitAll()
        }
    }

    suspend fun searchMeals(query: String): List<Meal> {
        return try {
            val response = recipeService.searchMeals(query).execute()
            if (response.isSuccessful && response.body() != null) {
                response.body()!!.meals
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

}