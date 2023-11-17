package com.example.kotlin.robertoruizapp.framework.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.network.model.Programs.Document
import java.text.SimpleDateFormat
import java.util.Locale

class ProgramsAdapter(private val programas: List<Document?>) :
    RecyclerView.Adapter<ProgramsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombrePrograma: TextView = view.findViewById(R.id.programas_nombre)
        val descripcionPrograma: TextView = view.findViewById(R.id.programas_description)
        val fechaPrograma: TextView = view.findViewById(R.id.fecha_inicio)
        val imagenprograma: ImageView = view.findViewById(R.id.imagen_programa)
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

        if (programa?.programImage?.isNotEmpty() == true) {
            Glide.with(holder.itemView.context)
                .load(programa.programImage)
                .into(holder.imagenprograma)
        } else {

            holder.imagenprograma.setImageResource(R.drawable.curso1)
        }

        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        val startDate = inputFormat.parse(programa?.startDate ?: "")
        val endDate = inputFormat.parse(programa?.endDate ?: "")
        holder.fechaPrograma.text = if (startDate != null && endDate != null) {
            "${outputFormat.format(startDate)} - ${outputFormat.format(endDate)}"
        } else {
            "Fechas no disponibles"
        }

    }


    override fun getItemCount() = programas.size
}