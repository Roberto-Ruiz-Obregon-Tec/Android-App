package com.example.kotlin.robertoruizapp.data.network.model

import android.util.Log
import com.example.kotlin.robertoruizapp.data.network.model.BankAccount.BankObject

/**
 * A client for interacting with the bank API.
 *
 * @param apiService The API service used to make requests to the bank API.
 */
class BankApiClient(private val apiService: BankApiService) {

    /**
     * Retrieves a list of banks from the API.
     *
     * @param token The user's authentication token.
     * @return A [BankObject] representing the list of banks, or null if an error occurs.
     */
    suspend fun getBanks(token: String): BankObject? {
        return try {
            apiService.getBanks("Bearer $token")
        } catch (e: Exception) {
            Log.d("Catch", "Error: ${e.message}")
            null
        }
    }
}