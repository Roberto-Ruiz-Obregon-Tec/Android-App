package com.example.kotlin.robertoruizapp.data

import android.util.Log
import com.example.kotlin.robertoruizapp.data.network.NetworkModuleDIEvent
import com.example.kotlin.robertoruizapp.data.network.model.EventApiService
import com.example.kotlin.robertoruizapp.data.network.model.Events.Document
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

    suspend fun getEventById(eventId: String, token: String): Document? {
        try {
            val response = api.getEventById(eventId, "Bearer $token")
            Log.d("EventRepository", "API Response for Single Event ID $eventId: $response")
            return response.data.documents
        } catch (e: Exception) {
            Log.e("EventRepository", "Error fetching single event by ID: ${e.message}")
            return null
        } 
    }
}