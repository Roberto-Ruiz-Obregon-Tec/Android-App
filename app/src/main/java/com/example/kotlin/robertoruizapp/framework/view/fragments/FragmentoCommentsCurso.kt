package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.kotlin.robertoruizapp.databinding.FragmentoCommentsCursosBinding
import com.example.kotlin.robertoruizapp.databinding.FragmentoCommentsPublicationBinding

class FragmentoCommentsCurso : Fragment() {
    private lateinit var binding: FragmentoCommentsCursosBinding

    private lateinit var currentFragment: Fragment

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentoCommentsCursosBinding.inflate(inflater, container, false)
        return binding.root
    }

}