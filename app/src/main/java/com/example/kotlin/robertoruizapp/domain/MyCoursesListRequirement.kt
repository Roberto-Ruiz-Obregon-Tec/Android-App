package com.example.kotlin.robertoruizapp.domain

import com.example.kotlin.robertoruizapp.data.MyCoursesRepository
import com.example.kotlin.robertoruizapp.data.network.model.MyCourses.Document
import com.example.kotlin.robertoruizapp.data.network.model.MyCourses.MyCourseObject
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity

/**
 * Class that represents a requirement to obtain a list of user courses.
 *
 * @param MyCoursesRepository Course repository used to get the course information.
 */
class MyCoursesListRequirement {
    private val myCoursesRepository = MyCoursesRepository()
    /**
     * Invokes the requirement to obtain a course using an authentication token.
     *
     * @param token Authentication token to make the request.
     * @return MyCourseObject that represents the obtained course, or null on error.
     */
    suspend operator fun invoke(token: String): Document? = myCoursesRepository.getMyCourses(
        LoginActivity.token)
}