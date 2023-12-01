package com.example.kotlin.robertoruizapp.framework.adapters

import androidx.recyclerview.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.network.model.Events.Document
import com.example.kotlin.robertoruizapp.framework.view.fragments.FragmentoEventos
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale



class EventsAdapter(
    private val events: List<Document?>,
    private val itemClickListener: FragmentoEventos.OnEventClickListener
) : RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val eventName: TextView = view.findViewById(R.id.name)
        val eventDescription: TextView = view.findViewById(R.id.description)
        val eventDate: TextView = view.findViewById(R.id.date)
        val eventLocation: TextView = view.findViewById(R.id.location)
        //val btnVerMas: Button = view.findViewById(R.id.btnVerMas)
        val imagenevento: ImageView = view.findViewById(R.id.imgCertificacion)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = events[position]
        holder.eventName.text = event?.eventName
        holder.eventDescription.text = event?.description
        holder.eventLocation.text = event?.location

        holder.eventDate.text = formatEventDate(event?.startDate)

//        holder.btnVerMas.setOnClickListener {
//            event?._id?.let { id ->
//                itemClickListener.onEventClick(id)
//            }
//        }
        if (event?.imageUrl?.isNotEmpty() == true) {
            Glide.with(holder.itemView.context)
                .load(event.imageUrl)
                .into(holder.imagenevento)
        } else {
            holder.imagenevento.setImageResource(R.drawable.curso1)
        }
    }
    override fun getItemCount() = events.size

    private fun formatEventDate(dateString: String?): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        return try {
            val date = inputFormat.parse(dateString)
            outputFormat.format(date)
        } catch (e: ParseException) {
            "Fecha no disponible"
        } catch (e: NullPointerException) {
            "Fecha no disponible"
        }
    }
}