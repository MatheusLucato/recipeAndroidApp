package com.app.recipeandroidapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.recipeandroidapp.model.Meal
import com.app.recipeandroidapp.model.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DescribeMealViewModel @Inject constructor(private val mealRepository: MealRepository) : ViewModel() {

    fun saveMeal(meal: Meal) {
        viewModelScope.launch(Dispatchers.IO) {
            mealRepository.insertMeal(meal)
        }
    }
}
