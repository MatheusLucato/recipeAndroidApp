package com.app.recipeandroidapp.components

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.app.recipeandroidapp.R

class Signup : AppCompatActivity() {
    // Declarar a variável fora do método para que seja acessível em toda a classe
    private lateinit var textTelaCadastro: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login) // setContentView deve ser chamado primeiro
        iniciarComponente()
        supportActionBar?.hide() // Melhor usar o operador safe call

        textTelaCadastro.setOnClickListener {
            val intent = Intent(this@Signup, cadastro::class.java)
            startActivity(intent)
        }
    }

    private fun iniciarComponente() {
        textTelaCadastro = findViewById(R.id.text_tela_cadastro) // Inicializar a variável
    }
}
