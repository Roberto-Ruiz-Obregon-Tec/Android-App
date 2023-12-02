package com.example.kotlin.robertoruizapp.data

import com.example.kotlin.robertoruizapp.data.network.NetworkModuleDIBank
import com.example.kotlin.robertoruizapp.data.network.model.BankAccount.BankObject
import com.example.kotlin.robertoruizapp.data.network.model.BankApiService
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity

/**
 * Repository for interacting with bank-related data.
 */
class BankRepository {
    private val api: BankApiService = NetworkModuleDIBank()

    /**
     * Retrieves a list of banks from the API.
     *
     * @param token The user's authentication token.
     * @return A [BankObject] representing the list of banks, or null if an error occurs.
     */
    suspend fun getBanks(token: String): BankObject? {
        return api.getBanks("Bearer ${token}")
    }
}