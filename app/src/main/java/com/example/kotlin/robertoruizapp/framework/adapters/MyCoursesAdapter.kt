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


    /**
     * ViewHolder for course list items. Here I call it so that it appears in item_cursos.xml
     *
     * @param view View of a list item.
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreCurso: TextView = view.findViewById(R.id.cursos_list)
        val descripcionCurso: TextView = view.findViewById(R.id.curso_description)
        val imagencurso: ImageView = view.findViewById(R.id.imagencursos)

    }

    /**
     * Method called when a new ViewHolder is created.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_my_courses, parent, false)
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


        //Put this when you have the correct urls of the images
        //Glide.with(holder.imagenCurso.context).load(curso?.courseImage).into(holder.imagenCurso)


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
