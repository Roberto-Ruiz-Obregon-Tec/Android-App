package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.network.model.BankAccount.BankDocument
import com.example.kotlin.robertoruizapp.databinding.FragmentoDetalleDePagoBinding
import com.example.kotlin.robertoruizapp.framework.adapters.BankAdapter
import com.example.kotlin.robertoruizapp.framework.viewmodel.BankViewModel
import android.view.LayoutInflater
import android.view.ViewGroup

class FragmentoDetalleDePago : Fragment() {

    private var _binding: FragmentoDetalleDePagoBinding? = null
    private val binding get() = _binding!!
    val cursoId = arguments?.getString("cursoId")

    private lateinit var viewModelBank: BankViewModel
    private lateinit var recyclerView: RecyclerView
    private val adapter : BankAdapter = BankAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        viewModelBank = ViewModelProvider(this)[BankViewModel::class.java]
        _binding = FragmentoDetalleDePagoBinding.inflate(inflater, container, false)

        val root: View = binding.root

        observeBanks()
        initializeComponents(root )

        viewModelBank.getListBanks()
        val costoCurso = arguments?.getDouble("costoCurso", 0.0) ?: 0.0
        val userId = arguments?.getString("userId") ?: ""

        binding.montoPago.text = "$${costoCurso} MXN"

        binding.backContainer.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return root
    }

    private fun observeBanks() {
        viewModelBank.bankList.observe(viewLifecycleOwner){result ->
            if ( result != null ){
                setupRecyclerView(result)
            } else{
                Log.d("prueba", "nulo: ${result}")
            }
        }
    }

    private fun setupRecyclerView(dataForList:ArrayList<BankDocument>) {
        Log.d("prueba", "setupreclycler: ${dataForList}")
        recyclerView.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false)
        recyclerView.layoutManager = linearLayoutManager
        adapter.BankAdapter(dataForList, requireContext())
        recyclerView.adapter = adapter
    }

    private fun initializeComponents(root: View){
        recyclerView = root.findViewById(R.id.bancos_list)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}