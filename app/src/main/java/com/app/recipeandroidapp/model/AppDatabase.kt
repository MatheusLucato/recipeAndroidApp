package com.app.recipeandroidapp.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class, Meal::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun mealDao(): MealDao
}