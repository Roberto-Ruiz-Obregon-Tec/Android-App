package com.example.kotlin.robertoruizapp.framework.adapters.viewholder

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.network.model.MyCourses.Document
import com.example.kotlin.robertoruizapp.data.network.model.MyCourses.MyCourseObject
import com.example.kotlin.robertoruizapp.data.network.model.MyCourses.course
import com.example.kotlin.robertoruizapp.databinding.ItemMyCoursesBinding

class MyCoursesViewHolder(private val binding: ItemMyCoursesBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: course){
        binding.cursosList.text = item.name
        binding.cursoDescription.text = item.description

        if (item.courseImage.isNotEmpty()) {
            Glide.with(binding.root.context)
                .load(item.courseImage)
                .into(binding.imagencursos)
        } else {
            binding.imagencursos.setImageResource(R.drawable.curso1)
        }

        Log.d("nameCourse", item.name)
    }
}