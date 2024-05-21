package com.app.recipeandroidapp.controller

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.recipeandroidapp.R
import com.app.recipeandroidapp.model.Meal
import com.app.recipeandroidapp.view.FavoritesActivity
import com.app.recipeandroidapp.viewmodel.MealViewModel

class MealsActivity : AppCompatActivity(), MealAdapter.OnItemClickListener {
    private val viewModel: MealViewModel by viewModels()
    private lateinit var mealAdapter: MealAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchBar: EditText
    private lateinit var consultarFavoritosButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meals)

        setupRecyclerView()
        setupViewModel()
        setupSearch()
        setupConsultarFavoritosButton()
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view_meals)
        mealAdapter = MealAdapter(emptyList(), this)
        recyclerView.adapter = mealAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupViewModel() {
        viewModel.mealsLiveData.observe(this, { meals ->
            mealAdapter.updateData(meals)
        })
        viewModel.loadRandomMeals()
    }

    private fun setupSearch() {
        searchBar = findViewById(R.id.search_bar)

        searchBar.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = searchBar.text.toString()
                if (query.isNotEmpty()) {
                    viewModel.searchMeals(query)
                } else {
                    viewModel.loadRandomMeals()
                }
                true
            } else {
                false
            }
        }
    }

    private fun setupConsultarFavoritosButton() {
        consultarFavoritosButton = findViewById(R.id.consultar_favoritos_button)
        consultarFavoritosButton.setOnClickListener {
            val intent = Intent(this, FavoritesActivity::class.java)
            startActivity(intent)
        }
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
        // Implementação vazia ou lógica relevante se necessário
    }
}
