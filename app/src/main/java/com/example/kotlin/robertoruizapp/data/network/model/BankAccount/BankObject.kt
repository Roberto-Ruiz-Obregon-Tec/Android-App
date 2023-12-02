package com.example.kotlin.robertoruizapp.data.network.model.BankAccount

import com.example.kotlin.robertoruizapp.data.network.model.BankAccount.Data

/**
 * Represents a bank object containing bank account data.
 *
 * @property data The bank account data.
 * @property results The number of results (optional).
 * @property status The status of the bank object.
 */
data class BankObject (
    val data: Data,
    val results: Int?,
    val status: String
)