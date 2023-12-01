package com.example.kotlin.robertoruizapp.data.network.model.Events

data class Document (
    val _id: String,
    val location: String,
    val eventName: String,
    val description: String,
    val startDate: String,
    val endDate: String,
    val imageUrl: String,

)