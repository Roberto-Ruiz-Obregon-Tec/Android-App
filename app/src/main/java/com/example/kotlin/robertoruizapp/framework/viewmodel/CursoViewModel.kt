package com.example.kotlin.robertoruizapp.framework.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.kotlin.robertoruizapp.data.CourseRepository
import com.example.kotlin.robertoruizapp.data.UserRepository
import com.example.kotlin.robertoruizapp.data.network.model.Course.Document
import com.example.kotlin.robertoruizapp.data.network.model.User.UserDocument
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import kotlinx.coroutines.Dispatchers

class CursoViewModel(private val userRepository: UserRepository, private val courseRepository: CourseRepository) : ViewModel() {

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
}