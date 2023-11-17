package com.example.kotlin.robertoruizapp.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.Response
import com.example.kotlin.robertoruizapp.utils.Constants
import com.google.gson.JsonElement


object NetworkModuleDIForgotPassword {
    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideForgotPasswordService(): ForgotPasswordAPIService {
        return provideRetrofit().create(ForgotPasswordAPIService::class.java)
    }
}

interface ForgotPasswordAPIService {
    @POST("user/forgotpassword")
    suspend fun forgotPassword(@Body request: ForgotPasswordRequest): Response<ApiResponse<JsonElement>> //

    @POST("user/resetpassword/{token}")
    suspend fun ResetPassword(@Path("token") token: String, @Body request: ResetPasswordRequest): Response<ApiResponse<JsonElement>> // Usa Any si el tipo de datos es desconocido o varía
}


data class ForgotPasswordRequest(val email: String)
data class ResetPasswordRequest(val password: String, val passwordConfirm: String)
