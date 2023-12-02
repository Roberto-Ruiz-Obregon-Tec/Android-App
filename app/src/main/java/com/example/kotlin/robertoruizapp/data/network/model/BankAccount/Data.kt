package com.example.kotlin.robertoruizapp.data.network.model.BankAccount

/**
 * Represents a container for a list of bank documents (accounts).
 *
 * @property accounts The list of bank documents (accounts).
 */
data class Data (
    val accounts: List<BankDocument>,
)