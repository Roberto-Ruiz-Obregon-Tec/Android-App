package com.example.kotlin.robertoruizapp.data.network.model.User

data class UserDocument (
    val id: String,
    val age: Int,
    val company: String,
    val email: String,
    val firstName: String,
    val gender: String,
    val interests: String,
    val lastName: String,
    val occupation: String,
    val postalCode: Int
)
