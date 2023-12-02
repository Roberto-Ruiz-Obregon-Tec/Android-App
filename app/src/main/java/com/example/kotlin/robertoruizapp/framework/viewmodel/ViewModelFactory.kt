package com.example.kotlin.robertoruizapp.framework.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin.robertoruizapp.data.CourseRepository
import com.example.kotlin.robertoruizapp.data.UserRepository

/**
 * Factory class for creating ViewModels with dependencies.
 *
 * @param userRepository The repository for user-related data.
 * @param courseRepository The repository for course-related data.
 */
class ViewModelFactory(
    private val userRepository: UserRepository,
    private val courseRepository: CourseRepository
) : ViewModelProvider.Factory {

    /**
     * Creates a new instance of the specified ViewModel class.
     *
     * @param modelClass The class of the ViewModel to create.
     * @return A new instance of the ViewModel with dependencies injected.
     * @throws IllegalArgumentException If the specified ViewModel class is unknown.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CursoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CursoViewModel(userRepository, courseRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
