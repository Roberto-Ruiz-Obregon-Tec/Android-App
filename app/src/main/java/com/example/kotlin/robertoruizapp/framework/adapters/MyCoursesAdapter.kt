package com.example.kotlin.robertoruizapp.framework.adapters

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
import com.example.kotlin.robertoruizapp.framework.view.fragments.FragmentoMyCourses
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Adapter for course list.
 *
 * @param courses List of courses to display in the list.
 */
class MyCoursesAdapter(
    private var courses: List<Document>,
) : RecyclerView.Adapter<MyCoursesAdapter.ViewHolder>() {

    fun updateList(newList: List<Document>) {
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
        Log.d("Cursos", "Cursos: ${curso?.course?.first()?.name}")
        holder.nombreCurso.text = curso?.course?.first()?.name
        holder.descripcionCurso.text = curso?.course?.first()?.description

        if (curso?.course?.first()?.courseImage?.isNotEmpty() == true) {
            Glide.with(holder.itemView.context)
                .load(curso?.course?.first()?.courseImage)
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
