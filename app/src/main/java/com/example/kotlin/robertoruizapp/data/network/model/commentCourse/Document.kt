package com.example.kotlin.robertoruizapp.data.network.model.commentCourse

import com.example.kotlin.robertoruizapp.data.network.model.Course.CourseObject
data class Document(
    val _id: String,
    val course: CourseObject,
    val createdAt: String,
    val description: String,
    val id: String,
    val name: String,
    val updatedAt: String
)

