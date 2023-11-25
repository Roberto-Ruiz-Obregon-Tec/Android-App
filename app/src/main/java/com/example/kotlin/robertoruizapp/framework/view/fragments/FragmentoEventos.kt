package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.EventRepository
import com.example.kotlin.robertoruizapp.data.network.model.Events.EventObject
import com.example.kotlin.robertoruizapp.databinding.FragmentoEventosBinding
import com.example.kotlin.robertoruizapp.framework.adapters.EventsAdapter
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentoEventos : Fragment() {
    private var _binding: FragmentoEventosBinding? = null
    private val binding get() = _binding!!

    interface OnEventClickListener {
        fun onEventClick(eventID: String)
    }

    private val onEventClickListener = object : OnEventClickListener {
        override fun onEventClick(eventID: String) {
            val fragmentDetails = FragmentoInfoEventos().apply {
                arguments = Bundle().apply {
                    putString("eventID", eventID)
                }
            }

            parentFragmentManager.beginTransaction().apply {
                replace(R.id.nav_host_fragment_content_main, fragmentDetails)
                addToBackStack(null)
                commit()
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentoEventosBinding.inflate(inflater, container, false)
        return binding.root
    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.eventosList.layoutManager = LinearLayoutManager(context)
        binding.eventosList.adapter = EventsAdapter(emptyList(), onEventClickListener)
        getEvents()
    }
    // Get events
    private fun getEvents() {
        showProgressBar()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val eventsRepository = EventRepository()
                val result: EventObject? = eventsRepository.getEvent(LoginActivity.token)

                withContext(Dispatchers.Main) {
                    if (result != null && isAdded) {
                        val adapter = EventsAdapter(result.data.documents, onEventClickListener)
                        binding.eventosList.adapter = adapter
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("FragmentoEventos", "Error al obtener eventos", e)
                    // Considera mostrar un mensaje de error al usuario aquí
                }
            } finally {
                withContext(Dispatchers.Main) {
                    if (isAdded) {
                        hideProgressBar()
                    }
                }
            }
        }
    }
    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.eventosList.visibility = View.INVISIBLE // Ocultar la lista mientras carga
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
        binding.eventosList.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}