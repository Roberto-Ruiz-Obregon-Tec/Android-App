package com.example.kotlin.robertoruizapp.data.network.model

import android.util.Log
import com.example.kotlin.robertoruizapp.data.network.model.Becas.BecasObjeto
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity

class ScholarshipApiClient {
    private lateinit var api: ScholarshipApiService
    suspend fun getSholarship(): BecasObjeto?{
        var result: BecasObjeto? = null
        Log.d("Try", "Try")
        try{
            result =api.getScholarship("Bearer ${LoginActivity.token}")
        }catch(e: java.lang.Exception){
            Log.d("Catch", "Result: ${result}")
            e.printStackTrace()
            null
        }
        return result
    }
}