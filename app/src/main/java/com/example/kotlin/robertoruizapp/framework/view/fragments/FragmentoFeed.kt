package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.os.Build
import com.example.kotlin.robertoruizapp.databinding.FragmentoFeedBinding
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.mypokedexapp.viewmodel.MainViewModel
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.companyCertificationRepository
import com.example.kotlin.robertoruizapp.data.network.model.Cursos.CursosObjeto
import com.example.kotlin.robertoruizapp.data.network.model.Cursos.Document as CourseDocument
import com.example.kotlin.robertoruizapp.data.network.model.companyCertification.Document


import com.example.kotlin.robertoruizapp.data.network.model.companyCertification.CertificacionEmpresaObj

import com.example.kotlin.robertoruizapp.utils.PreferenceHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentoFeed : Fragment() {
    private var _binding: FragmentoFeedBinding? = null
    private var empresa: String? = null
    private var companyData: Document? = null
    private lateinit var currentFragment: Fragment

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeBinding()
        getCompanyCertification()
        // Carga los datos
        lateinit var data: List<com.example.kotlin.robertoruizapp.data.network.model.companyCertification.Document>
    }

    private fun initializeBinding() {
        _binding = FragmentoFeedBinding.inflate(layoutInflater)

    }


    private val binding get() = _binding!! // Mover esta línea después de la inicialización

    class EmpresaAdapter(private val empresas: List<Document>) : RecyclerView.Adapter<EmpresaAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val nombreEmpresa: TextView = view.findViewById(R.id.name)
            val descripcionEmpresa: TextView = view.findViewById(R.id.description)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.fragmento_feed, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val empresa = empresas[position]
            holder.nombreEmpresa.text = empresa.name
            holder.descripcionEmpresa.text = empresa.description
        }

        override fun getItemCount() = empresas.size
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCompanyCertification()
    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun getCompanyCertification() {
        CoroutineScope(Dispatchers.IO).launch {
            val companyCertificationRepository = companyCertificationRepository()
            val result: CertificacionEmpresaObj? = companyCertificationRepository.getCompanyCertification()

            if (result != null) {
                withContext(Dispatchers.Main) {
                    val adapter = EmpresaAdapter(result.data.documents)
                    binding.empresaList.adapter = adapter
                    binding.empresaList.layoutManager = LinearLayoutManager(context)
                }
            }
        }
    }



}