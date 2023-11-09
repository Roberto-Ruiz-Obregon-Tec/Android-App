package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.kotlin.robertoruizapp.databinding.FragmentoFrroBinding

class FragmentoFRRO : Fragment() {
    private var _binding: FragmentoFrroBinding? = null
    private val binding get() = _binding!!

    /**
     * Create the fragment view.
     *
     *
     * @param inflater The LayoutInflater used to inflate the fragment.
     * @param container The container where the fragment will be inserted.
     * @param savedInstanceState A Bundle containing data from the previous state of the fragment.
     * @return The root view of the inflated fragment.
     */

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentoFrroBinding.inflate(inflater, container, false)
        return binding.root
    }
}