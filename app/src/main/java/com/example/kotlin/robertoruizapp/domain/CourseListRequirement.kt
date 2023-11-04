package com.example.kotlin.robertoruizapp.domain

import com.example.kotlin.robertoruizapp.data.CourseRepository
import com.example.kotlin.robertoruizapp.data.network.model.Course.CourseObject
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity

class CourseListRequirement {
    private val courseRepository = CourseRepository()
    suspend operator fun invoke(token: String): CourseObject? = courseRepository.getCourse(
        LoginActivity.token)
}