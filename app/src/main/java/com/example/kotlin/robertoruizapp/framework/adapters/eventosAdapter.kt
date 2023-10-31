package com.example.kotlin.robertoruizapp.framework.adapters

import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide //Not in use
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.framework.view.activities.EventoClickListener
import com.example.kotlin.robertoruizapp.data.network.model.Eventos.Document
import com.example.kotlin.robertoruizapp.databinding.ItemProgramaBinding //IDK what is this
import java.text.SimpleDateFormat // ??
import java.util.*

class eventosAdapter (val clickListener: EventosClickListener): RecyclerView.Adapter<eventosAdapter.ViewHolder>() {

    lateinit var data : List<Document>
    var results : Int = 0

    fun eventosAdapter (data: List<Document>) {
            this.data = data

    }

    fun eventosResults (results: Int?) {
        if (results != null) {
            this?.results = results
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int):ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_element_eventos, viewGroup, false)
        return ViewHolder(v, clickListener)
    }

    /**
     * Overrides the fun onBindViewHolder to assign the information to the cell view
     *
     * @param viewHolder a ViewHolder object to assign the values
     * @param i an Integer
     */
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) { //Missing stuff
        var temp: Document = data[i]

        viewHolder.bind(temp, clickListener)
    }

    override fun getItemCount(): Int {
        val eventos: Int = results
        return eventos
    }

    inner class ViewHolder(itemView: View, private val clickListener: EventClickListener): RecyclerView.ViewHolder(itemView){
        val eventName: TextView
        val description: TextView
        val eventDate: TextView
        val eventImage: ImageView
        val eventLocation: TextView
        val eventButton: Button

        init {
            eventName = itemView.findViewById(R.id.txtEvento)
            description = itemView.findViewById(R.id.txtDescripcion)
            eventDate = itemView.findViewById(R.id.txtFecha)
            eventImage = itemView.findViewById(R.id.imgEvento)
            eventLocation = itemView.findViewById(R.id.txtUbicacion)
            eventButton = itemView.findViewById(R.id.btnVerMas)
        }
    }


}