package com.example.kotlin.robertoruizapp.domain

import com.example.kotlin.robertoruizapp.data.InfoFunRepository
import com.example.kotlin.robertoruizapp.data.network.model.InfoFoundation.InfoObject
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity

class infoFunListRequirement {

    private val infoFunRepository = InfoFunRepository()
    /**
     * Invokes the requirement to obtain a course using an authentication token.
     *
     * @param token Authentication token to make the request.
     * @return CourseObject that represents the obtained course, or null on error.
     */
    suspend operator fun invoke(token: String): InfoObject? = infoFunRepository.getInfo(
        LoginActivity.token)
}