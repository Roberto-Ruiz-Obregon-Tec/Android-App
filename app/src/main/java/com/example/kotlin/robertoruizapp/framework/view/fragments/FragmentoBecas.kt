package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.robertoruizapp.data.ScholarshipRepository
import com.example.kotlin.robertoruizapp.data.network.model.Becas.BecasObjeto
import com.example.kotlin.robertoruizapp.databinding.FragmentoBecasBinding
import com.example.kotlin.robertoruizapp.databinding.FragmentoInicioBinding
import com.example.kotlin.robertoruizapp.framework.adapters.ScholarshipAdapter
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import com.example.kotlin.robertoruizapp.framework.viewmodel.BecasViewModel
import com.example.kotlin.robertoruizapp.framework.viewmodel.InicioViewModel
import com.example.kotlin.robertoruizapp.utils.PreferenceHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentoBecas : Fragment() {
    private var _binding: FragmentoBecasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentoBecasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showProgressBar()
        getScholarship()
    }

    private fun getScholarship() {
        showProgressBar()
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val ScholarshipRepository = ScholarshipRepository()
                val result: BecasObjeto? = ScholarshipRepository.getScholarship(LoginActivity.token)

                if (result != null) {
                    withContext(Dispatchers.Main) {

                        val adapter = ScholarshipAdapter(result.data.documents)
                        binding.recyclerbecas.adapter = adapter
                        binding.recyclerbecas.layoutManager = LinearLayoutManager(context)
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
     * Displays the progress bar and hides the course list.
     */
    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerbecas.visibility = View.INVISIBLE
    }

    /**
     * Hides the progress bar and displays the course list.
     */
    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
        binding.recyclerbecas.visibility = View.VISIBLE
    }

    /**
     * Method called when the fragment view is destroyed.
     * Clear the reference to the object to avoid memory leaks.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // ... otros métodos como showProgressBar, hideProgressBar, etc.
}