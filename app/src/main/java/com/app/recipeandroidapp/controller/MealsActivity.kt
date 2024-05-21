package com.app.recipeandroidapp.controller

import MealViewModel
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.recipeandroidapp.R

class MealsActivity : AppCompatActivity() {
    private val viewModel: MealViewModel by viewModels()
    private lateinit var mealAdapter: MealAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchBar: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meals)

        setupRecyclerView()
        setupViewModel()
        setupSearch()
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view_meals)
        mealAdapter = MealAdapter(emptyList())
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
                }
                else{
                    viewModel.loadRandomMeals()
                }
                true
            } else {
                false
            }
        }
    }
}
