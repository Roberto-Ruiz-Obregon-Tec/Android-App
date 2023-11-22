package com.example.kotlin.robertoruizapp.domain


import com.example.kotlin.robertoruizapp.data.ScholarshipRepository
import com.example.kotlin.robertoruizapp.data.network.model.Becas.BecasObjeto
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity


/**
 * Scholarship list requirement
 *
 * @constructor Create empty Scholarship list requirement
 */
class ScholarshipListRequirement {
    private val ScholarshipRepository = ScholarshipRepository()

    /**
     * Invoke the repository in order to get the request from the API
     *
     * @param token: Authorization token
     * @return [BecasObjeto]
     */
    suspend operator fun invoke(token: String): BecasObjeto? = ScholarshipRepository.getScholarship(
        LoginActivity.token
    )
}