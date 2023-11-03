package com.example.kotlin.robertoruizapp.data

import com.example.kotlin.robertoruizapp.data.network.ScholarshipApiClient
import com.example.kotlin.robertoruizapp.data.network.model.Becas.BecasObjeto

class ScholarshipRepository {
    private val apiScholarship = ScholarshipApiClient()

    suspend fun getScholarshipList(limit: Int): BecasObjeto? =
        apiScholarship.getScholarshipList(limit)

    suspend fun getScholarshipInfo(id:Int): BecasObjeto? =
        apiScholarship.getScholarshipInfo(id)
}