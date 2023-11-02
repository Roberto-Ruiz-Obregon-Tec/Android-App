package com.example.kotlin.robertoruizapp.data.network.model.Events

data class Document (

    val location: String,
    val _id: String,
    val eventName: String,
    val description: String,
    val startDate: String,
    val endDate: String,
    val ImageUrl: String
)