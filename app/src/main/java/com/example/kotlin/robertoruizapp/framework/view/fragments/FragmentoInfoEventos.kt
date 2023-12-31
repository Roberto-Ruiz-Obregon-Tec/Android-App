package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.kotlin.robertoruizapp.databinding.FragmentoInfoEventosBinding
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.kotlin.robertoruizapp.data.EventRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.kotlin.robertoruizapp.data.network.model.Events.Document
import com.example.kotlin.robertoruizapp.data.network.model.Events.EventObject
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import java.text.SimpleDateFormat
import java.util.*


class FragmentoInfoEventos : Fragment() {
    private lateinit var binding: FragmentoInfoEventosBinding
    private lateinit var currentFragment: Fragment

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, 
        container: ViewGroup?, 
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentoInfoEventosBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val eventID = arguments?.getString("eventID") ?: return
        Log.d("FragmentoInfoEventos", "Event ID: $eventID")
        
        getEventInfo(eventID)
    }
    
    @RequiresApi(Build.VERSION_CODES.N)
    private fun getEventInfo(eventID: String) {
        CoroutineScope(Dispatchers.IO).launch {
            
            try {
                val eventRepository = EventRepository()
                val result: Document? = eventRepository.getEventById(eventID, LoginActivity.token)
                withContext(Dispatchers.Main) {
                    if (result != null) {
                        binding.eventName.text = result.eventName
                        binding.eventDescription.text = result.description
                        binding.eventLocation.text = result.location

                        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
                        val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        val date = inputFormat.parse(result.startDate)
                        val endDate = inputFormat.parse(result.endDate)
                        val formattedDate = outputFormat.format(date)
                        val endFormatDate = outputFormat.format(endDate)

                        binding.eventDate.text = formattedDate
                        // binding.eventEndDate.text = endFormatDate
                    }
                }
            }
            catch (e: Exception) {
               Log.e("FragmentoInfoEventos", "Error: ${e.message}") 
            }
            finally {
                withContext(Dispatchers.Main) {
                }
            }
        }
    }
}
