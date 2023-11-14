package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.kotlin.robertoruizapp.R
import androidx.fragment.app.Fragment
import com.example.kotlin.robertoruizapp.databinding.FragmentoCursoDetallesBinding


class FragmentoCursoDetalles: Fragment() {
    private var _binding: FragmentoCursoDetallesBinding? = null

    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cursoId = arguments?.getString("CURSO_ID")
        // Aquí puedes usar cursoId para cargar los detalles del curso
    }


}