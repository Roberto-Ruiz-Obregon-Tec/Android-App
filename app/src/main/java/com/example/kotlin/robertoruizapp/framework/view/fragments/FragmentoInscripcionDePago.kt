package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlin.robertoruizapp.databinding.FragmentoInscripcionDePagoBinding
import com.example.kotlin.robertoruizapp.framework.viewmodel.CursoViewModel
import androidx.fragment.app.viewModels
import com.example.kotlin.robertoruizapp.data.UserRepository
import com.example.kotlin.robertoruizapp.data.network.NetworkModuleDIUser
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import com.example.kotlin.robertoruizapp.framework.viewmodel.ViewModelFactory

class FragmentoInscripcionDePago : Fragment() {

    private var _binding: FragmentoInscripcionDePagoBinding? = null
    private val binding get() = _binding!!

    private val cursoViewModel: CursoViewModel by viewModels {
        ViewModelFactory()
    }

    // Suponiendo que tienes un argumento de tipo Document para el curso
    private val cursoId: String by lazy {
        arguments?.getString("cursoId") ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentoInscripcionDePagoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cursoViewModel.getUserInfo(LoginActivity.token, cursoId).observe(viewLifecycleOwner) { result ->
            result.onSuccess { curso ->
                // Aquí actualizas la UI con la información del curso obtenido
            }
            result.onFailure { throwable ->
                // Manejar el error
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
