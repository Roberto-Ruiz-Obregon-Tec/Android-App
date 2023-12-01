package com.example.kotlin.robertoruizapp.data

import com.example.kotlin.robertoruizapp.data.network.NetworkModuleDIBank
import com.example.kotlin.robertoruizapp.data.network.model.BankAccount.BankObject
import com.example.kotlin.robertoruizapp.data.network.model.BankApiService
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity


class BankRepository {
    private val api: BankApiService = NetworkModuleDIBank()

    suspend fun getBanks(token: String): BankObject? {
        return api.getBanks("Bearer ${token}")
    }
}