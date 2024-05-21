package com.app.recipeandroidapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.recipeandroidapp.model.Meal
import com.app.recipeandroidapp.model.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val mealRepository: MealRepository) : ViewModel() {

    private val _favoritesLiveData = MutableLiveData<List<Meal>>()
    val favoritesLiveData: LiveData<List<Meal>> get() = _favoritesLiveData

    fun loadFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            val favorites = mealRepository.getAllMeals()
            _favoritesLiveData.postValue(favorites)
        }
    }

    fun deleteMeal(meal: Meal) {
        viewModelScope.launch(Dispatchers.IO) {
            mealRepository.deleteMeal(meal)
            loadFavorites()
        }
    }
}
