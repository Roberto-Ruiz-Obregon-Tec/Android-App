package com.example.kotlin.robertoruizapp.data.network

import com.example.kotlin.robertoruizapp.data.network.model.Becas.BecasObjeto


class ScholarshipApiClient {
    private lateinit var api: ScholarshipApiService
    suspend fun getScholarshipList(limit: Int): BecasObjeto? {
        api = NetworkModuleDIScholarship()
        return try {
            api.getScholarshipList(limit)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            null
        }
    }
    suspend fun getScholarshipInfo(id: Int): BecasObjeto? {
        api = NetworkModuleDIScholarship()
        return try {
            api.getScholarshipInfo(id)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            null
        }
    }
}