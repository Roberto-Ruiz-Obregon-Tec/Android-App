package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.os.Build
import com.example.kotlin.robertoruizapp.databinding.FragmentoFeedBinding
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
import com.example.kotlin.robertoruizapp.data.Repository
import com.example.kotlin.robertoruizapp.data.companyCertificationRepository
import com.example.kotlin.robertoruizapp.data.network.model.Events.EventObject
import com.example.kotlin.robertoruizapp.data.network.model.companyCertification.Document
import com.example.kotlin.robertoruizapp.data.network.model.companyCertification.CertificacionEmpresaObj
import com.example.kotlin.robertoruizapp.framework.adapters.EventsAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentoFeed : Fragment() {

    private var _binding: FragmentoFeedBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentoFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCompanyCertification()

        binding.button3.setOnClickListener() {

            getCompanyCertification()
        }

        binding.button1.setOnClickListener {
            getEvents()
        }
    }

    class EmpresaAdapter(private val empresas: List<Document?>) :
        RecyclerView.Adapter<EmpresaAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val nombreEmpresa: TextView = view.findViewById(R.id.empresa_nombre)
            val descripcionEmpresa: TextView = view.findViewById(R.id.empresa_description)
            val certificacionEmpresa: TextView = view.findViewById(R.id.empresa_certificacion)
            val celEmpresa: TextView = view.findViewById(R.id.cel_empresa)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_empresas_certificacion, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val empresa = empresas[position]
            //Log.d("EmpresaAdapter", "Certificaciones: ${empresa?.certifications}")
            holder.nombreEmpresa.text = empresa?.name
            holder.descripcionEmpresa.text = empresa?.description
            holder.certificacionEmpresa.text = empresa?.certifications?.joinToString(separator = ", ")
            holder.celEmpresa.text = empresa?.phone

        }


        override fun getItemCount() = empresas.size
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun getCompanyCertification() {
        showProgressBar()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val companyCertificationRepository = companyCertificationRepository()
                val result: CertificacionEmpresaObj? = companyCertificationRepository.getCompanyCertification()

                withContext(Dispatchers.Main) {
                    if (result != null) {
                        val adapter = EmpresaAdapter(result.data.companies)
                        binding.empresaList.adapter = adapter
                        binding.empresaList.layoutManager = LinearLayoutManager(context)
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
    // Get events
    @RequiresApi(Build.VERSION_CODES.N)
    private fun getEvents() {
        showProgressBar()
        CoroutineScope(Dispatchers.IO).launch {
            val eventsRepository = Repository()
            val result: EventObject? = eventsRepository.getEventsNoFilter()

            withContext(Dispatchers.Main) {
                hideProgressBar()
                if (result != null) {
                    val adapter = EventsAdapter(result.data.documents)
                    binding.empresaList.adapter = adapter
                    binding.empresaList.layoutManager = LinearLayoutManager(context)
                }
            }
        }
    }
    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.empresaList.visibility = View.INVISIBLE // Ocultar la lista mientras carga
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
        binding.empresaList.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}