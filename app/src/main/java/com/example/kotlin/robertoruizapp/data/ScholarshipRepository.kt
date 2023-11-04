package com.example.kotlin.robertoruizapp.data

import com.example.kotlin.robertoruizapp.data.network.NetworkModuleDICourse
import com.example.kotlin.robertoruizapp.data.network.NetworkModuleDIScholarship
import com.example.kotlin.robertoruizapp.data.network.model.Becas.BecasObjeto
import com.example.kotlin.robertoruizapp.data.network.model.Course.CourseObject
import com.example.kotlin.robertoruizapp.data.network.model.CourseApiService
import com.example.kotlin.robertoruizapp.data.network.model.ScholarshipApiService

class ScholarshipRepository {
    private lateinit var api: ScholarshipApiService
    suspend fun getScholarship(): BecasObjeto {
        api = NetworkModuleDIScholarship()
        return api.getScholarship()
    }
}