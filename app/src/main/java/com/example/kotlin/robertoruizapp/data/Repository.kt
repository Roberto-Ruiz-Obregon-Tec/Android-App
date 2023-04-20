package com.example.kotlin.robertoruizapp.data

import com.example.kotlin.robertoruizapp.data.network.model.*
import com.example.kotlin.robertoruizapp.data.network.model.Cursos.CursosObjeto
import com.example.kotlin.robertoruizapp.data.network.model.Profile.EditProfileRequest
import com.example.kotlin.robertoruizapp.data.network.model.Profile.EditProfileResponse
import com.example.kotlin.robertoruizapp.data.network.model.Profile.Profile
import retrofit2.Call

class Repository() {
    private lateinit var api: ApiService

    suspend fun getCursos(): CursosObjeto?{
        api = NetworkModuleDI()
        return try{
            api.getCursos()
        }catch (e:java.lang.Exception){
            e.printStackTrace()
            null
        }
    }

    suspend fun getUserInfo(id:String): Profile?{
        api = NetworkModuleDI()
        return try{
            api.getUserInfo(id)
        }catch (e:java.lang.Exception){
            e.printStackTrace()
            null
        }
    }

    /**
     * Gets the user [Profile] using their [jwt]
     * @param [jwt] Authorization header of the user
     * @return the API response in [Profile]
     */
    suspend fun getMyInfo(jwt:String): Profile?{
        api = NetworkModuleDI()
        return try{
            api.getMyInfo(jwt)
        }catch (e:java.lang.Exception){
            e.printStackTrace()
            null
        }
    }

    /**
     * Edits the user [Profile]
     * @param [jwt] Authorization header of the user
     * @param [request] Changed fields for the user's profile using a [EditProfileRequest]
     * @return the API response [EditProfileResponse]
     */
    suspend fun editMyInfo(jwt: String, request: EditProfileRequest): EditProfileResponse? {
        api = NetworkModuleDI()
        return try{
            api.editMyInfo(jwt, request)
        }catch (e:java.lang.Exception){
            e.printStackTrace()
            null
        }
    }
}