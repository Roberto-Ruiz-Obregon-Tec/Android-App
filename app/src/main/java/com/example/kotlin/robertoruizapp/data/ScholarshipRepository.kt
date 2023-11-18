package com.example.kotlin.robertoruizapp.data

import com.example.kotlin.robertoruizapp.data.network.NetworkModuleDICourse
import com.example.kotlin.robertoruizapp.data.network.NetworkModuleDIScholarship
import com.example.kotlin.robertoruizapp.data.network.model.Becas.BecasObjeto
import com.example.kotlin.robertoruizapp.data.network.model.Course.CourseObject
import com.example.kotlin.robertoruizapp.data.network.model.CourseApiService
import com.example.kotlin.robertoruizapp.data.network.model.ScholarshipApiService
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity

class ScholarshipRepository {
    private  var api: ScholarshipApiService = NetworkModuleDIScholarship()
    suspend fun getScholarship(token: String): BecasObjeto {
        return api.getScholarship("Bearer ${LoginActivity.token}")
    }
}