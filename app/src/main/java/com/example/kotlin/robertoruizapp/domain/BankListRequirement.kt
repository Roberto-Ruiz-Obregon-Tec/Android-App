package com.example.kotlin.robertoruizapp.domain

import com.example.kotlin.robertoruizapp.data.BankRepository
import com.example.kotlin.robertoruizapp.data.network.model.BankAccount.BankObject
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity

class BankListRequirement {
    private val bankRepository = BankRepository()

    suspend fun invoke(token: String): BankObject? = bankRepository.getBanks(LoginActivity.token)
}