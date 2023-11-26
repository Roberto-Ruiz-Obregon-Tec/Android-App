package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.MyCoursesRepository
import com.example.kotlin.robertoruizapp.data.network.model.MyCourses.MyCourseObject
import com.example.kotlin.robertoruizapp.data.network.model.MyCourses.Document
import com.example.kotlin.robertoruizapp.databinding.FragmentoMyCoursesBinding
import com.example.kotlin.robertoruizapp.framework.adapters.MyCoursesAdapter
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import com.example.kotlin.robertoruizapp.framework.viewmodel.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.Normalizer
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FragmentoMyCourses: Fragment() {
    private var _binding: FragmentoMyCoursesBinding? = null
    private val binding get() = _binding!!

    // Variables adicionales
    private var fullCoursesList: List<Document> = listOf()
    private lateinit var emptyTextView: TextView
    private val viewModel: SharedViewModel by activityViewModels()

    /**
     * Method called when the fragment view is created.
     */
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentoMyCoursesBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Method called when the fragment view is created.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCourse()
    }

    /**
     * Method to get the list of courses and display it in the UI.
     */
    private fun getCourse() {
        showProgressBar()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val courseRepository = MyCoursesRepository()
                val result: List<MyCourseObject>? = courseRepository.getMyCourses(LoginActivity.token)

                if (result != null) {
                    fullCoursesList = result.first().data

                    Log.d("hola", result.first().data.toString())

                    withContext(Dispatchers.Main) {
                        val adapter = MyCoursesAdapter(fullCoursesList)
                        binding.cursosList.adapter = adapter
                        binding.cursosList.layoutManager = LinearLayoutManager(context)
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace() // Log the exception
            } finally {
                withContext(Dispatchers.Main) {
                    hideProgressBar()
                }
            }
        }
    }


    /**
     * Normalizes the name of the course by removing diacritical marks and converting to lowercase.
     *
     * @return The normalized name.
     */
    fun String.normalize(): String {
        val normalized = Normalizer.normalize(this, Normalizer.Form.NFD)
        return normalized.replace("[\\p{InCombiningDiacriticalMarks}]".toRegex(), "").toLowerCase(
            Locale.getDefault())
    }

    /**
     * Displays the progress bar and hides the course list.
     */
    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.cursosList.visibility = View.INVISIBLE
    }

    /**
     * Hides the progress bar and displays the course list.
     */
    private fun hideProgressBar() {
        if (isAdded) {
            binding.progressBar.visibility = View.GONE
            binding.cursosList.visibility = View.VISIBLE
        }
    }

    /**
     * Method called when the fragment view is destroyed.
     * Clear the reference to the object to avoid memory leaks.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}