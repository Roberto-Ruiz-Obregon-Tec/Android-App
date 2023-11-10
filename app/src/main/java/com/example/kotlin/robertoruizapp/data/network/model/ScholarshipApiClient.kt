package com.example.kotlin.robertoruizapp.data.network.model

import android.util.Log
import com.example.kotlin.robertoruizapp.data.network.model.Becas.BecasObjeto

class ScholarshipApiClient {
    private lateinit var api: ScholarshipApiService
    suspend fun getSholarship(): BecasObjeto?{
        var result: BecasObjeto? = null
        try{
            result =api.getScholarship()
        }catch(e: java.lang.Exception){
            Log.d("Catch", "Result: ${result}")
            e.printStackTrace()
            null
        }
        return result
    }
}