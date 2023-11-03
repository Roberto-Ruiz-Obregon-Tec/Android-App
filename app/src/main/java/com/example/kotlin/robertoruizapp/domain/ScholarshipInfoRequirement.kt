package com.example.kotlin.robertoruizapp.domain

import com.example.kotlin.robertoruizapp.data.ScholarshipRepository
import com.example.kotlin.robertoruizapp.data.network.model.Becas.BecasObjeto

class ScholarshipInfoRequirement {
    private val repository = ScholarshipRepository()
    suspend operator fun invoke(
        idScholarship: Int
    ): BecasObjeto? = repository.getScholarshipInfo(idScholarship)
}