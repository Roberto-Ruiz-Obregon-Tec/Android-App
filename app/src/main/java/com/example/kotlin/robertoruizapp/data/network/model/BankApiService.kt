package com.example.kotlin.robertoruizapp.data.network.model

import com.example.kotlin.robertoruizapp.data.network.model.BankAccount.BankObject
import retrofit2.http.GET
import retrofit2.http.Header

interface BankApiService {
    @GET("bankaccounts")
    suspend fun getBanks(@Header("Authorization") authToken: String): BankObject
}