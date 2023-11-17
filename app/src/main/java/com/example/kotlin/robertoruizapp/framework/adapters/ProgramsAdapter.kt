package com.example.kotlin.robertoruizapp.framework.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.network.model.Programs.Document

class ProgramsAdapter(private val programas: List<Document?>) :
    RecyclerView.Adapter<ProgramsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombrePrograma: TextView = view.findViewById(R.id.programas_nombre)
        val descripcionPrograma: TextView = view.findViewById(R.id.programas_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_programas, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val programa = programas[position]
        //Log.d("EmpresaAdapter", "Certificaciones: ${empresa?.certifications}")
        holder.nombrePrograma.text = programa?.name
        holder.descripcionPrograma.text = programa?.description

    }


    override fun getItemCount() = programas.size
}