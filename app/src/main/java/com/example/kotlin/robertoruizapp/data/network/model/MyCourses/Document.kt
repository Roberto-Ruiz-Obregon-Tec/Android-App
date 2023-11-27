package com.example.kotlin.robertoruizapp.data.network.model.MyCourses

data class Document (
    val `data`: List<course>,
    val results: Int?,
    val status: String
)