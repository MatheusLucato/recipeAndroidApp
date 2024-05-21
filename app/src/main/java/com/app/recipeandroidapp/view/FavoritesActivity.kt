package com.app.recipeandroidapp.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.recipeandroidapp.controller.DescribeMealActivity
import com.app.recipeandroidapp.controller.MealAdapter
import com.app.recipeandroidapp.databinding.ActivityFavoritesBinding
import com.app.recipeandroidapp.model.Meal
import com.app.recipeandroidapp.viewmodel.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesActivity : AppCompatActivity(), MealAdapter.OnItemClickListener {

    private lateinit var binding: ActivityFavoritesBinding
    private val viewModel: FavoritesViewModel by viewModels()
    private lateinit var mealAdapter: MealAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        mealAdapter = MealAdapter(emptyList(), this, true)
        binding.recyclerViewFavorites.apply {
            adapter = mealAdapter
            layoutManager = LinearLayoutManager(this@FavoritesActivity)
        }
    }

    private fun observeViewModel() {
        viewModel.favoritesLiveData.observe(this, { meals ->
            mealAdapter.updateData(meals)
        })
        viewModel.loadFavorites()
    }

    override fun onItemClick(meal: Meal) {
        val intent = Intent(this, DescribeMealActivity::class.java).apply {
            putExtra("meal_name", meal.strMeal)
            putExtra("meal_category", meal.strCategory)
            putExtra("meal_area", meal.strArea)
            putExtra("meal_instructions", meal.strInstructions)
            putExtra("meal_thumb", meal.strMealThumb)
            putExtra("meal_id", meal.idMeal)
            putExtra("meal_youtube", meal.strYoutube)
        }
        startActivity(intent)
    }

    override fun onDeleteClick(meal: Meal) {
        GlobalScope.launch(Dispatchers.IO) {
            viewModel.deleteMeal(meal)
            viewModel.loadFavorites()
        }
    }
}
