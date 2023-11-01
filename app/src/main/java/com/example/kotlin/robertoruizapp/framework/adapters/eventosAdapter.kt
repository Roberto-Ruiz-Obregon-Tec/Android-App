package com.example.kotlin.robertoruizapp.framework.adapters

import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.framework.view.activities.EventoClickListener
import com.example.kotlin.robertoruizapp.data.network.model.Eventos.Document
import com.example.kotlin.robertoruizapp.databinding.ListElementEventosBinding
import java.text.SimpleDateFormat
import java.util.*

class eventosAdapter (val clickListener: EventoClickListener): RecyclerView.Adapter<eventosAdapter.ViewHolder>() {

    lateinit var data : List<Document>
    var results : Int = 0

    /**
     * function to assign the data provided as param to the current events
     *
     * @param data list of events
     */
    fun eventosAdapter (data: List<Document>) {
            this.data = data

    }

    fun eventosResults (results: Int?) {
        if (results != null) {
            this?.results = results
        }
    }

    /**
     * Overrides the fun onCreateViewHolder to set up the LayoutInflater for the ViewHolder
     * @param viewGroup a ViewGroup object
     * @param i an Integer
     * @return ViewHolder type object
     */
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


        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        if(temp.fecha_Inicio != null){
            val date: Date = inputFormat.parse(temp.fecha_Inicio)
            val formattedDate = outputFormat.format(date)
            viewHolder.eventDate.text = formattedDate
        } else {
            viewHolder.eventDate.text = "Sin fecha"
        }
        viewHolder.eventName.text = temp.nombre
        viewHolder.description.text = temp.descripcion
        viewHolder.eventLocation.text = temp.ubicacion

        Glide.with(viewHolder.itemView.context)
            .load(temp.fotoUrl)
            .into(viewHolder.eventImage)

        viewHolder.eventButton.setOnClickListener(){
            clickListener.onClick(temp)
        }
    }

    override fun getItemCount(): Int {
        val eventos: Int = results
        return eventos
    }

    inner class ViewHolder(itemView: View, private val clickListener: EventoClickListener): RecyclerView.ViewHolder(itemView){
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

    //Auxiliary method
    private fun Button.setOnClickListener(_id: String) {

    }

    //Auxiliary method
    private fun ImageView.setImageDrawable(imageUrl: String) {

    }

}