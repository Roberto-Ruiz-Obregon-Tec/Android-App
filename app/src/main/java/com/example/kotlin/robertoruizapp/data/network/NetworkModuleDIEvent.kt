package com.example.kotlin.robertoruizapp.data.network

import com.example.kotlin.robertoruizapp.data.network.model.EventApiService
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import com.example.kotlin.robertoruizapp.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Object that provides an instance of [EventApiService] configured to make requests to the Course API.
 */
object NetworkModuleDIEvent {
    private val gsonFactory: GsonConverterFactory = GsonConverterFactory.create()

    // Create an instance of OkHttpClient with an interceptor that adds the JWT token.
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor { chain ->
            val originalRequest = chain.request()

            // Make sure the token is present before trying to add it to the header
            val token = LoginActivity.token
            if (token.isNotEmpty()) {
                val newRequest = originalRequest.newBuilder()
                    .header("Authorization", "Bearer $token")
                    .build()
                chain.proceed(newRequest)
            } else {
                chain.proceed(originalRequest)
            }
        }
    }.build()

    /**
     * Gets an instance of [CourseApiService] configured with the base URL, client OkHttpClient
     * with Gson authentication token and converter.
     *
     * @return Instance of [EventApiService].
     */
    operator fun invoke(): EventApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonFactory)
            .build()
            .create(EventApiService::class.java)
    }
}
