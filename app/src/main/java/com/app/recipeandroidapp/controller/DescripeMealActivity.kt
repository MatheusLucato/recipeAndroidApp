package com.app.recipeandroidapp.controller

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.recipeandroidapp.R
import com.app.recipeandroidapp.databinding.ActivityDescribeMealBinding
import com.app.recipeandroidapp.model.Meal
import com.app.recipeandroidapp.viewmodel.DescribeMealViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DescribeMealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDescribeMealBinding
    private val viewModel: DescribeMealViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescribeMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mealName = intent.getStringExtra("meal_name")
        val mealCategory = intent.getStringExtra("meal_category")
        val mealArea = intent.getStringExtra("meal_area")
        val mealInstructions = intent.getStringExtra("meal_instructions")
        val mealThumb = intent.getStringExtra("meal_thumb")
        val mealId = intent.getStringExtra("meal_id")
        val mealYoutube = intent.getStringExtra("meal_youtube")

        val meal = Meal(
            idMeal = mealId ?: "",
            strMeal = mealName ?: "",
            strDrinkAlternate = null,
            strCategory = mealCategory ?: "",
            strArea = mealArea ?: "",
            strInstructions = mealInstructions ?: "",
            strMealThumb = mealThumb ?: "",
            strTags = null,
            strYoutube = mealYoutube ?: ""
        )

        binding.tvName.text = mealName
        binding.tcCategory.text = mealCategory
        binding.ingredientes.text = mealInstructions

        Picasso.get().load(mealThumb).into(binding.imgRecipe)

        binding.imgFvrt.setOnClickListener {
            viewModel.saveMeal(meal)
            Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_SHORT).show()
            binding.imgFvrt.setImageResource(R.drawable.baseline_favorite_24)
        }
    }
}
