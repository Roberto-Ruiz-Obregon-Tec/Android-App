package com.example.kotlin.robertoruizapp.data.network.model.MyCourses

data class Document (
    val _id: String,
    val course: List<course>,
    val focus: List<String>
)