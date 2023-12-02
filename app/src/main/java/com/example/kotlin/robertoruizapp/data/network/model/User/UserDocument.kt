package com.example.kotlin.robertoruizapp.data.network.model.User

/**
 * Represents a document containing user information.
 *
 * @property id The unique identifier of the user.
 * @property age The age of the user.
 * @property company The company name associated with the user.
 * @property email The email address of the user.
 * @property firstName The first name of the user.
 * @property gender The gender of the user.
 * @property interests The user's interests or hobbies.
 * @property lastName The last name of the user.
 * @property occupation The occupation or job title of the user.
 * @property postalCode The postal code or ZIP code of the user.
 */
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
