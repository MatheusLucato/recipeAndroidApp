package com.app.recipeandroidapp.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.recipeandroidapp.R

class cadastro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_cadastro)
    }
}