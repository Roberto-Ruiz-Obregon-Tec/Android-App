package com.example.kotlin.robertoruizapp.domain

import com.example.kotlin.robertoruizapp.data.ScholarshipRepository
import com.example.kotlin.robertoruizapp.data.network.model.Becas.BecasObjeto

class ScholarshipListRequirement {
    private val repository = ScholarshipRepository()
    suspend operator fun invoke(
        limit: Int
    ): BecasObjeto? = repository.getScholarshipList(limit)
}