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
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        * @see Document
        *
        *
     */
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCertificaciones()
    }

    /*
    * A class that represents a certification adapter.
    * @param certificaciones: A List<Document> object.
    * @return None.
    * @see ViewHolder
    *
    * Methods:
    * onCreateViewHolder(): A function that is called when the view holder is created each time the recycler view is created.
    * @param parent: A ViewGroup object.
    * @param viewType: An Int object.
    * @return a ViewHolder object.
    *
    * onBindViewHolder(): A function that is called when the view holder is bound to the recycler view.
    * @param holder: A ViewHolder object.
    * @param position: An Int object.
    * @return None.
    *
    * getItemCount(): A function that returns the number of items in the recycler view.
    * @param None.
    * @return an Int object.
     */

    class CertificacionesAdapter(private val certificaciones: List<Document?>) :
        RecyclerView.Adapter<CertificacionesAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val nombreCertificacion: TextView = view.findViewById(R.id.certificacion_list)
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

    /*
        * A suspend function that gets the certification data from the repository.
        * @param None.
        * @return a CertificacionesObjeto object containing the certification data.
        * @see CertificacionesObjeto
        * @see CertificacionesRepository
        * @see Document
     */
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