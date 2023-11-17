package com.example.kotlin.robertoruizapp.framework.adapters

import android.util.Log
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.TextView
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.network.model.Events.Document
import com.example.kotlin.robertoruizapp.framework.view.fragments.FragmentoFeed
import java.util.EventObject

interface OnEventClickListener {
    fun onEventClick(eventID: String)
}

class EventsAdapter(
    private val events: List<Document?>, 
    private val itemClickListener: OnEventClickListener
): RecyclerView.Adapter<EventsAdapter.ViewHolder>() {
        
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val eventName: TextView = view.findViewById(R.id.name)
        val eventDescription: TextView = view.findViewById(R.id.description)
        val eventDate: TextView = view.findViewById(R.id.date)
        val eventLocation: TextView = view.findViewById(R.id.location)
        val btnVerMas: Button = view.findViewById(R.id.btnVerMas)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = events[position]
        holder.eventName.text = event?.eventName
        holder.eventDescription.text = event?.description
        holder.eventDate.text = event?.startDate
        holder.eventLocation.text = event?.location

        holder.btnVerMas.setOnClickListener {
            event?._id?.let { id ->
                Log.d("EventsAdapter", "Show more clicked for event ID: $id")
                itemClickListener.onEventClick(id)
            }
        }
    }

    override fun getItemCount() = events.size
}