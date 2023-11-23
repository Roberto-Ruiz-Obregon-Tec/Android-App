package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.kotlin.robertoruizapp.R
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.kotlin.robertoruizapp.data.CourseRepository
import com.example.kotlin.robertoruizapp.data.network.model.Course.CourseObject
import com.example.kotlin.robertoruizapp.data.network.model.Course.Document
import com.example.kotlin.robertoruizapp.databinding.FragmentoCursoDetallesBinding
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Fragment displaying detailed information about a course.
 */
class FragmentoCursoDetalles: Fragment() {
    private var _binding: FragmentoCursoDetallesBinding? = null

    private val binding get() = _binding!!

    /**
     * Creates the view of the Fragment.
     *
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container The parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     * @return The View for the fragment's UI.
     */
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentoCursoDetallesBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Initializes the Fragment's UI and fetches course details based on the provided course ID.
     *
     * @param view The view returned by [onCreateView].
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     */
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the course ID from the arguments
        val cursoId = arguments?.getString("CURSO_ID") ?: return
        Log.d("CursoDetalles", "ID del curso recibido: $cursoId")

        // Get course information for the given ID
        getInfoCourse(cursoId)

        binding.backContainer.setOnClickListener {
            // Return to the previous fragment
            parentFragmentManager.popBackStack()
        }
    }

    /**
     * Fetches and displays course details based on the provided course ID.
     *
     * @param cursoId The ID of the course to retrieve and display details for.
     */
    @RequiresApi(Build.VERSION_CODES.N)
    private fun getInfoCourse(cursoId: String) {
        showProgressBar()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val courseRepository = CourseRepository()
                val result: Document? = courseRepository.getCourseById(cursoId, LoginActivity.token)
                Log.d("CursoDetalles", "Detalles del curso obtenidos: ${result?.name}")
                withContext(Dispatchers.Main) {
                    if (result != null) {
                        updateUIWithCourseDetails(result)
                    } else {
                        showError("No se encontraron detalles del curso.")
                    }
                    hideProgressBar()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    //showError("Error al obtener los detalles del curso: ${e.message}")
                    hideProgressBar()
                }
                e.printStackTrace() // Log the exception
            } finally {
                withContext(Dispatchers.Main) {
                }
            }
        }
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */

    private fun showError(message: String) {
        // Show a Toast or a dialog with the error message
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    /**
     * Updates the UI with course details.
     *
     * @param course The course object containing details to display.
     */
    private fun updateUIWithCourseDetails(course: Document) {
        binding.nombreCursoInfo.text = course.name
        //binding.imagenCurso.drawable = course.courseImage
        binding.nombrePonente.text = course.speaker
        binding.ratingCurso.text = course.rating.toString()

        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)

        val startDate = inputFormat.parse(course.startDate)
        val endDate = inputFormat.parse(course.endDate)

        val formattedStartDate = if (startDate != null) {
            outputFormat.format(startDate)
        } else {
            ""
        }
        val formattedEndDate = if (endDate != null) {
            outputFormat.format(endDate)
        } else {
            ""
        }
        val fechaCursoInfo = "$formattedStartDate - $formattedEndDate"
        binding.fechaCursoInfo.text = fechaCursoInfo

        binding.descripcionCursoInfo.text = course.description
        binding.modalidadCurso.text = course.modality
        binding.ubicacionCurso.text = course.location

        // Set location visibility based on course modality
        if (course.modality.equals("Remoto", ignoreCase = true)) {
            binding.ubicacionTitle.visibility = View.GONE
            binding.ubicacionCurso.visibility = View.GONE

            // Updates the restrictions of the views that were under location
            val layoutParamsHorarioTitle = binding.horarioTitle.layoutParams as ConstraintLayout.LayoutParams
            layoutParamsHorarioTitle.topToBottom = binding.modalidadCurso.id
            binding.horarioTitle.layoutParams = layoutParamsHorarioTitle

            val layoutParamsCostoTitle = binding.costoTitle.layoutParams as ConstraintLayout.LayoutParams
            layoutParamsCostoTitle.topToBottom = binding.horarioCurso.id
            binding.costoTitle.layoutParams = layoutParamsCostoTitle

            val layoutParamsCuposTitle = binding.cuposTitle.layoutParams as ConstraintLayout.LayoutParams
            layoutParamsCuposTitle.topToBottom = binding.costoCurso.id
            binding.cuposTitle.layoutParams = layoutParamsCuposTitle

            val layoutParamsFechaLimiteTitle = binding.fechaLimiteTitle.layoutParams as ConstraintLayout.LayoutParams
            layoutParamsFechaLimiteTitle.topToBottom = binding.capacidadFaltanteCurso.id
            binding.fechaLimiteTitle.layoutParams = layoutParamsFechaLimiteTitle

            // Solicitar a la vista que se reorganice con las nuevas restricciones
            binding.detailsContainer.requestLayout()

        } else {
            // Request the view to reorganize with the new constraints
            binding.ubicacionTitle.visibility = View.VISIBLE
            binding.ubicacionCurso.visibility = View.VISIBLE

            // Reset view restrictions below location
            val layoutParamsHorarioTitle = binding.horarioTitle.layoutParams as ConstraintLayout.LayoutParams
            layoutParamsHorarioTitle.topToBottom = binding.ubicacionCurso.id
            binding.horarioTitle.layoutParams = layoutParamsHorarioTitle

            val layoutParamsCostoTitle = binding.costoTitle.layoutParams as ConstraintLayout.LayoutParams
            layoutParamsCostoTitle.topToBottom = binding.horarioCurso.id
            binding.costoTitle.layoutParams = layoutParamsCostoTitle
        }

        binding.horarioCurso.text = course.schedule

        val costo = course.cost
        val costoTexto = if (costo == 0.0) {
            "Gratuito"
        } else {
            String.format(Locale.US, "$%.2f MXN", costo)
        }
        binding.costoCurso.text = costoTexto

        binding.capacidadFaltanteCurso.text = course.remaining.toString()

        val fechaLimite = startDate
        val fechaLimiteFormateada = fechaLimite?.let { outputFormat.format(it) } ?: ""
        binding.fechaLimiteInscripcion.text = fechaLimiteFormateada

        val imageUrl = course.courseImage
        if (!imageUrl.isNullOrEmpty()) {
            Glide.with(this@FragmentoCursoDetalles)
                .load(imageUrl)
                .into(binding.ivCourseImage)
        } else {
            binding.ivCourseImage.setImageResource(R.drawable.curso1)
        }

    }

    /**
     * Displays the progress bar.
     */
    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        // Puede querer ocultar también otros elementos de la interfaz de usuario
    }

    /**
     * Hides the progress bar.
     */
    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    /**
     * Called when the fragment's view is destroyed.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}