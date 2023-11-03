package com.example.kotlin.robertoruizapp.domain

import com.example.kotlin.robertoruizapp.data.CourseRepository
import com.example.kotlin.robertoruizapp.data.network.model.Course.CourseObject

class CourseListRequirement {
    private val CourseRepository = CourseRepository()
    suspend operator fun invoke(): CourseObject? = CourseRepository.getCourse()
}