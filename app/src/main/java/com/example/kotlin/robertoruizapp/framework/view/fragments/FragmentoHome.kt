package com.example.kotlin.robertoruizapp.framework.view.fragments


import com.example.kotlin.robertoruizapp.data.network.model.Course.Document
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin.mypokedexapp.viewmodel.MainViewModel
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.CourseRepository
import com.example.kotlin.robertoruizapp.data.network.model.ApiService
import com.example.kotlin.robertoruizapp.data.network.model.Course.CourseObject
import com.example.kotlin.robertoruizapp.data.network.model.NetworkModuleDI
import com.example.kotlin.robertoruizapp.databinding.FragmentoHomeBinding
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import com.example.kotlin.robertoruizapp.utils.PreferenceHelper
import com.example.kotlin.robertoruizapp.utils.PreferenceHelper.get
import com.example.kotlin.robertoruizapp.utils.PreferenceHelper.set
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Locale
import com.example.kotlin.robertoruizapp.framework.view.fragments.FragmentoPerfil as perfil

class FragmentoHome : Fragment() {

    private var _binding: FragmentoHomeBinding? = null

    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentoHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCourse()
    }

    class CursoAdapter(private val cursos: List<Document?>) :
        RecyclerView.Adapter<CursoAdapter.ViewHolder>() {

        //Acá lo llamo para que aparezca en item_cursos.xml
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val nombreCurso: TextView = view.findViewById(R.id.cursos_list)
            val descripcionCurso: TextView = view.findViewById(R.id.curso_description)
            val fechaCurso: TextView = view.findViewById(R.id.curso_fecha)
            //Poner esto cuando se tengan las url's bien de las imagenes
            //val imagenCurso: ImageView = view.findViewById(R.id.curso_imagen)
            val costoCurso: TextView = view.findViewById(R.id.curso_costo)
            val modalidadCurso: TextView = view.findViewById(R.id.curso_modalidad)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cursos, parent, false)
            return ViewHolder(view)
        }

        //Esto es lo que tengo en mi lista de documentos
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val curso = cursos[position]
            Log.d("Cursos", "Cursos: ${curso?.name}")
            holder.nombreCurso.text = curso?.name
            holder.descripcionCurso.text = curso?.description

            // Convertir y mostrar la fecha
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
            val outputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.US)
            val date = inputFormat.parse(curso?.startDate ?: "")
            holder.fechaCurso.text = outputFormat.format(date)

            //Poner esto cuando se tengan las url's bien de las imagenes
            //Glide.with(holder.imagenCurso.context).load(curso?.courseImage).into(holder.imagenCurso)

            // Mostrar el costo
            if (curso?.cost == 0) {
                holder.costoCurso.text = "Gratuito"
            } else {
                holder.costoCurso.text = "$${curso?.cost}"
            }

            holder.modalidadCurso.text = curso?.modality
        }



        override fun getItemCount() = cursos.size
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getCourse() {
        CoroutineScope(Dispatchers.IO).launch {
            val CourseRepository = CourseRepository()
            val result: CourseObject? = CourseRepository.getCourse()

            if (result != null) {
                withContext(Dispatchers.Main) {
                    val adapter = CursoAdapter(result.data)
                    binding.cursosList.adapter = adapter
                    binding.cursosList.layoutManager = LinearLayoutManager(context)
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
