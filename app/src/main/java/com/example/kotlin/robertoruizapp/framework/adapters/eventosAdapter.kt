package com.example.kotlin.robertoruizapp.framework.adapters

import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.network.model.Events.Document
import java.util.EventObject

class EventsAdapter(private val events: List<Document?>) : 
    RecyclerView.Adapter<EventsAdapter.ViewHolder>() {
        
    interface OnItemClickListener {
        fun onItemClick(event: Document)
    }
        
        
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreEvento: TextView = view.findViewById(R.id.event_name)
        val descripcionEvento: TextView = view.findViewById(R.id.event_description)
        val fechaEvento: TextView = view.findViewById(R.id.event_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = events[position]
        holder.nombreEvento.text = event?.name
        holder.descripcionEvento.text = event?.description
        holder.fechaEvento.text = event?.startDate
    }

    override fun getItemCount() = events.size
}