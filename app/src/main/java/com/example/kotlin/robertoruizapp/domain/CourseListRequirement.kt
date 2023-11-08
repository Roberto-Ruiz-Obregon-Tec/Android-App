package com.example.kotlin.robertoruizapp.domain

import com.example.kotlin.robertoruizapp.data.CourseRepository
import com.example.kotlin.robertoruizapp.data.network.model.Course.CourseObject
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity

/**
 * Class that represents a requirement to obtain a list of courses.
 *
 * @param courseRepository Course repository used to get the course information.
 */
class CourseListRequirement {
    private val courseRepository = CourseRepository()
    /**
     * Invokes the requirement to obtain a course using an authentication token.
     *
     * @param token Authentication token to make the request.
     * @return CourseObject that represents the obtained course, or null on error.
     */
    suspend operator fun invoke(token: String): CourseObject? = courseRepository.getCourse(
        LoginActivity.token)
}