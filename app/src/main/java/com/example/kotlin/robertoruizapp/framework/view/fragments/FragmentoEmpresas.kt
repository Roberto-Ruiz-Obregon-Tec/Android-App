package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.robertoruizapp.data.companyCertificationRepository
import com.example.kotlin.robertoruizapp.data.network.model.companyCertification.CertificacionEmpresaObj
import com.example.kotlin.robertoruizapp.databinding.FragmentoEmpresasBinding
import com.example.kotlin.robertoruizapp.databinding.FragmentoEventosBinding
import com.example.kotlin.robertoruizapp.databinding.FragmentoPublicacionesBinding
import com.example.kotlin.robertoruizapp.framework.adapters.EmpresaAdapter
import com.example.kotlin.robertoruizapp.framework.adapters.EventsAdapter
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentoEmpresas : Fragment(){

    private var _binding: FragmentoEmpresasBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentoEmpresasBinding.inflate(inflater, container, false)
        return binding.root
    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCompanyCertification()
    }
    /**
     * Obtain company certifications and update your view.
     *
     * Make an asynchronous request to obtain company certifications and, once obtained,
     * Updates the list in the UI.
     */

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getCompanyCertification() {
        showProgressBar()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val companyCertificationRepository = companyCertificationRepository()
                val result: CertificacionEmpresaObj? = companyCertificationRepository.getCompanyCertification(
                    LoginActivity.token)


                if (result != null) {
                    withContext(Dispatchers.Main) {
                        val adapter = EmpresaAdapter(result.data.companies)
                        binding.empresasList.adapter = adapter
                        binding.empresasList.layoutManager = LinearLayoutManager(context)
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
    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.empresasList.visibility = View.INVISIBLE // Ocultar la lista mientras carga
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
        binding.empresasList.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}
