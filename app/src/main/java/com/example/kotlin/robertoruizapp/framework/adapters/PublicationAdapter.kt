package com.example.kotlin.robertoruizapp.framework.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.network.model.publication.Document

/**
 * Adapter for company list.
 *
 * This adapter is responsible for linking the companies' data with the view in the RecyclerView.
 *
 * @param companies List of company documents that will be displayed.
 */
class PublicationAdapter(private val empresas: List<Document?>) :
    RecyclerView.Adapter<PublicationAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreEmpresa: TextView = view.findViewById(R.id.titulo_programa)
        val descripcionEmpresa: TextView = view.findViewById(R.id.programa_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_publication, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val empresa = empresas[position]
        //Log.d("EmpresaAdapter", "Certificaciones: ${empresa?.certifications}")
        holder.nombreEmpresa.text = empresa?.title
        holder.descripcionEmpresa.text = empresa?.description
    }


    override fun getItemCount() = empresas.size
}