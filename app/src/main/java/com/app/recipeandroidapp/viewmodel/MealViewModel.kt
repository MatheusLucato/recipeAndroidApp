package com.app.recipeandroidapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.recipeandroidapp.controller.RecipeController
import com.app.recipeandroidapp.model.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MealViewModel : ViewModel() {
    private val _mealsLiveData = MutableLiveData<List<Meal>>()
    val mealsLiveData: LiveData<List<Meal>> = _mealsLiveData

    fun loadRandomMeals() {
        viewModelScope.launch {
            try {
                val randomMealResponse = withContext(Dispatchers.IO) {
                    RecipeController.getRandomMeals()
                }

                val meals = randomMealResponse
                    .filterNotNull()
                    .flatMap { it.meals }

                _mealsLiveData.postValue(meals)
            } catch (e: Exception) {
                Log.e("REQUEST ERROR", "Failed to load meals", e)
            }
        }
    }

    fun searchMeals(query: String) {
        viewModelScope.launch {
            try {
                val searchResults = withContext(Dispatchers.IO) {
                    RecipeController.searchMeals(query)
                }
                _mealsLiveData.postValue(searchResults)
            } catch (e: Exception) {
                Log.e("SEARCH ERROR", "Failed to search meals", e)
            }
        }
    }
}