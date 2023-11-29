package com.example.kotlin.robertoruizapp.data.network.model.MyCourses

data class MyCourseObject (
    val `data`: List<Document>,
    val results: Int?,
    val status: String
)