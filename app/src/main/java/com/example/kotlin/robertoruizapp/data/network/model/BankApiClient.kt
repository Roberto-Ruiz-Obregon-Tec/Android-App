package com.example.kotlin.robertoruizapp.data.network.model

import android.util.Log
import com.example.kotlin.robertoruizapp.data.network.model.BankAccount.BankObject

class BankApiClient(private val apiService: BankApiService) {
    private lateinit var api: BankApiService

    suspend fun getbanks(token: String): BankObject? {
        try{
            return api.getBanks("Bearer $token")
        } catch (e: Exception) {
            Log.d("Catch", "Error: ${e.message}")
            return null
        }
    }
}