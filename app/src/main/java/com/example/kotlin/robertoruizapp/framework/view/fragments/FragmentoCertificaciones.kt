package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.os.Build
import com.example.kotlin.robertoruizapp.databinding.ActivityCertificacionesBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.CertificacionesRepository
import com.example.kotlin.robertoruizapp.data.network.model.certificaciones.CertificacionesObjeto
import com.example.kotlin.robertoruizapp.data.network.model.certificaciones.Document
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import com.example.kotlin.robertoruizapp.framework.viewmodel.CertificacionesAdapter
import java.util.*

/*
    * Created by Dante Perez 2/11/2023
    * A fragment that displays a list of certifications.
    *
    * Parameters:
    *  None.
    *
    * Methods:
    *  getCertificaciones(): A suspend function that gets the certification data from the repository.
    *  @return a CertificacionesObjeto object containing the certification data.
    *
    *  onViewCreated(): A function that is called when the fragment is created.
    *  @param view: A View object.
    *  @param savedInstanceState: A Bundle object.
    *
    *  onCreateView(): A function that is called when the fragment is created.
    *  @param inflater: A LayoutInflater object.
    *  @param container: A ViewGroup object.
    *  @param savedInstanceState: A Bundle object.
    *  @return a View object.
    *
    *  onDestroyView(): A function that is called when the fragment is destroyed.
    *  @param None.
    *  @return None.
    *
 */

class FragmentoCertificaciones : Fragment() {
    private var _binding: ActivityCertificacionesBinding? = null
    private val binding get() = _binding!!

    // Crear la vista del fragmento inflando el layout correspondiente y preparando el binding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ActivityCertificacionesBinding.inflate(inflater, container, false)
        return binding.root
    }

    /*
        * A function that is called when the fragment is created.
        * @param view: A View object.
        * @param savedInstanceState: A Bundle object.
        * @return None.
        * @see getCertificaciones
        * @see CertificacionesAdapter
        * @see CertificacionesObjeto
        * @see CertificacionesRepository
        * @see UserDocument
        *
        *
     */
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showProgressBar()
        getCertifications()
    }


    /*
        * A suspend function that gets the certification data from the repository.
        * @param None.
        * @return a CertificacionesObjeto object containing the certification data.
        * @see CertificacionesObjeto
        * @see CertificacionesRepository
        * @see UserDocument
     */
    @RequiresApi(Build.VERSION_CODES.N)
    private fun getCertifications() {
        showProgressBar()
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val certificationRepository = CertificacionesRepository()
                val result: CertificacionesObjeto? = certificationRepository.getCertificaciones(LoginActivity.token)

                if (result != null) {
                    withContext(Dispatchers.Main) {
                        val adapter = CertificacionesAdapter(result.data)
                        binding.certificacionesList.adapter = adapter
                        binding.certificacionesList.layoutManager = LinearLayoutManager(context)
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
        binding.certificacionesList.visibility = View.INVISIBLE
    }

    /**
     * Hides the progress bar and displays the course list.
     */
    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
        binding.certificacionesList.visibility = View.VISIBLE
    }

    /*
        * A function that is called when the fragment is destroyed.
        * @param None.
        * @return None.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}