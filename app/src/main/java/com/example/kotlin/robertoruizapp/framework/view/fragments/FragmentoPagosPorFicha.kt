package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin.robertoruizapp.databinding.FragmentoDetalleDePagoBinding
import com.example.kotlin.robertoruizapp.databinding.FragmentoPagosPorFichaBinding
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import com.example.kotlin.robertoruizapp.framework.viewmodel.BankViewModel
import com.example.kotlin.robertoruizapp.framework.viewmodel.FichaPagoViewModel

/**
 * Fragment for handling payments by voucher.
 */
class FragmentoPagosPorFicha : Fragment() {
    private var _binding: FragmentoPagosPorFichaBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FichaPagoViewModel

    /**
     * Called when the fragment's view is created.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {

        _binding = FragmentoPagosPorFichaBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[FichaPagoViewModel::class.java]

        val root: View = binding.root

        binding.backContainer.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        initializeObservers()

        return root
    }

    /**
     * Called when the fragment's view is created and ready.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentoPagosPorFichaBinding.bind(view)

        val cursoId = arguments?.getString("cursoId")
        val costoCurso = arguments?.getDouble("costoCurso")

        binding.montoPago.text = "$${costoCurso} MXN"

        binding.btnEnroll.setOnClickListener {
            val num = binding.idVoucher.text.toString()
            val token: String = LoginActivity.token

            if (cursoId != null) {
                Log.d("send","1 ${token} 1 ${cursoId} 1 ${num}" )
                viewModel.sendNum(token,cursoId,num)
            }
        }

    }

    /**
     * Initializes observers to listen for changes in ViewModel data.
     */
    private fun initializeObservers() {

        viewModel.voucherLiveData.observe(viewLifecycleOwner) {result ->
            if (result != null){
                Log.d("prueba", result)

                context?.let {
                    AlertDialog.Builder(it)
                        .setTitle("Confirmamción de Asistencia")
                        .setMessage(result)
                        .setPositiveButton("Aceptar") { dialog, which ->
                            parentFragmentManager.popBackStack()
                        }
                        .show()
                }

            }
        }
    }

}