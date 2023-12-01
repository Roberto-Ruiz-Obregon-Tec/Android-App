package com.example.kotlin.robertoruizapp.framework.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.CourseRepository
import com.example.kotlin.robertoruizapp.data.UserRepository
import com.example.kotlin.robertoruizapp.data.network.model.Course.Document
import com.example.kotlin.robertoruizapp.data.network.model.User.UserDocument
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import kotlinx.coroutines.Dispatchers

class CursoViewModel(private val userRepository: UserRepository, private val courseRepository: CourseRepository) : ViewModel() {

    val currentUserInfo: LiveData<UserDocument> = liveData(Dispatchers.IO) {
        val token = LoginActivity.token
        Log.d("CursoViewModel", "Token: $token")
        val usuarioObjeto = userRepository.getUser(token)
        Log.d("CursoViewModel", "UsuarioObjeto: $usuarioObjeto")
        val userId = usuarioObjeto?.data?.document?.id
        Log.d("CursoViewModel", "UserId: $userId")

        if (!userId.isNullOrEmpty()) {
            val userInfo = userRepository.getUserById(userId, token)
            if (userInfo != null) {
                Log.d("CursoViewModel", "UserInfo: $userInfo")
                emit(userInfo)
            } else {
                Log.d("CursoViewModel", "UserInfo es null")
            }
        } else {
            Log.d("CursoViewModel", "UserId es nulo o vacío")
        }
    }

    fun getUserInfo(token: String, userId: String) = liveData(Dispatchers.IO) {
        try {
            val userInfo = userRepository.getUserById(userId, token)
            if (userInfo != null) {
                emit(Result.success(userInfo))
            } else {
                emit(Result.failure(RuntimeException("Usuario no encontrado")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    fun getCursoInfo(cursoId: String) = liveData(Dispatchers.IO) {
        try {
            val courseInfo = courseRepository.getCourseById(cursoId, LoginActivity.token)
            if (courseInfo != null) {
                emit(Result.success(courseInfo))
            } else {
                emit(Result.failure(RuntimeException("Curso no encontrado")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    fun postInscription(courseId: String) = liveData(Dispatchers.IO) {
        try {
            val inscriptionResponse = courseRepository.postInscription(courseId,"" ,LoginActivity.token)
            Log.d("prueba", "token ${LoginActivity.token}")
            if (inscriptionResponse != null) {
                emit(Result.success(inscriptionResponse))
            } else {
                emit(Result.failure(RuntimeException("Hubo un error")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}