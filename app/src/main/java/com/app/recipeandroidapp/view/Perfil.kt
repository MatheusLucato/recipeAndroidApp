package com.app.recipeandroidapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.recipeandroidapp.R

class Perfil : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_perfil)
        supportActionBar?.hide() // Melhor usar o operador safe call
    }
}
