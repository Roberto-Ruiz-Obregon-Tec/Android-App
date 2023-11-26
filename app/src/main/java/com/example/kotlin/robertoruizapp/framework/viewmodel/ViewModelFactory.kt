package com.example.kotlin.robertoruizapp.framework.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin.robertoruizapp.data.CourseRepository
import com.example.kotlin.robertoruizapp.data.UserRepository

class ViewModelFactory(
    private val userRepository: UserRepository,
    private val courseRepository: CourseRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CursoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CursoViewModel(userRepository, courseRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
