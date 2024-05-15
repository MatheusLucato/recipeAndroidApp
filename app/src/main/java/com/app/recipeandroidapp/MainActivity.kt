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
import com.app.recipeandroidapp.modal.Recipe
import com.app.recipeandroidapp.ui.theme.RecipeAndroidAppTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MainActivity : ComponentActivity() {

    private val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"


    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RecipeController::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        getAllRecipes()
    }

    private fun getAllRecipes() {
            api.getAllRecipes().enqueue(object : Callback<List<Recipe>>{
                override fun onResponse(
                    call: Call<List<Recipe>>,
                    response: Response<List<Recipe>>)
                {
                    if(response.isSuccessful){
                        response.body()?.let {
                            for (recipe in it) {
                                Log.i("CHECK_RESPONSE", "onResponse: ${recipe.toString()}" )
                            }
                        }
                    }
                }

                override fun onFailure(
                    call: Call<List<Recipe>>,
                    t: Throwable) {
                    Log.i("CHECK_RESPONSE", "onFailure: ${t.message}")
                }

            })

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