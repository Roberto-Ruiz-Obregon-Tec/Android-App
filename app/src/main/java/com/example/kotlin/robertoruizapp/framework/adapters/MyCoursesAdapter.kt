package com.example.kotlin.robertoruizapp.framework.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.network.model.MyCourses.Document
import com.example.kotlin.robertoruizapp.data.network.model.MyCourses.MyCourseObject
import com.example.kotlin.robertoruizapp.data.network.model.MyCourses.course
import com.example.kotlin.robertoruizapp.databinding.ItemMyCoursesBinding
import com.example.kotlin.robertoruizapp.framework.adapters.viewholder.MyCoursesViewHolder
import com.example.kotlin.robertoruizapp.framework.view.fragments.FragmentoMyCourses
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Adapter for course list.
 *
 * @param courses List of courses to display in the list.
 */
class MyCoursesAdapter(
    private var courses: List<course>,
) : RecyclerView.Adapter<MyCoursesAdapter.ViewHolder>() {

    fun updateList(newList: List<course>) {
        courses = newList
        notifyDataSetChanged()
    }


    /**
     * ViewHolder for course list items. Here I call it so that it appears in item_cursos.xml
     *
     * @param view View of a list item.
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreCurso: TextView = view.findViewById(R.id.cursos_list)
        val descripcionCurso: TextView = view.findViewById(R.id.curso_description)
        //val fechaInicioCurso: TextView = view.findViewById(R.id.curso_fecha_inicio)
        //val fechaFinCurso: TextView = view.findViewById(R.id.curso_fecha_fin)
        //Put this when you have the correct urls of the images
        //val imagenCurso: ImageView = view.findViewById(R.id.curso_imagen)
        val fechaCurso: TextView = view.findViewById(R.id.curso_fecha)
        val costoCurso: TextView = view.findViewById(R.id.curso_costo)
        val modalidadCurso: TextView = view.findViewById(R.id.curso_modalidad)
        val imagencurso: ImageView = view.findViewById(R.id.imagencursos)

    }

    /**
     * Method called when a new ViewHolder is created.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cursos, parent, false)
        return ViewHolder(view)

    }

    /**
     * Method called to display the data in a specific ViewHolder. This is what I have in my document list.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curso = courses[position]
        Log.d("Cursos", "Cursos: ${curso?.name}")
        holder.nombreCurso.text = curso?.name
        holder.descripcionCurso.text = curso?.description

        // Convert and display the date
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        val startDate = inputFormat.parse(curso?.startDate ?: "")
        val endDate = inputFormat.parse(curso?.endDate ?: "")
        holder.fechaCurso.text = if (startDate != null && endDate != null) {
            "${outputFormat.format(startDate)} - ${outputFormat.format(endDate)}"
        } else {
            "Fechas no disponibles"
        }

        //Put this when you have the correct urls of the images
        //Glide.with(holder.imagenCurso.context).load(curso?.courseImage).into(holder.imagenCurso)

        // Show the cost
        if (curso?.cost == 0.0) {
            holder.costoCurso.text = "Gratuito"
        } else {
            holder.costoCurso.text = "$${curso?.cost} MXN"
        }

        holder.modalidadCurso.text = curso?.modality

        holder.itemView.findViewById<Button>(R.id.btn_ver_mas).setOnClickListener {
            Log.d("click", "click")
        }

        if (curso?.courseImage?.isNotEmpty() == true) {
            Glide.with(holder.itemView.context)
                .load(curso.courseImage)
                .into(holder.imagencurso)
        } else {
            holder.imagencurso.setImageResource(R.drawable.curso1)
        }
    }

    /**
     * Method called to get the number of elements in the list.
     */
    override fun getItemCount() = courses.size
}
