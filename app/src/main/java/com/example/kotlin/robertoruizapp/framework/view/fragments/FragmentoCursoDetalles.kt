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
import com.example.kotlin.robertoruizapp.R
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.robertoruizapp.data.CourseRepository
import com.example.kotlin.robertoruizapp.data.network.model.Course.CourseObject
import com.example.kotlin.robertoruizapp.data.network.model.Course.Document
import com.example.kotlin.robertoruizapp.databinding.FragmentoCursoDetallesBinding
import com.example.kotlin.robertoruizapp.databinding.FragmentoHomeBinding
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale


class FragmentoCursoDetalles: Fragment() {
    private var _binding: FragmentoCursoDetallesBinding? = null

    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentoCursoDetallesBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener el ID del curso de los argumentos
        val cursoId = arguments?.getString("CURSO_ID") ?: return
        Log.d("CursoDetalles", "ID del curso recibido: $cursoId")

        // Obtener la información del curso con ese ID
        getInfoCourse(cursoId)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getInfoCourse(cursoId: String) {

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
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    showError("Error al obtener los detalles del curso: ${e.message}")
                }
                e.printStackTrace() // Log the exception
            } finally {
                withContext(Dispatchers.Main) {
                }
            }
        }
    }

    private fun showError(message: String) {
        // Mostrar un Toast o un diálogo con el mensaje de error
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

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

        //binding.fechaInicio.text = course.startDate
        //binding.fechaFin.text = course.endDate
        binding.descripcionCursoInfo.text = course.description
        binding.modalidadCurso.text = course.modality
        binding.ubicacionCurso.text = course.location
        binding.horarioCurso.text = course.schedule

        val costo = course.cost
        val costoTexto = if (costo == 0.0) {
            "Gratuito" // Si el costo es 0, mostrar "Gratuito"
        } else {
            String.format(Locale.US, "$%.2f MXN", costo) // De lo contrario, mostrar el formato de moneda
        }
        binding.costoCurso.text = costoTexto

        binding.capacidadFaltanteCurso.text = course.remaining.toString()

        val fechaLimite = SimpleDateFormat("dd/MM/yyyy", Locale.US).parse(course.startDate)
        val fechaLimiteFormateada = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).format(fechaLimite)

        binding.fechaLimiteInscripcion.text = fechaLimiteFormateada

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}