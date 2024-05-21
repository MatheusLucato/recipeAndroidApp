package com.app.recipeandroidapp.model

data class Meal(
    val idMeal: String,
    val strMeal: String,
    val strDrinkAlternate: String?,
    val strCategory: String,
    val strArea: String,
    val strInstructions: String,
    val strMealThumb: String,
    val strTags: String?,
    val strYoutube: String,
    val ingredients: List<Pair<String, String?>>
)

data class MealResponse(
    val meals: List<Meal>
)