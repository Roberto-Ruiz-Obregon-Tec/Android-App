package com.example.kotlin.robertoruizapp.data.network.model.BankAccount

import com.example.kotlin.robertoruizapp.data.network.model.companyCertification.Data

data class BankObject (
    val `data`: Data,
    val results: Int?,
    val status: String
)