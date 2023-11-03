package com.example.kotlin.robertoruizapp.data.network.model.Course

data class Document(
    val _id: String,
    val cost: Int,
    val capacity: Int,
    val rating: Int,
    val meetingCode: String,
    val accessCode: String,
    val name: String,
    val description: String,
    val speaker: String,
    val startDate: String,
    val endDate: String,
    val schedule: String,
    val modality: String,
    val postalCode: Int,
    val location: String,
    val status: String,
    val courseImage: String,
    val focus: List<String>
)