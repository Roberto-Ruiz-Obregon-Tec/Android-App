package com.example.kotlin.robertoruizapp.data

import com.example.kotlin.robertoruizapp.data.network.NetworkModuleDIPrograms
import com.example.kotlin.robertoruizapp.data.network.model.Programs.ProgramsObj
import com.example.kotlin.robertoruizapp.data.network.model.ProgramsApiService
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity

class ProgramsRepository {
    private val api: ProgramsApiService = NetworkModuleDIPrograms()


    /**
     * Uses the authentication token, which is provided by [LoginActivity]
     *
     * @param authentication token, although it is received as a parameter, the one stored aesthetically is used
     *
     * @return returns an object [ProgramsObj] with the details of the programs
     */
    suspend fun  getPrograms(token: String): ProgramsObj?{
        return api.getPrograms("Bearer ${LoginActivity.token}")

    }


}