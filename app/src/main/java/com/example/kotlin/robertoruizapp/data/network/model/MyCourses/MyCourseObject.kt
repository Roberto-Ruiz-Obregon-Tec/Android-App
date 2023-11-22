package com.example.kotlin.robertoruizapp.data.network.model.MyCourses

import com.example.kotlin.robertoruizapp.data.network.model.MyCourses.Document

data class MyCourseObject (
    val `data`: List<Document>,
    val results: Int?,
    val status: String
)