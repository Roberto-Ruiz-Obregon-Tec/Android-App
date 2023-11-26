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
     * Adapter for course list.
     *
     * @param courses List of courses to display in the list.
     */
    class CursoAdapter(
        private val cursos: List<Document?>,
        private val itemClickListener: OnCursoClickListener
    ) : RecyclerView.Adapter<CursoAdapter.ViewHolder>() {


        /**
         * ViewHolder for course list items. Here I call it so that it appears in item_cursos.xml
         *
         * @param view View of a list item.
         */
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val nombreCurso: TextView = view.findViewById(R.id.cursos_list)
            val descripcionCurso: TextView = view.findViewById(R.id.curso_description)
            //val fechaInicioCurso: TextView = view.findViewById(R.id.curso_fecha_inicio)
            //val fechaFinCurso: TextView = view.findViewById(R.id.curso_fecha_fin)
            //Put this when you have the correct urls of the images
            //val imagenCurso: ImageView = view.findViewById(R.id.curso_imagen)
            val fechaCurso: TextView = view.findViewById(R.id.curso_fecha)
            val costoCurso: TextView = view.findViewById(R.id.curso_costo)
            val modalidadCurso: TextView = view.findViewById(R.id.curso_modalidad)

        }

        /**
         * Method called when a new ViewHolder is created.
         */
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cursos, parent, false)
            return ViewHolder(view)


        }

        /**
         * Method called to display the data in a specific ViewHolder. This is what I have in my document list.
         */
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val curso = cursos[position]
            Log.d("Cursos", "Cursos: ${curso?.name}")
            holder.nombreCurso.text = curso?.name
            holder.descripcionCurso.text = curso?.description

            // Convert and display the date
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
            val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
            val startDate = inputFormat.parse(curso?.startDate ?: "")
            val endDate = inputFormat.parse(curso?.endDate ?: "")
            holder.fechaCurso.text = if (startDate != null && endDate != null) {
                "${outputFormat.format(startDate)} - ${outputFormat.format(endDate)}"
            } else {
                "Fechas no disponibles"
            }

            //Put this when you have the correct urls of the images
            //Glide.with(holder.imagenCurso.context).load(curso?.courseImage).into(holder.imagenCurso)

            // Show the cost
            if (curso?.cost == 0.0) {
                holder.costoCurso.text = "Gratuito"
            } else {
                holder.costoCurso.text = "$${curso?.cost} MXN"
            }

            holder.modalidadCurso.text = curso?.modality

            holder.itemView.findViewById<Button>(R.id.btn_ver_mas).setOnClickListener {
                curso?._id?.let { id ->
                    itemClickListener.onCursoClicked(id)
                }
            }

        }


        /**
         * Method called to get the number of elements in the list.
         */
        override fun getItemCount() = cursos.size
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
