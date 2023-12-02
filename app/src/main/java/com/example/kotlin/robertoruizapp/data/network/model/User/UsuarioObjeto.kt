package com.example.kotlin.robertoruizapp.data.network.model.User

/**
 * Represents a user object containing user-related data.
 *
 * @property data The user data container.
 * @property results The number of results.
 * @property status The status of the user object.
 */
data class UsuarioObjeto (
    val `data`: Data,
    val results: Int,
    val status: String
)