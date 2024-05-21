package com.app.recipeandroidapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.app.recipeandroidapp.view.Login


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, Login::class.java))

    }
}
