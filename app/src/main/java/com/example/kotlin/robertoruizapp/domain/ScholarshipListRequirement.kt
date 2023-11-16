package com.example.kotlin.robertoruizapp.domain


import com.example.kotlin.robertoruizapp.data.ScholarshipRepository
import com.example.kotlin.robertoruizapp.data.network.model.Becas.BecasObjeto
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity


class ScholarshipListRequirement {
    private val ScholarshipRepository = ScholarshipRepository()
    suspend operator fun invoke(token: String): BecasObjeto? = ScholarshipRepository.getScholarship(
        LoginActivity.token
    )
}