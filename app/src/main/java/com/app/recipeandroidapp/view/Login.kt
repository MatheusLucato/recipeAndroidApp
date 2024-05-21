package com.app.recipeandroidapp.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.recipeandroidapp.R
import com.app.recipeandroidapp.controller.MealsActivity
import com.app.recipeandroidapp.model.user.UserRepository
import com.app.recipeandroidapp.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.*

@AndroidEntryPoint
class Login : AppCompatActivity() {

    private lateinit var textTelaCadastro: TextView
    private lateinit var userEmail: EditText
    private lateinit var userPassword: EditText
    private lateinit var loginButton: Button

    private val viewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        iniciarComponente()
        supportActionBar?.hide()

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
            GlobalScope.launch(Dispatchers.IO) {
                val user = userRepository.dao.findUserByUsername(email)

                withContext(Dispatchers.Main) {
                    if (user != null) {
                        if (user.password == password) {
                            Toast.makeText(this@Login, "Logado com sucesso", Toast.LENGTH_LONG).show()
                            val intent = Intent(this@Login, MealsActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@Login, "Senha incorreta", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Toast.makeText(this@Login, "Usuario nao encontrado", Toast.LENGTH_LONG).show()
                    }
                }
            }
        } else {
            Toast.makeText(this, "Email ou senha não podem estar vazios", Toast.LENGTH_LONG).show()
        }
    }

    private fun iniciarComponente() {
        textTelaCadastro = findViewById(R.id.text_tela_cadastro)
        userEmail = findViewById(R.id.edit_email)
        userPassword = findViewById(R.id.edit_senha)
        loginButton = findViewById(R.id.bt_entrar)
    }
}
