package com.example.kotlin.robertoruizapp.data.network.model.Inscripcion

/**
 * Represents a request for course inscription.
 *
 * @property courseId The identifier of the course for which the user wants to register.
 * @property voucher The voucher or registration code for the course.
 */
data class inscriptionRequest(
    val courseId: String,
    val voucher: String
)
