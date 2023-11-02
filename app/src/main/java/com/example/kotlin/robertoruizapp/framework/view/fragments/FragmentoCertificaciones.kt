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
import java.util.*

class FragmentoCertificaciones : Fragment() {


    private var _binding: ActivityCertificacionesBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActivityCertificacionesBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCertificaciones()
    }

    class CertificacionesAdapter(private val certificaciones: List<Document?>) :
        RecyclerView.Adapter<CertificacionesAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val nombreCertificacion: TextView = view.findViewById(R.id.certificaciones_list)
            val descripcionCertificacion: TextView = view.findViewById(R.id.descripcionCertificacion)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val certificacionView = LayoutInflater.from(parent.context)
                .inflate(R.layout.certificacion_item, parent, false)
            return ViewHolder(certificacionView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val certificacion = certificaciones[position]
            holder.nombreCertificacion.text = certificacion?.name
            holder.descripcionCertificacion.text = certificacion?.description
        }

        override fun getItemCount(): Int {
            return certificaciones.size
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getCertificaciones() {
        CoroutineScope(Dispatchers.IO).launch {
            val certificacionesRepository = CertificacionesRepository()
            val result: CertificacionesObjeto? = certificacionesRepository.getCertificaciones()

            if (result != null) {
                withContext(Dispatchers.Main) {
                    val adapter = CertificacionesAdapter(result.data.documents)
                    binding.certificacionesList.adapter = adapter
                    binding.certificacionesList.layoutManager = LinearLayoutManager(context)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}