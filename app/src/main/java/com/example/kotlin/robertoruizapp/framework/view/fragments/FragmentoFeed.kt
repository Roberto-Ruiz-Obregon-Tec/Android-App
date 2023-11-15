package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.annotation.SuppressLint
import android.os.Build
import com.example.kotlin.robertoruizapp.databinding.FragmentoFeedBinding
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.EventRepository
import com.example.kotlin.robertoruizapp.data.Repository
import com.example.kotlin.robertoruizapp.data.companyCertificationRepository
import com.example.kotlin.robertoruizapp.data.network.model.Events.EventObject
import com.example.kotlin.robertoruizapp.data.network.model.companyCertification.Document
import com.example.kotlin.robertoruizapp.data.network.model.companyCertification.CertificacionEmpresaObj
import com.example.kotlin.robertoruizapp.framework.adapters.EventsAdapter
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * This fragment shows the companies with their certifications and events
 *
 * @property _binding Variable for the automatically generated view binding for this fragment.
 * @property binding Access property to `_binding` that ensures it is not null.
 */
class FragmentoFeed : Fragment() {

    private var _binding: FragmentoFeedBinding? = null
    private val binding get() = _binding!!

    /**
     * Create the fragment view.
     *
     *
     * @param inflater The LayoutInflater used to inflate the fragment.
     * @param container The container where the fragment will be inserted.
     * @param savedInstanceState A Bundle containing data from the previous state of the fragment.
     * @return The root view of the inflated fragment.
     */

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentoFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Called once the view has been created.
     *
     * Configure the button listeners and perform the first upload of certification data.
     *
     * @param view The root view of the fragment.
     * @param savedInstanceState A Bundle containing data from the previous state of the fragment.
     */

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getEvents()

        binding.button3.setOnClickListener {
            getCompanyCertification()

            // Cambia el drawable y color del texto de button3
            binding.button3.setBackgroundResource(R.drawable.button_active)
            binding.button3.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

            // Cambia el drawable y color del texto de button1
            binding.button1.setBackgroundResource(R.drawable.button_inactive)
            binding.button1.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        }

        binding.button1.setOnClickListener {
            getEvents()

            // Cambia el drawable y color del texto de button1
            binding.button1.setBackgroundResource(R.drawable.button_active)
            binding.button1.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

            // Cambia el drawable y color del texto de button3
            binding.button3.setBackgroundResource(R.drawable.button_inactive)
            binding.button3.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        }
    }

    /**
     * Adapter for company list.
     *
     * This adapter is responsible for linking the companies' data with the view in the RecyclerView.
     *
     * @param companies List of company documents that will be displayed.
     */
    class EmpresaAdapter(private val empresas: List<Document?>) :
        RecyclerView.Adapter<EmpresaAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val nombreEmpresa: TextView = view.findViewById(R.id.empresa_nombre)
            val descripcionEmpresa: TextView = view.findViewById(R.id.empresa_description)
            val certificacionEmpresa: TextView = view.findViewById(R.id.empresa_certificacion)
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

        }


        override fun getItemCount() = empresas.size
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
                val result: CertificacionEmpresaObj? = companyCertificationRepository.getCompanyCertification(LoginActivity.token)


                    if (result != null) {
                        withContext(Dispatchers.Main) {
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
            try {
                val eventsRepository = EventRepository()
                val result: EventObject? = eventsRepository.getEvent(LoginActivity.token)

                if (result != null) {
                   withContext(Dispatchers.Main) {
                        val adapter = EventsAdapter(result.data.documents)
                        binding.empresaList.adapter = adapter
                        binding.empresaList.layoutManager = LinearLayoutManager(context)
                    } 
                }
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
            finally {
                withContext(Dispatchers.Main) {
                    hideProgressBar()
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