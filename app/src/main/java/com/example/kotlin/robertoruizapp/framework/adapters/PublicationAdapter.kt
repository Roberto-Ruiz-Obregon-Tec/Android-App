package com.example.kotlin.robertoruizapp.framework.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.network.model.publication.Document
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

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
        val fechapublicacion: TextView = view.findViewById(R.id.fecha_publicacion)
        val likesTextView: TextView = view.findViewById(R.id.like_total)
        val imagenpublicacion: ImageView = view.findViewById(R.id.imagenpublicacion)

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
        holder.likesTextView.text = empresa?.likes.toString()

        if (empresa?.image?.isNotEmpty() == true) {
            Glide.with(holder.itemView.context)
                .load(empresa.image)
                .into(holder.imagenpublicacion)
        } else {

            holder.imagenpublicacion.setImageResource(R.drawable.curso1)
        }


        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        val createdAt = empresa?.createdAt

        val startDate = if (!createdAt.isNullOrEmpty()) {
            try {
                inputFormat.parse(createdAt)
            } catch (e: ParseException) {
                null
            }
        } else {
            null
        }

        holder.fechapublicacion.text = startDate?.let {
            outputFormat.format(it)
        } ?: "Fecha no disponible"




    }


    override fun getItemCount() = empresas.size
}