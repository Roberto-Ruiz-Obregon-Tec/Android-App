package com.example.kotlin.robertoruizapp.data.network

data class ApiResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T? = null // T es un tipo genérico que representa los datos adicionales
)
