package com.example.kotlin.robertoruizapp.data


import com.example.kotlin.robertoruizapp.data.network.NetworkModuleInfoFun
import com.example.kotlin.robertoruizapp.data.network.model.InfoFoundation.InfoObject
import com.example.kotlin.robertoruizapp.data.network.model.InfoFunApiService
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity

class InfoFunRepository {
    private val api: InfoFunApiService = NetworkModuleInfoFun()

    /**
     * Gets a course using an authentication token.
     *
     * @param token Authentication token to make the request.
     * @return CourseObject that represents the obtained course, or null on error.
     */
    suspend fun getInfo(token: String): InfoObject? {
        return api.getInfo("Bearer ${LoginActivity.token}")
    }
}