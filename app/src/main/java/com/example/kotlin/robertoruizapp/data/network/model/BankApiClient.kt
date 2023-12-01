package com.example.kotlin.robertoruizapp.data.network.model

import android.util.Log
import com.example.kotlin.robertoruizapp.data.network.model.BankAccount.BankObject

class BankApiClient(private val apiService: BankApiService) {

    suspend fun getBanks(token: String): BankObject? {
        return try {
            apiService.getBanks("Bearer $token")
        } catch (e: Exception) {
            Log.d("Catch", "Error: ${e.message}")
            null
        }
    }
}