package com.app.recipeandroidapp.model

import javax.inject.Inject

class MealRepository @Inject constructor(private val mealDao: MealDao) {

    suspend fun insertMeal(meal: Meal) {
        mealDao.insertMeal(meal)
    }

    suspend fun getMealById(idMeal: String): Meal? {
        return mealDao.getMealById(idMeal)
    }

    suspend fun getAllMeals(): List<Meal> {
        return mealDao.getAllMeals()
    }

    suspend fun deleteMeal(meal: Meal) {
        mealDao.deleteMeal(meal)
    }
}
