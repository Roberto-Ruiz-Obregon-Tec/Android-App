package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.MyCoursesRepository
import com.example.kotlin.robertoruizapp.data.network.model.MyCourses.MyCourseObject
import com.example.kotlin.robertoruizapp.data.network.model.MyCourses.Document
import com.example.kotlin.robertoruizapp.data.network.model.MyCourses.course
import com.example.kotlin.robertoruizapp.databinding.FragmentoMyCoursesBinding
import com.example.kotlin.robertoruizapp.framework.adapters.MyCoursesAdapter
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import com.example.kotlin.robertoruizapp.framework.viewmodel.MyCoursesViewModel
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
    private var fullCoursesList: List<course> = listOf()
    private lateinit var emptyTextView: TextView
    private val viewModel: SharedViewModel by activityViewModels()


    /**
     * Interface for handling curso (course) click events.
     */
    interface OnCursoClickListener {
        /**
         * Called when a curso (course) item is clicked.
         *
         * @param cursoId The ID of the clicked curso (course).
         */
        fun onCursoClicked(cursoId: String)
    }

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

        emptyTextView = view.findViewById(R.id.emptyTextView)

        viewModel.filtroActual.observe(viewLifecycleOwner) { filtro ->
            if (filtro.isNotEmpty()) {
                filterCourses(filtro)
            }
        }
    }

    /**
     * Method to get the list of courses and display it in the UI.
     */
    private fun getCourse() {
        showProgressBar()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val courseRepository = MyCoursesRepository()
                val result: Document? = courseRepository.getMyCourses(LoginActivity.token)

                if (result != null) {
                    fullCoursesList = result.data
                    // Filter courses by startDate and by remaining, so they don't appear on the view
                    val filteredCourses = result.data.filter { course ->
                        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
                        val startDate = sdf.parse(course.startDate)
                        val today = Calendar.getInstance().time

                        val isDateValid = startDate != null && (startDate.after(today) || startDate.compareTo(today) == 0)
                        val isRemainingValid = course.remaining > 0 // Validate the capacity of the courses

                        isDateValid && isRemainingValid // The course must follow both conditions
                    }

                    withContext(Dispatchers.Main) {
                        val adapter = MyCoursesAdapter(filteredCourses)
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
     * Filters the courses based on the given query.
     *
     * @param query The query string to filter the courses.
     */
    private fun filterCourses(query: String) {
        val today = Calendar.getInstance().time
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val normalizedQuery = query.normalize()

        val filteredList = fullCoursesList.filter { course ->
            val startDate = sdf.parse(course.startDate)
            val isDateValid = startDate != null && !startDate.before(today)
            val isRemainingValid = course.remaining > 0
            val normalizedCourseName = course.name.normalize()

            normalizedCourseName.contains(normalizedQuery, ignoreCase = true) && isDateValid && isRemainingValid
        }

        val adapter = binding.cursosList.adapter
        if (binding.cursosList.adapter == null) {
            val adapter = MyCoursesAdapter(filteredList)
            binding.cursosList.adapter = adapter
            binding.cursosList.layoutManager = LinearLayoutManager(context)
        } else if (binding.cursosList.adapter is MyCoursesAdapter) {
            (binding.cursosList.adapter as MyCoursesAdapter).updateList(filteredList)
        }
    }

    /**
     * Normalizes the name of the course by removing diacritical marks and converting to lowercase.
     *
     * @return The normalized name.
     */
    fun String.normalize(): String {
        val normalized = Normalizer.normalize(this, Normalizer.Form.NFD)
        return normalized.replace("[\\p{InCombiningDiacriticalMarks}]".toRegex(), "").toLowerCase(Locale.getDefault())
    }

    /**
     * Called when the fragment's view is resumed or becomes active.
     * It observes the [viewModel.filtroActual] and, if it is not empty, filters the courses based on it.
     */
    override fun onResume() {
        super.onResume()
        viewModel.filtroActual.value?.let {
            if (it.isNotEmpty()) {
                filterCourses(it)
            }
        }
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