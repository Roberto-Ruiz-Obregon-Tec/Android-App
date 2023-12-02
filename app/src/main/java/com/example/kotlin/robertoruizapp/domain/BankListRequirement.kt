package com.example.kotlin.robertoruizapp.domain

import com.example.kotlin.robertoruizapp.data.BankRepository
import com.example.kotlin.robertoruizapp.data.network.model.BankAccount.BankObject
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity

/**
 * Use case for retrieving a list of banks.
 */
class BankListRequirement {
    private val bankRepository = BankRepository()

    /**
     * Executes the use case to retrieve a list of banks.
     *
     * @return A [BankObject] representing the list of banks, or null if an error occurs.
     */
    suspend operator fun invoke(): BankObject? = bankRepository.getBanks(LoginActivity.token)
}