package com.example.kotlin.robertoruizapp.framework.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.kotlin.robertoruizapp.data.UserRepository
import kotlinx.coroutines.Dispatchers

class CursoViewModel(private val userRepository: UserRepository) : ViewModel() {

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
}