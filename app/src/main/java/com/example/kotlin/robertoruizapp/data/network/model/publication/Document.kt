package com.example.kotlin.robertoruizapp.data.network.model.publication


data class Document (
    val _id: String,
    val likes: Int,
    val title: String,
    val description: String,
    val image: String,
    val createdAt: String,
    val updatedAt: String,
    val comments: List<Comment>

)