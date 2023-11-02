package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin.robertoruizapp.databinding.FragmentoBecasBinding
import com.example.kotlin.robertoruizapp.databinding.FragmentoInicioBinding
import com.example.kotlin.robertoruizapp.framework.viewmodel.BecasViewModel
import com.example.kotlin.robertoruizapp.framework.viewmodel.InicioViewModel
import com.example.kotlin.robertoruizapp.utils.PreferenceHelper

class FragmentoBecas : Fragment() {
    private var _binding: FragmentoBecasBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: BecasViewModel
    private val preferences by lazy {
        PreferenceHelper.defaultPrefs(this@FragmentoBecas.requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[BecasViewModel::class.java]
        _binding = FragmentoBecasBinding.inflate(inflater, container, false)

        val root: View = binding.root
        return root
    }
}