package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        val view = binding.root


        binding.faceBook.setOnClickListener {
            openFacebookPage()
        }

        binding.instagram.setOnClickListener{
            openInstagramPage()
        }

        return view
    }

    private fun openFacebookPage() {
        val url = "https://www.facebook.com/fundacionruizobregon" // Reemplaza con la URL de tu página de Facebook
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)

        // Verifica si hay una aplicación que pueda manejar el intent
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            // Muestra un mensaje de error o realiza una acción alternativa
            Toast.makeText(context, "No se encontró una aplicación para abrir el enlace", Toast.LENGTH_SHORT).show()
        }
    }
    private fun openInstagramPage() {
        val url = "https://www.instagram.com/frobertoruizobregon/" // Reemplaza con la URL de tu página de Facebook
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)

        // Verifica si hay una aplicación que pueda manejar el intent
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            // Muestra un mensaje de error o realiza una acción alternativa
            Toast.makeText(context, "No se encontró una aplicación para abrir el enlace", Toast.LENGTH_SHORT).show()
        }
    }
}