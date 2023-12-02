package com.example.kotlin.robertoruizapp.data.network.model.Inscripcion

/**
 * Represents a response from a course inscription request.
 *
 * @property status The status of the response (e.g., success or failure).
 * @property message A descriptive message associated with the response.
 */
data class inscriptionResponse(
    val status: String,
    val message: String
)
