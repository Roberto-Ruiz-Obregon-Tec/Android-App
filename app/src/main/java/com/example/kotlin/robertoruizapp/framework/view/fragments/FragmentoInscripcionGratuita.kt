package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.CourseRepository
import com.example.kotlin.robertoruizapp.data.UserRepository
import com.example.kotlin.robertoruizapp.data.network.NetworkModuleDIUser
import com.example.kotlin.robertoruizapp.databinding.FragmentoInscripcionGratuitaBinding
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import com.example.kotlin.robertoruizapp.framework.viewmodel.CursoViewModel
import com.example.kotlin.robertoruizapp.framework.viewmodel.ViewModelFactory
import com.example.kotlin.robertoruizapp.utils.PreferenceHelper
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Fragment for handling course enrollment and payment information.
 */
class FragmentoInscripcionGratuita : Fragment() {
    private var _binding: FragmentoInscripcionGratuitaBinding? = null
    private val binding get() = _binding!!

    /**
     * ViewModel for managing course-related data and operations.
     */
    private val cursoViewModel: CursoViewModel by viewModels {
        ViewModelFactory(UserRepository(), CourseRepository())
    }

    /**
     * Lazily initializes the course ID from fragment arguments.
     *
     * If the "CURSO_ID" argument is provided, it will be used as the course ID. Otherwise, an
     * empty string will be used.
     */
    private val cursoId: String by lazy {
        arguments?.getString("cursoId") ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentoInscripcionGratuitaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showProgressBar()
        // Obtener el userId de SharedPreferences
        val preferences = PreferenceHelper.defaultPrefs(requireContext())
        val userId = preferences.getString("userId", "") ?: ""
        Log.d("FragmentoInscripcion", "UserId obtenido: $userId")
        if (userId.isNotEmpty()) {
            // Utilizar el userId para obtener la información del usuario
            cursoViewModel.getUserInfo(LoginActivity.token, userId).observe(viewLifecycleOwner) { result ->
                result.onSuccess { userInfo ->
                    // Actualizar la UI con los detalles del usuario
                    binding.nombreUsuario.text = "${userInfo.firstName} ${userInfo.lastName}"
                    binding.correoUsuario.text = userInfo.email
                }

                result.onFailure {
                    // Manejar el error
                    Toast.makeText(requireContext(), "Error al obtener datos del usuario", Toast.LENGTH_LONG).show()
                }
            }
        } else {
            // Manejar el caso en que no se encuentre el userId
            Toast.makeText(requireContext(), "Usuario no identificado", Toast.LENGTH_LONG).show()
        }
        cursoViewModel.getCursoInfo(cursoId).observe(viewLifecycleOwner) { result ->
            hideProgressBar()
            result.onSuccess { curso ->
                binding.nombreCursoInfo.text = curso.name
                val imageToLoad = if (curso.courseImage.isNullOrEmpty()) {
                    R.drawable.curso1
                } else {
                    curso.courseImage
                }

                Glide.with(this)
                    .load(imageToLoad)
                    .placeholder(R.drawable.curso1)
                    .error(R.drawable.curso1)
                    .into(binding.ivCourseImage)

                // Formatea las fechas
                val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
                val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)

                val startDate = inputFormat.parse(curso.startDate)
                val endDate = inputFormat.parse(curso.endDate)

                val formattedStartDate = startDate?.let { outputFormat.format(it) } ?: ""
                val formattedEndDate = endDate?.let { outputFormat.format(it) } ?: ""
                binding.fechaCursoInfo.text = "$formattedStartDate - $formattedEndDate"

                binding.horarioCurso.text = curso.schedule
                if (curso.modality != "Remoto") {
                    binding.ubicacionCurso.text = curso.location
                } else {
                    binding.ubicacionCurso.text = "${curso.modality} (Se accederá por medio de un link a la clase)"
                }
                binding.costoCurso.text = if(curso.cost == 0.0) "Gratuito" else "${curso.cost} MXN"

                binding.backContainer.setOnClickListener {
                    parentFragmentManager.popBackStack()
                }
            }
        }

        setupEnrollButton()
    }

    /**
     * Displays the progress bar.
     */
    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    /**
     * Hides the progress bar.
     */
    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    /**
     * Sets up the enroll button click listener to show the confirmation dialog.
     */
    private fun setupEnrollButton() {
        binding.btnEnroll.setOnClickListener {
            showConfirmationDialog(cursoId)
        }
    }

    /**
     * Displays a confirmation dialog for course enrollment.
     *
     * @param courseId The ID of the course to enroll in.
     */
    private fun showConfirmationDialog(courseId: String) {
        context?.let {
            AlertDialog.Builder(it)
                .setTitle("Confirmamción de Asistencia")
                .setMessage("¿Estás seguro de enviar tu solicitud de inscripción al curso?")
                .setPositiveButton("Aceptar") { dialog, which ->
                    // Lógica para manejar la confirmación de asistencia
                    confirmAttendance(courseId)
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }
    }

    /**
     * Handles the confirmation of attendance and sends the enrollment request to the ViewModel.
     *
     * @param courseId The ID of the course to enroll in.
     */
    private fun confirmAttendance(courseId: String){

        cursoViewModel.postInscription(courseId).observe(viewLifecycleOwner) { result ->
            context?.let {
                AlertDialog.Builder(it)
                    .setTitle("Confirmación de Asistencia")
                    .setMessage("Se ha enviado su petición de inscripción al curso. Cuando sea aprobado, aparecerá en la parte de Mis cursos en su perfil")
                    .setPositiveButton("Aceptar") { dialog, which ->
                        // Navegar a FragmentoCurso
                        //navigateToFragment(FragmentoCurso())
                    }
                    .show()
            }
        }
    }

    /**
     * Called when the fragment's view is destroyed.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}