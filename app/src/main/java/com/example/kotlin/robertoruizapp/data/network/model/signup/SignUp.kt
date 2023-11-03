package com.example.kotlin.robertoruizapp.data.network.model.signup

data class SignUp(
    val firstName: String,
    val lastName: String,
    val email: String,
    val age: Int,
    val gender: String,
    val occupation: String,
    val postalCode: Int,
    val interests: String,
    val company: String?,
    val sociallyResponsibleCompany: Boolean?,
    val profilePicture: String,
    val password: String
)