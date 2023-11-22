import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.databinding.FragmentoInicioBinding
import com.example.kotlin.robertoruizapp.framework.view.fragments.FragmentoBecas
import com.example.kotlin.robertoruizapp.framework.view.fragments.FragmentoCertificaciones
import com.example.kotlin.robertoruizapp.framework.view.fragments.FragmentoCurso
import com.example.kotlin.robertoruizapp.framework.view.fragments.FragmentoCursos
import com.example.kotlin.robertoruizapp.framework.view.fragments.FragmentoProgramas

class FragmentoInicio : Fragment() {
    private var _binding: FragmentoInicioBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentoInicioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectButton(binding.button1) // Seleccionar el botón de cursos
        navigateToFragment(FragmentoCurso()) // Cargar el fragmento de cursos

        // Inicializar el estado de los botones
        selectButton(binding.button1)
        navigateToFragment(FragmentoCurso())

        binding.button1.setOnClickListener {
            selectButton(binding.button1)
            navigateToFragment(FragmentoCurso())
        }

        binding.button2.setOnClickListener {
            selectButton(binding.button2)
            navigateToFragment(FragmentoProgramas())
        }

        binding.button3.setOnClickListener {
            selectButton(binding.button3)
            navigateToFragment(FragmentoCertificaciones())
        }

        binding.button4.setOnClickListener {
            selectButton(binding.button4)
            navigateToFragment(FragmentoBecas())
        }
    }

    private fun navigateToFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun selectButton(selectedButton: Button) {
        // Desactivar todos los botones
        binding.button1.isSelected = false
        binding.button2.isSelected = false
        binding.button3.isSelected = false
        binding.button4.isSelected = false

        // Activar el botón seleccionado
        selectedButton.isSelected = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
