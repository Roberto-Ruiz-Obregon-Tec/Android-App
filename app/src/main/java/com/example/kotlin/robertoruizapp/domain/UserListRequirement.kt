package com.example.kotlin.robertoruizapp.domain

import com.example.kotlin.robertoruizapp.data.UserRepository
import com.example.kotlin.robertoruizapp.data.network.model.User.UsuarioObjeto
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity

class UserListRequirement {
    private val userRepository = UserRepository()
    /**
     * Class that represents a requirement to obtain a list of users.
     *
     * @param userRepository User repository used to get the user information.
     */
    suspend operator fun invoke(token: String): UsuarioObjeto? = userRepository.getUser(LoginActivity.token)
}