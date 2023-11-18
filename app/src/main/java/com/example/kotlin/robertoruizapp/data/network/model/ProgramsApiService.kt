package com.example.kotlin.robertoruizapp.data.network.model

import com.example.kotlin.robertoruizapp.data.network.model.Programs.ProgramsObj
import retrofit2.http.GET
import retrofit2.http.Header

interface ProgramsApiService {
    //http://localhost:3001/v1/program
    /**
     *
     * Programs details are obtained
     * @param authToken is the token we receive which accepts the GET request
     * @return Returns an object which contains the details of the programs
     * @throws IOException If a network or server connection problem occurs.
     * @throws HttpException if the HTTP response was not successful
     */

    @GET("program") //URL Bases
    suspend fun getPrograms(@Header("Authorizarion")authToken: String):
            ProgramsObj
}