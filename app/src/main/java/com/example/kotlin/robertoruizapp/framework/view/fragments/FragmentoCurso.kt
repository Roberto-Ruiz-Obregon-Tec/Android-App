package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.os.Build
import android.os.Bundle
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
import com.example.kotlin.robertoruizapp.data.CourseRepository
import com.example.kotlin.robertoruizapp.data.network.model.Course.CourseObject
import com.example.kotlin.robertoruizapp.data.network.model.Course.Document
import com.example.kotlin.robertoruizapp.databinding.FragmentoCursoBinding
import com.example.kotlin.robertoruizapp.databinding.FragmentoHomeBinding
import com.example.kotlin.robertoruizapp.framework.adapters.CursoAdapter
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import com.example.kotlin.robertoruizapp.framework.view.fragments.FragmentoHome.OnCursoClickListener
import com.example.kotlin.robertoruizapp.framework.viewmodel.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.Normalizer
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FragmentoCurso : Fragment() {
    private var _binding: FragmentoCursoBinding? = null
    private val binding get() = _binding!!

    // Variables adicionales
    private lateinit var fullCoursesList: List<Document>
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
     * An instance of [OnCursoClickListener] used to handle curso (course) click events.
     */
    private val onCursoClickListener = object : FragmentoHome.OnCursoClickListener {
        override fun onCursoClicked(cursoId: String) {
            val fragmentoDetalles = FragmentoCursoDetalles().apply {
                arguments = Bundle().apply {
                    putString("CURSO_ID", cursoId)
                }
            }

            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_main, fragmentoDetalles)
                .addToBackStack(null) // Allows returning to the previous fragment
                .commit()
        }
    }

    /**
     * Method called when the fragment view is created.
     */
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentoCursoBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Method called when the fragment view is created.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCourse()

        emptyTextView = view.findViewById(R.id.emptyTextView)

        val searchView: SearchView = view.findViewById(R.id.search_view)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    filterCourses(it)
                }
                return true
            }
        })
        setupSearchView()
    }

    /**
     * Method to get the list of courses and display it in the UI.
     */
    private fun getCourse() {
        showProgressBar()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val courseRepository = CourseRepository()
                val result: CourseObject? = courseRepository.getCourse(LoginActivity.token)

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
                        val adapter = CursoAdapter(filteredCourses, onCursoClickListener)
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

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    viewModel.setFiltroActual(it)
                    filterCourses(it)
                }
                return true
            }
        })

        // Observar el filtroActual del ViewModel
        viewModel.filtroActual.observe(viewLifecycleOwner) { filtro ->
            filterCourses(filtro)
        }
    }

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
        if (adapter is CursoAdapter) {
            adapter.updateList(filteredList)
        } else {
            binding.cursosList.visibility = View.VISIBLE
            emptyTextView.visibility = View.GONE
            (binding.cursosList.adapter as? CursoAdapter)?.updateList(filteredList)
        }
    }

    fun String.normalize(): String {
        val normalized = Normalizer.normalize(this, Normalizer.Form.NFD)
        return normalized.replace("[\\p{InCombiningDiacriticalMarks}]".toRegex(), "").toLowerCase(Locale.getDefault())
    }

    override fun onResume() {
        super.onResume()
        viewModel.filtroActual.value?.let {
            filterCourses(it)
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
