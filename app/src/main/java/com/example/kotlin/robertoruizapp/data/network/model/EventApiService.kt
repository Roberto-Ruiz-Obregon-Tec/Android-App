package com.example.kotlin.robertoruizapp.data.network.model
import com.example.kotlin.robertoruizapp.data.network.model.Events.EventObject
import com.example.kotlin.robertoruizapp.data.network.model.Events.SingleEventObject
import com.example.kotlin.robertoruizapp.data.network.model.companyCertification.CertificacionEmpresaObj
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

/**
 * Interface that defines how requests to the Events API should be made.
 */
interface EventApiService {
    /**
     * Gets an event using an authentication token.
     *
     * @param authToken Authentication token for the request.
     * @return CourseObject that represents the returned event.
     */
    @GET("event")
    suspend fun getEvent(@Header("Authorization") authToken: String): EventObject
    
    @GET("event/{id}")
    suspend fun getEventById(@Path("id") eventId: String, @Header("Authorization") authToken: String): SingleEventObject
}
