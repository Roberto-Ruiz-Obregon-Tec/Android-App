package com.example.kotlin.robertoruizapp.data.network.model.Course

data class CourseObject(
    val `data`: List<Document>,
    val results: Int?,
    val status: String
)