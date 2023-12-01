package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin.robertoruizapp.databinding.FragmentoDetalleDePagoBinding
import com.example.kotlin.robertoruizapp.databinding.FragmentoPagosPorFichaBinding
import com.example.kotlin.robertoruizapp.framework.viewmodel.BankViewModel

class FragmentoPagosPorFicha : Fragment() {
    private var _binding: FragmentoPagosPorFichaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {

        _binding = FragmentoPagosPorFichaBinding.inflate(inflater, container, false)

        val root: View = binding.root

        binding.backContainer.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentoPagosPorFichaBinding.bind(view)

        val cursoId = arguments?.getString("cursoId")
        val userId = arguments?.getString("userId")
        val costoCurso = arguments?.getDouble("costoCurso")

        // Utilizar los datos extraídos
        binding.montoPago.text = "$${costoCurso} MXN"

    }

}