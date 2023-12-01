package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.network.model.Course.Document
import com.example.kotlin.robertoruizapp.databinding.FragmentoDetalleDePagoBinding
import com.example.kotlin.robertoruizapp.domain.BankListRequirement
import com.example.kotlin.robertoruizapp.framework.adapters.BankAdapter
import com.example.kotlin.robertoruizapp.framework.viewmodel.BankViewModel
import com.example.kotlin.robertoruizapp.framework.viewmodel.BankViewModelFactory

class FragmentoDetalleDePago : Fragment(R.layout.fragmento_detalle_de_pago) {

    private var _binding: FragmentoDetalleDePagoBinding? = null
    private val binding get() = _binding!!
    val cursoId = arguments?.getString("cursoId")

    private val viewModel: BankViewModel by viewModels {
        BankViewModelFactory(BankListRequirement())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentoDetalleDePagoBinding.bind(view)

        setupRecyclerView()
        observeBanks()

        val costoCurso = arguments?.getDouble("costoCurso", 0.0) ?: 0.0
        val userId = arguments?.getString("userId") ?: ""

        binding.montoPago.text = "$${costoCurso} MXN"

        binding.backContainer.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun setupRecyclerView() {
        binding.bancosList.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeBanks() {
        viewModel.bankList.observe(viewLifecycleOwner) { bankList ->
            binding.bancosList.adapter = BankAdapter(bankList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}