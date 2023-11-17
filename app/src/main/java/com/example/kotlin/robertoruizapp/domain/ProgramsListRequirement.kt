package com.example.kotlin.robertoruizapp.domain

import com.example.kotlin.robertoruizapp.data.CourseRepository
import com.example.kotlin.robertoruizapp.data.ProgramsRepository
import com.example.kotlin.robertoruizapp.data.network.model.Course.CourseObject
import com.example.kotlin.robertoruizapp.data.network.model.Programs.ProgramsObj
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity

class ProgramsListRequirement {
    private val programsRepository = ProgramsRepository()
    /**
     * Invokes the requirement to obtain a course using an authentication token.
     *
     * @param token Authentication token to make the request.
     * @return [ProgramsObj] that represents the obtained course, or null on error.
     */
    suspend operator fun invoke(token: String): ProgramsObj? = programsRepository.getPrograms(
        LoginActivity.token)
}