package com.example.kotlin.robertoruizapp.framework.adapters.viewholder

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.network.model.MyCourses.Document
import com.example.kotlin.robertoruizapp.data.network.model.MyCourses.MyCourseObject
import com.example.kotlin.robertoruizapp.databinding.ItemMyCoursesBinding

class MyCoursesViewHolder(private val binding: ItemMyCoursesBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Document){
        binding.cursosList.text = item.course.name
        binding.cursoDescription.text = item.course.description

        if (item.course.courseImage.isNotEmpty()) {
            Glide.with(binding.root.context)
                .load(item.course.courseImage)
                .into(binding.imagencursos)
        } else {
            binding.imagencursos.setImageResource(R.drawable.curso1)
        }

        Log.d("nameCourse", item.course.name)
    }
}