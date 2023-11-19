package com.example.kotlin.robertoruizapp.framework.view.fragments


import android.content.Intent
import com.example.kotlin.robertoruizapp.data.network.model.Course.Document
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.CertificacionesRepository
import com.example.kotlin.robertoruizapp.data.CourseRepository
import com.example.kotlin.robertoruizapp.data.ProgramsRepository
import com.example.kotlin.robertoruizapp.data.ScholarshipRepository
import com.example.kotlin.robertoruizapp.data.network.model.Becas.BecasObjeto
import com.example.kotlin.robertoruizapp.data.network.model.Course.CourseObject
import com.example.kotlin.robertoruizapp.data.network.model.Programs.ProgramsObj
import com.example.kotlin.robertoruizapp.data.network.model.certificaciones.CertificacionesObjeto
import com.example.kotlin.robertoruizapp.databinding.FragmentoHomeBinding
import com.example.kotlin.robertoruizapp.framework.adapters.CursoAdapter
import com.example.kotlin.robertoruizapp.framework.adapters.ProgramsAdapter
import com.example.kotlin.robertoruizapp.framework.adapters.ScholarshipAdapter
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Fragment that represents the home screen of the application.
 */
class FragmentoHome : Fragment() {

    private var _binding: FragmentoHomeBinding? = null

    private val binding get() = _binding!!

    /**
     * Interface for handling curso (course) click events.
     */
    interface OnCursoClickListener {
        /**
         * Called when a curso (course) item is clicked.
         *
         * @param cursoId The ID of the clicked curso (course).
         */
        fun onCursoClicked(cursoId: String)
    }
    /**
     * An instance of [OnCursoClickListener] used to handle curso (course) click events.
     */
    private val onCursoClickListener = object : OnCursoClickListener {
        override fun onCursoClicked(cursoId: String) {
            val fragmentoDetalles = FragmentoCursoDetalles().apply {
                arguments = Bundle().apply {
                    putString("CURSO_ID", cursoId)
                }
            }

            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_main, fragmentoDetalles)
                .addToBackStack(null) // Allows returning to the previous fragment
                .commit()
        }
    }


    /**
     * Method called when the fragment view is created.
     */
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentoHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    /**
     * Method called when the fragment view has been completely created.
     */
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCourse()

        binding.button1.setOnClickListener {
            getCourse()

            binding.button1.setBackgroundResource(R.drawable.button_active)
            binding.button1.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

            binding.button2.setBackgroundResource(R.drawable.button_inactive)
            binding.button2.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))

            binding.button3.setBackgroundResource(R.drawable.button_inactive)
            binding.button3.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))

            binding.button4.setBackgroundResource(R.drawable.button_inactive)
            binding.button4.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        }

        binding.button2.setOnClickListener {
            getPrograms()

            binding.button2.setBackgroundResource(R.drawable.button_active)
            binding.button2.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

            binding.button1.setBackgroundResource(R.drawable.button_inactive)
            binding.button1.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))

            binding.button3.setBackgroundResource(R.drawable.button_inactive)
            binding.button3.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))

            binding.button4.setBackgroundResource(R.drawable.button_inactive)
            binding.button4.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        }

        binding.button3.setOnClickListener {
            getCertificaciones()

            binding.button1.setBackgroundResource(R.drawable.button_inactive)
            binding.button1.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))

            binding.button2.setBackgroundResource(R.drawable.button_inactive)
            binding.button2.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))

            binding.button3.setBackgroundResource(R.drawable.button_active)
            binding.button3.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

            binding.button4.setBackgroundResource(R.drawable.button_inactive)
            binding.button4.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))


        }
        binding.button4.setOnClickListener {
            getScholarship()

            binding.button1.setBackgroundResource(R.drawable.button_inactive)
            binding.button1.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))

            binding.button2.setBackgroundResource(R.drawable.button_inactive)
            binding.button2.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))

            binding.button3.setBackgroundResource(R.drawable.button_inactive)
            binding.button3.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))

            binding.button4.setBackgroundResource(R.drawable.button_active)
            binding.button4.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        }




    }


    /**
     * Method to get the list of courses and display it in the UI.
     */
    @RequiresApi(Build.VERSION_CODES.N)
    private fun getCourse() {
        showProgressBar()
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val CourseRepository = CourseRepository()
                val result: CourseObject? = CourseRepository.getCourse(LoginActivity.token)

                if (result != null) {
                    withContext(Dispatchers.Main) {
                        val adapter = CursoAdapter(result.data, onCursoClickListener)
                        binding.cursosList.adapter = adapter
                        binding.cursosList.layoutManager = LinearLayoutManager(context)
                    }

                }

            } catch (e: Exception) {
                e.printStackTrace() // Log the exception
            } finally {
                withContext(Dispatchers.Main) {
                    hideProgressBar()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getPrograms() {
        showProgressBar()
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val programsRepository = ProgramsRepository()
                val result: ProgramsObj? = programsRepository.getPrograms(LoginActivity.token)

                if (result != null) {
                    withContext(Dispatchers.Main) {
                        val adapter = ProgramsAdapter(result.data.programs)
                        binding.cursosList.adapter = adapter
                        binding.cursosList.layoutManager = LinearLayoutManager(context)
                    }

                }

            } catch (e: Exception) {
                e.printStackTrace() // Log the exception
            } finally {
                withContext(Dispatchers.Main) {
                    hideProgressBar()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getCertificaciones() {
        CoroutineScope(Dispatchers.IO).launch {
            val certificacionesRepository = CertificacionesRepository()
            val result: CertificacionesObjeto? = certificacionesRepository.getCertificaciones(LoginActivity.token)

            if (result != null) {
                withContext(Dispatchers.Main) {
                    val adapter = FragmentoCertificaciones.CertificacionesAdapter(result.data.documents)
                    binding.cursosList.adapter = adapter
                    binding.cursosList.layoutManager = LinearLayoutManager(context)
                }
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun getScholarship() {
        showProgressBar()
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val ScholarshipRepository = ScholarshipRepository()
                val result: BecasObjeto? = ScholarshipRepository.getScholarship(LoginActivity.token)

                if (result != null) {
                    withContext(Dispatchers.Main) {

                        val adapter = ScholarshipAdapter(result.data.documents)
                        binding.cursosList.adapter = adapter
                        binding.cursosList.layoutManager = LinearLayoutManager(context)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace() // Log the exception
            } finally {
                withContext(Dispatchers.Main) {
                    hideProgressBar()
                }
            }
        }
    }

    /**
     * Displays the progress bar and hides the course list.
     */
    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.cursosList.visibility = View.INVISIBLE
    }

    /**
     * Hides the progress bar and displays the course list.
     */
    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
        binding.cursosList.visibility = View.VISIBLE
    }


    /**
     * Method called when the fragment view is destroyed.
     * Clear the reference to the object to avoid memory leaks.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
