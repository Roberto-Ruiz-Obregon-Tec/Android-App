package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.network.model.BankAccount.BankDocument
import com.example.kotlin.robertoruizapp.databinding.FragmentoDetalleDePagoBinding
import com.example.kotlin.robertoruizapp.framework.adapters.BankAdapter
import com.example.kotlin.robertoruizapp.framework.viewmodel.BankViewModel

class FragmentoDetalleDePago : Fragment(R.layout.fragmento_detalle_de_pago) {

    private var _binding: FragmentoDetalleDePagoBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModelBank: BankViewModel
    private lateinit var recyclerView: RecyclerView
    private val adapter : BankAdapter = BankAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentoDetalleDePagoBinding.bind(view)

        observeBanks()

        viewModelBank.getListBanks()
    }

    private fun setupRecyclerView(dataForList:ArrayList<BankDocument>) {
        recyclerView.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false)
        recyclerView.layoutManager = linearLayoutManager
        adapter.BankAdapter(dataForList, requireContext())
        recyclerView.adapter = adapter
    }

    private fun observeBanks() {

        viewModelBank.bankList.observe(viewLifecycleOwner){result ->
            if ( result != null ){
                setupRecyclerView(result)
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}