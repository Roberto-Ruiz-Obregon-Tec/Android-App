package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.RepositoryPublication
import com.example.kotlin.robertoruizapp.data.network.model.publication.LikeRequest
import com.example.kotlin.robertoruizapp.data.network.model.publication.PublicObjeto
import com.example.kotlin.robertoruizapp.databinding.FragmentoPublicacionesBinding
import com.example.kotlin.robertoruizapp.framework.adapters.EventsAdapter
import com.example.kotlin.robertoruizapp.framework.adapters.PublicationAdapter
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentoPublicaciones : Fragment() {

    private var lastClickTime: Long = 0
    private val clickInterval: Long = 1000 // 1 segundo

    private var _binding: FragmentoPublicacionesBinding? = null
    private val binding get() = _binding!!

    private val onCommentClickListener = object : PublicationAdapter.OnCommentClickListener {
        override fun OnCommentClicked(position: Int,publicationId: String) {
            val fragmentoCommentDetalles = FragmentoCommentsPublication().apply {
                arguments = Bundle().apply {
                    putString("PUBLICACION_ID", publicationId)
                }
            }


            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_main, fragmentoCommentDetalles)
                .addToBackStack(null)
                .commit()
        }

    }
    private val onLikeClickListener = object : PublicationAdapter.OnLikeClickListener {
        @RequiresApi(Build.VERSION_CODES.N)
        override fun OnLikeClicked(position: Int, publicationId: String) {
            val currentTime = System.currentTimeMillis()

            if (currentTime - lastClickTime >= clickInterval) {
                likePublication(position, publicationId)
                lastClickTime = currentTime
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentoPublicacionesBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Method called when the fragment view is created.
     */
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.publicacionList.layoutManager = LinearLayoutManager(context)
        binding.publicacionList.adapter = PublicationAdapter(emptyList(), onCommentClickListener, onLikeClickListener)

        for (i in 0 until parentFragmentManager.backStackEntryCount) {
            val entry = parentFragmentManager.getBackStackEntryAt(i)
            Log.d("BackStack", "Entry at $i: ${entry.name}")
        }

        getPublication()
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun getPublication() {
        showProgressBar()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repositoryPublication = RepositoryPublication()
                val result: PublicObjeto? = repositoryPublication.getPublication(LoginActivity.token)

                if (result != null) {
                    withContext(Dispatchers.Main) {
                        // Pasa ambos listeners al adaptador
                        val adapter = PublicationAdapter(result.data, onCommentClickListener, onLikeClickListener)
                        binding.publicacionList.adapter = adapter
                        binding.publicacionList.layoutManager = LinearLayoutManager(context)
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
    private fun likePublication(position: Int, publicationId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repositoryPublication = RepositoryPublication()
                val likeRequest = LikeRequest(publicationId)
                val response = repositoryPublication.likePublication(LoginActivity.token, likeRequest)

                if (response != null) {
                    // Obtén la versión actualizada de la publicación.
                    // Esto puede ser a través de una nueva solicitud o parte de la respuesta de 'likePublication'.
                    val updatedPublication = repositoryPublication.getPublicationId(publicationId, LoginActivity.token)

                    withContext(Dispatchers.Main) {
                        if (updatedPublication != null) {
                            val adapter = binding.publicacionList.adapter as PublicationAdapter
                            adapter.updatePublication(position, updatedPublication)
                            // Actualizar la interfaz de usuario según sea necesario
                        }
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    // Manejar errores, mostrar un mensaje, etc.
                }
            }
        }
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.publicacionList.visibility = View.INVISIBLE // Ocultar la lista mientras carga
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
        binding.publicacionList.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}