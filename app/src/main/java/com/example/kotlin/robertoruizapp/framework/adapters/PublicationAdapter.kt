package com.example.kotlin.robertoruizapp.framework.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.network.model.publication.Document
import com.example.kotlin.robertoruizapp.framework.view.fragments.FragmentoFeed
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

class PublicationAdapter(private val publicaciones: List<Document?>,
                         private val commentClickListener: OnCommentClickListener
) :
    RecyclerView.Adapter<PublicationAdapter.ViewHolder>() {
    interface OnCommentClickListener {
        fun OnCommentClicked(publicationId: String)
    }

    class ViewHolder(view: View, private val commentClickListener:OnCommentClickListener) : RecyclerView.ViewHolder(view) {
        val nombrePublicacion: TextView = view.findViewById(R.id.titulo_programa)
        val descripcionPublicacion: TextView = view.findViewById(R.id.programa_description)
        val verMas: TextView = view.findViewById(R.id.ver_mas)
        val fechapublicacion: TextView = view.findViewById(R.id.fecha_publicacion)
        val likesTextView: TextView = view.findViewById(R.id.like_total)
        val imagenpublicacion: ImageView = view.findViewById(R.id.imagenpublicacion)

        fun bind(publicacion: Document?) {
            nombrePublicacion.text = publicacion?.title
            descripcionPublicacion.text = publicacion?.description
            likesTextView.text = publicacion?.likes.toString()

            if (publicacion?.image?.isNotEmpty() == true) {
                Glide.with(itemView.context)
                    .load(publicacion.image)
                    .into(imagenpublicacion)
            } else {
                imagenpublicacion.setImageResource(R.drawable.curso1)
            }

            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
            val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
            val createdAt = publicacion?.createdAt

            val startDate = if (!createdAt.isNullOrEmpty()) {
                try {
                    inputFormat.parse(createdAt)
                } catch (e: ParseException) {
                    null
                }
            } else {
                null
            }

            fechapublicacion.text = startDate?.let {
                outputFormat.format(it)
            } ?: "Fecha no disponible"

            // Lógica para "Ver más/Ver menos"
            val descripcion = publicacion?.description.orEmpty()
            if (descripcion.length > 30) {
                descripcionPublicacion.text = "${descripcion.take(30)}..."
                verMas.visibility = View.VISIBLE
                verMas.text = "Ver más"
                verMas.setOnClickListener {
                    if (verMas.text == "Ver más") {
                        descripcionPublicacion.text = descripcion
                        verMas.text = "Ver menos"
                    } else {
                        descripcionPublicacion.text = "${descripcion.take(30)}..."
                        verMas.text = "Ver más"
                    }
                    verMas.visibility = View.GONE
                }
            } else {
                descripcionPublicacion.text = descripcion
                verMas.visibility = View.GONE
            }
            itemView.findViewById<LinearLayout>(R.id.boton_comentar).setOnClickListener {
                publicacion?._id?.let { id ->
                    commentClickListener.OnCommentClicked(id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_publication, parent, false)
        return ViewHolder(view, commentClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val publicacion = publicaciones[position]
        holder.bind(publicacion)
    }

    override fun getItemCount() = publicaciones.size
}
