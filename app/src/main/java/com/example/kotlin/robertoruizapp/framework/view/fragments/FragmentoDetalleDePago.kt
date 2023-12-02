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

/**
 * Fragment for displaying payment details and banks.
 */
class FragmentoDetalleDePago : Fragment() {

    private var _binding: FragmentoDetalleDePagoBinding? = null
    private val binding get() = _binding!!
    val cursoId = arguments?.getString("cursoId")
    private var costoCurso: Double = 0.0
    private var userId: String = ""

    private lateinit var viewModelBank: BankViewModel
    private lateinit var recyclerView: RecyclerView
    private val adapter : BankAdapter = BankAdapter()

    /**
     * Inflates the fragment's layout and initializes its components.
     *
     * @param inflater The LayoutInflater used to inflate the layout.
     * @param container The parent view in which the fragment should be attached.
     * @param savedInstanceState The saved instance state of the fragment.
     * @return The root view of the fragment.
     */
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
        costoCurso = arguments?.getDouble("costoCurso", 0.0) ?: 0.0
        userId = arguments?.getString("userId") ?: ""

        binding.montoPago.text = "$${costoCurso} MXN"


        binding.backContainer.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        setupEnrollButton()
        return root
    }

    /**
     * Sets up the behavior of the "Enroll" button.
     */
    private fun setupEnrollButton() {
        binding.btnEnroll.setOnClickListener {
            val curso = arguments?.getString("cursoId")
            val bundle = Bundle().apply {
                putString("cursoId", curso)
                putDouble("costoCurso", costoCurso)
                putString("userId", userId)
            }
            navigateToPagosPorFicha(bundle)
        }
    }

    /**
     * Navigates to the "PagosPorFicha" fragment with the provided bundle.
     *
     * @param bundle The Bundle containing data to pass to the target fragment.
     */
    private fun navigateToPagosPorFicha(bundle: Bundle) {
        val pagosPorFichaFragment = FragmentoPagosPorFicha().apply {
            arguments = bundle.apply {
                putDouble("costoCurso", costoCurso)
            }
        }
        parentFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_content_main, pagosPorFichaFragment)
            .addToBackStack(null)
            .commit()
    }

    /**
     * Observes changes in bank data and updates the RecyclerView accordingly.
     */
    private fun observeBanks() {
        viewModelBank.bankList.observe(viewLifecycleOwner){result ->
            if ( result != null ){
                setupRecyclerView(result)
            } else{
                Log.d("prueba", "nulo: ${result}")
            }
        }
    }

    /**
     * Sets up the RecyclerView to display bank data.
     *
     * @param dataForList The list of [BankDocument] objects to display.
     */
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

    /**
     * Initializes the components of the fragment.
     *
     * @param root The root view of the fragment.
     */
    private fun initializeComponents(root: View){
        recyclerView = root.findViewById(R.id.bancos_list)
    }

    /**
     * Cleans up resources when the view is destroyed.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}