package com.example.kotlin.robertoruizapp.data

import com.example.kotlin.robertoruizapp.data.network.NetworkModuleDIEvent
import com.example.kotlin.robertoruizapp.data.network.model.EventApiService
import com.example.kotlin.robertoruizapp.data.network.model.Events.EventObject
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity

class EventRepository {
    private val api: EventApiService = NetworkModuleDIEvent()
    
    /**
     * Uses the authentication token, which is provided by [LoginActivity]
     *
     * @param authentication token, although it is received as a parameter, the one stored aesthetically is used
     *
     * @return returns an object [eventObj] with the details of the events
     */
    
    suspend fun getEvent(token: String): EventObject? {
        return api.getEvent("Bearer ${LoginActivity.token}")
    }
}