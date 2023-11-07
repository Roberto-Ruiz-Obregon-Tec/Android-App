package com.example.kotlin.robertoruizapp.framework.adapters

import androidx.recyclerview.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.network.model.Becas.Document


class ScholarshipAdapter(private val becas: List<Document>) :
    RecyclerView.Adapter<ScholarshipAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val becaName: TextView = view.findViewById(R.id.titulo_beca_card)
        val becaDescription: TextView = view.findViewById(R.id.des_beca)
        val becaDate: TextView = view.findViewById(R.id.fecha_beca)
        val becaSector: TextView = view.findViewById(R.id.tipo_beca)
        val becaOrga: TextView = view.findViewById(R.id.orga_beca)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_element_becas, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beca = becas[position]
        holder.becaName.text = beca?.name
        holder.becaDescription.text = beca?.description
        holder.becaDate.text = "25/12/2023"
        holder.becaSector.text = beca?.sector
        holder.becaOrga.text = beca?.organization
    }

    override fun getItemCount() = becas.size
}