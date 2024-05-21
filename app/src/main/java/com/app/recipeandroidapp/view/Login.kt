package com.app.recipeandroidapp.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.recipeandroidapp.R
import com.app.recipeandroidapp.database.UserDatabase

class Login : AppCompatActivity() {
    // Declarar a variável fora do método para que seja acessível em toda a classe
    private lateinit var textTelaCadastro: TextView
    private lateinit var userEmail: EditText
    private lateinit var userPassword: EditText
    private lateinit var loginButton: Button
    private lateinit var userDatabase: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login) // setContentView deve ser chamado primeiro
        iniciarComponente()
        supportActionBar?.hide() // Melhor usar o operador safe call

        textTelaCadastro.setOnClickListener {
            val intent = Intent(this@Login, Cadastro::class.java)
            startActivity(intent)
        }
        loginButton.setOnClickListener {
            realizarLogin()
        }
    }

    private fun realizarLogin() {
        val email = userEmail.text.toString()
        val password = userPassword.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            val user = userDatabase.findUserByEmail(email)
            if (user != null && user.moveToFirst()) {
                val passwordIndex = user.getColumnIndex("password")
                if (passwordIndex != -1) {
                    val storedPassword = user.getString(passwordIndex)
                    if (storedPassword == password) {
                        Toast.makeText(this, "Logado com sucesso", Toast.LENGTH_LONG).show()
                        val intent = Intent(this@Login, MealsActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Senha incorreta", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this, "Database error: 'password' column not found", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Usuario nao encontrado", Toast.LENGTH_LONG).show()
            }
            user?.close()
        } else {
            Toast.makeText(this, "Email ou senhas nao podem estar vazias", Toast.LENGTH_LONG).show()
        }
    }

    private fun iniciarComponente() {
        textTelaCadastro = findViewById(R.id.text_tela_cadastro) // Inicializar a variável
        userEmail = findViewById(R.id.edit_email)
        userPassword = findViewById(R.id.edit_senha)
        loginButton = findViewById(R.id.bt_entrar)
        userDatabase = UserDatabase(this)
    }
}
