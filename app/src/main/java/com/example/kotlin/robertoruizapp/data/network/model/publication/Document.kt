package com.example.kotlin.robertoruizapp.data.network.model.publication

import retrofit2.http.Body


data class Document (
    val _id: String,
    val likes: Int,
    val title: String,
    val description: String,
    val image: String,
    val createdAt: String,
    val updatedAt: String,
    val liked: Boolean,
    val comments: List<Comment>

)