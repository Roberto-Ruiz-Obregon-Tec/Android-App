package com.example.kotlin.robertoruizapp.data.network.model.publication


data class PublicObjeto (
    val `data`: List<Document>,
    val results: Int?,
    val status: String
)