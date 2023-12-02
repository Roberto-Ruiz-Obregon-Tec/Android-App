package com.example.kotlin.robertoruizapp.data.network.model

import com.example.kotlin.robertoruizapp.data.network.model.BankAccount.BankObject
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * Defines the API service for interacting with bank-related data.
 */
interface BankApiService {
    /**
     * Retrieves a list of banks from the API.
     *
     * @param authToken The user's authentication token to access the API.
     * @return A [BankObject] representing the list of banks.
     */
    @GET("bankaccounts")
    suspend fun getBanks(@Header("Authorization") authToken: String): BankObject
}