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
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
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
    private lateinit var binding: FragmentoMyCoursesBinding
    private val adapter : MyCoursesAdapter = MyCoursesAdapter()
    private lateinit var data:ArrayList<course>
    private val viewModel: MyCoursesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeBinding()
        initializeObservers()
    }

    private fun initializeBinding() {
        binding = FragmentoMyCoursesBinding.inflate(layoutInflater)
        //setContentView(binding.root)
    }

    private fun setUpRecyclerView(dataForList: List<course>) {
        binding.cursosList.setHasFixedSize(true)

        val gridLayoutManager = GridLayoutManager(requireContext(), 1)
        binding.cursosList.layoutManager = gridLayoutManager

        adapter.MyCourseAdapter(dataForList, requireContext())
        binding.cursosList.adapter = adapter
    }

    private fun initializeObservers() {
        viewModel.LiveData.observe(this) { item ->
            adapter.MyCourseAdapter(item.data, requireContext())
            adapter.notifyDataSetChanged()
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

//    /**
//     * Hides the progress bar and displays the course list.
//     */
//    private fun hideProgressBar() {
//        if (isAdded) {
//            binding.progressBar.visibility = View.GONE
//            binding.cursosList.visibility = View.VISIBLE
//        }
//    }
//
//    /**
//     * Method called when the fragment view is destroyed.
//     * Clear the reference to the object to avoid memory leaks.
//     */
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}