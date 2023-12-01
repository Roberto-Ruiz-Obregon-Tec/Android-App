package com.example.kotlin.robertoruizapp.framework.view.fragments


import android.os.Build
import com.example.kotlin.robertoruizapp.databinding.FragmentoFeedBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.databinding.FragmentoInicioBinding

/**
 * This fragment shows the companies with their certifications and events
 *
 * @property _binding Variable for the automatically generated view binding for this fragment.
 * @property binding Access property to `_binding` that ensures it is not null.
 */
class FragmentoFeed : Fragment(){

    private var _binding: FragmentoFeedBinding? = null
    private val binding get() = _binding!!

    private var lastButtonClickTime: Long = 0
    private val buttonClickInterval: Long = 1000


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentoFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectButton(binding.button1) // Seleccionar el botón de cursos
        navigateToFragment(FragmentoEventos()) // Cargar el fragmento de cursos

        // Inicializar el estado de los botones
        selectButton(binding.button1)
        navigateToFragment(FragmentoEventos())

        binding.button1.setOnClickListener {
            if (isClickable()) {
                selectButton(binding.button1)
                navigateToFragment(FragmentoEventos())
            }
        }

        binding.button2.setOnClickListener {
            if (isClickable()) {
                selectButton(binding.button2)
                navigateToFragment(FragmentoPublicaciones())
            }
        }

        binding.button3.setOnClickListener {
            if (isClickable()) {
                selectButton(binding.button3)
                navigateToFragment(FragmentoEmpresas())
            }
        }


    }

    private fun navigateToFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_feed, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun selectButton(selectedButton: Button) {
        // Desactivar todos los botones
        binding.button1.isSelected = false
        binding.button2.isSelected = false
        binding.button3.isSelected = false

        // Activar el botón seleccionado
        selectedButton.isSelected = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun isClickable(): Boolean {
        val currentTime = System.currentTimeMillis()

        if (currentTime - lastButtonClickTime >= buttonClickInterval) {
            lastButtonClickTime = currentTime
            return true
        }
        return false
    }

}