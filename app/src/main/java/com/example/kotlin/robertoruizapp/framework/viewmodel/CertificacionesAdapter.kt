package com.example.kotlin.robertoruizapp.framework.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.network.model.certificaciones.Document


/*
    * A class that represents a certification adapter.
    * @param certificaciones: A List<UserDocument> object.
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