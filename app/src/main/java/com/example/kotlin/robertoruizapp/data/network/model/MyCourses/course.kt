package com.example.kotlin.robertoruizapp.data.network.model.MyCourses

data class course (
    val id: String,
    val cost: Double,
    val capacity: Int,
    val remaining: Int,
    val rating: Double,
    val ratingCount: Int,
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
)