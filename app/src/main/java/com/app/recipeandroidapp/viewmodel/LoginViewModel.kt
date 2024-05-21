package com.app.recipeandroidapp.viewmodel

import androidx.lifecycle.ViewModel
import com.app.recipeandroidapp.model.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    fun findUserByUsername(username: String) = userRepository.dao.findUserByUsername(username)
}
