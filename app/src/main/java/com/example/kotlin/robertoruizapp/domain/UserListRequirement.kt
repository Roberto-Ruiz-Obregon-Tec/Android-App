package com.example.kotlin.robertoruizapp.domain

import com.example.kotlin.robertoruizapp.data.UserRepository
import com.example.kotlin.robertoruizapp.data.network.model.User.UsuarioObjeto
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity

/**
 * Use case for obtaining a list of users.
 *
 * This use case allows fetching a list of users from the repository.
 *
 * @property userRepository The user repository used to access user information.
 */
class UserListRequirement {
    private val userRepository = UserRepository()
    /**
     * Executes the use case to obtain a list of users.
     *
     * @param token The user's authentication token.
     * @return A [UsuarioObjeto] representing the list of users, or null if an error occurs.
     */
    suspend operator fun invoke(token: String): UsuarioObjeto? = userRepository.getUser(LoginActivity.token)
}