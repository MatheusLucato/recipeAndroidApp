package com.app.recipeandroidapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.recipeandroidapp.model.User
import com.app.recipeandroidapp.model.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CadastroViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    fun cadastrarUsuario(username: String, senha: String, onResult: (Long) -> Unit) {
        if (username.isNotEmpty() && senha.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                val newUser = User(username = username, password = senha)
                val userId = userRepository.dao.insert(newUser)
                onResult(userId)
            }
        } else {
            onResult(-1L)
        }
    }
}
