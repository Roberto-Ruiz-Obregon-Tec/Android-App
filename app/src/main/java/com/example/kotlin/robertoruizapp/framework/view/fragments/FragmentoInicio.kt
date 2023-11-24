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

/**
 * Fragment for displaying the home screen with navigation buttons to different sections.
 */
class FragmentoInicio : Fragment() {
    private var _binding: FragmentoInicioBinding? = null
    private val binding get() = _binding!!

    /**
     * Called when the fragment's view is created. Inflates the layout for the home screen
     * and returns the root view.
     *
     * @param inflater The LayoutInflater object that can be used to inflate views.
     * @param container The parent view that the fragment's UI should be attached to.
     * @param savedInstanceState The saved instance state of the fragment, if any.
     *
     * @return The root View for the fragment's UI.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentoInicioBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Called when the fragment's view has been created. This method is used to set up
     * the initial state of the UI elements, such as buttons and navigation.
     *
     * @param view The root View of the fragment's UI.
     * @param savedInstanceState The saved instance state of the fragment, if any.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the buttons with the FragmentoCurso fragment on Inicio
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

    /**
     * Navigates to the specified fragment.
     *
     * @param fragment The fragment to navigate to.
     */
    private fun navigateToFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    /**
     * Selects the specified button and deselects all other buttons.
     *
     * @param selectedButton The button to select.
     */
    private fun selectButton(selectedButton: Button) {
        // Deselect all buttons
        binding.button1.isSelected = false
        binding.button2.isSelected = false
        binding.button3.isSelected = false
        binding.button4.isSelected = false

        // Select the specified button
        selectedButton.isSelected = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
