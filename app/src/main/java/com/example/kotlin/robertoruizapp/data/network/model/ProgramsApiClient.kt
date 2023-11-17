package com.example.kotlin.robertoruizapp.data.network.model

import android.util.Log
import com.example.kotlin.robertoruizapp.data.network.model.Programs.ProgramsObj
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity

class ProgramsApiClient {
        /**
         * Make the request to the API service to obtain the characteristics of the Programs
         * Use the LoginActivity Token to authenticate
         *
         * @return an object that has the programs details or 'null' if there is an exception
         */

        private lateinit var api: ProgramsApiService

        suspend fun getPrograms(): ProgramsObj?{
            var result: ProgramsObj? = null
            Log.d("TRY", "TRY")
            try {
                return api.getPrograms("Bearer ${LoginActivity.token}")

            } catch (e: java.lang.Exception) {
                Log.d("Catch","Holaaa" + {result})
                null
            }
            return result
        }
    }