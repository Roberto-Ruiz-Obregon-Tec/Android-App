package com.example.kotlin.robertoruizapp.data.network.model

import com.example.kotlin.robertoruizapp.data.network.model.Events.EventObject
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity

class EventApiClient {
    
    private lateinit var api: EventApiService
    
    suspend fun getEvent() : EventObject? {
        return try {
            api.getEvent("Bearer ${LoginActivity.token}")
        }
        catch (e:java.lang.Exception) {
            e.printStackTrace()
            null
        }
    }
}