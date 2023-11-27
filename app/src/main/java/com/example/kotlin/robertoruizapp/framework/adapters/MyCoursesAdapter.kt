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
class MyCoursesAdapter() : RecyclerView.Adapter<MyCoursesViewHolder>() {
    var data: ArrayList<Document> = ArrayList()
    lateinit var context: Context

    fun MyCourseAdapter(basicData: List<MyCourseObject>, context: Context){
        this.data = basicData as ArrayList<Document>
        this.context = context
    }

    override fun onBindViewHolder(holder: MyCoursesViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    /**
     * Method called when a new ViewHolder is created.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCoursesViewHolder {
        val binding = ItemMyCoursesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyCoursesViewHolder(binding)
    }

    /**
     * Method called to get the number of elements in the list.
     */
    override fun getItemCount() = data.size
}
