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
        // Asegúrate de que estás llamando a las vistas correctas aquí
        binding.nombreCursoInfo.text = course.name
        // ... etc. Actualiza todas las vistas que necesites con la información del curso
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}