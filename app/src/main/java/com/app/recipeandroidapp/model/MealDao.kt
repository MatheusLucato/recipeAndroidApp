package com.app.recipeandroidapp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: Meal)

    @Query("SELECT * FROM meals WHERE idMeal = :idMeal")
    suspend fun getMealById(idMeal: String): Meal?

    @Query("SELECT * FROM meals")
    suspend fun getAllMeals(): List<Meal>

    @Delete
    suspend fun deleteMeal(meal: Meal)
}
