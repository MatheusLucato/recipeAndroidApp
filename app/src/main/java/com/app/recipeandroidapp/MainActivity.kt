package com.app.recipeandroidapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.recipeandroidapp.controller.RecipeController
import com.app.recipeandroidapp.model.MealResponse
import com.app.recipeandroidapp.ui.theme.RecipeAndroidAppTheme
import com.app.recipeandroidapp.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception


class MainActivity : ComponentActivity() {

    private fun fetchData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Faz a chamada para obter as categorias
                val mealResponse = RecipeController.getAllCategories()

                // Processa a resposta se for bem-sucedida
                mealResponse?.meals?.forEach { meal ->
                    Log.i("CHECK_RESPONSE", "Category: ${meal.strCategory}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchData()
        setContent {
            RecipeAndroidAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RecipeAndroidAppTheme {
        Greeting("Android")
    }
}