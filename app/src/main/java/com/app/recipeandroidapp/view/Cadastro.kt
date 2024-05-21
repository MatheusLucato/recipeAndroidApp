package com.app.recipeandroidapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import com.app.recipeandroidapp.R
import com.app.recipeandroidapp.viewmodel.CadastroViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Cadastro : AppCompatActivity() {
    private lateinit var nomeEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var senhaEditText: EditText
    private lateinit var cadastrarButton: Button

    private val viewModel: CadastroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
        supportActionBar?.hide()
        iniciarComponentes()
        setupListeners()
    }

    private fun iniciarComponentes() {
        nomeEditText = findViewById(R.id.edit_nome)
        emailEditText = findViewById(R.id.edit_email)
        senhaEditText = findViewById(R.id.edit_senha)
        cadastrarButton = findViewById(R.id.bt_cadastrar)
    }

    private fun setupListeners() {
        cadastrarButton.setOnClickListener {
            realizarCadastro()
        }
    }

    private fun realizarCadastro() {
        val nome = nomeEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val senha = senhaEditText.text.toString().trim()

        viewModel.cadastrarUsuario(nome, email, senha) { userId ->
            runOnUiThread {
                if (userId != -1L) {
                    Toast.makeText(this@Cadastro, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@Cadastro, Login::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@Cadastro, "Falha ao cadastrar. Tente novamente.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
