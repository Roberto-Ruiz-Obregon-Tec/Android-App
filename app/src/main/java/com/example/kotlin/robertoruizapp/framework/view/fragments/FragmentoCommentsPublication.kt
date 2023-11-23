package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.CourseRepository
import com.example.kotlin.robertoruizapp.data.RepositoryPublication
import com.example.kotlin.robertoruizapp.data.network.model.publication.Document
import com.example.kotlin.robertoruizapp.databinding.FragmentoCommentsPublicationBinding
import com.example.kotlin.robertoruizapp.framework.adapters.CommentsAdapter
import com.example.kotlin.robertoruizapp.framework.adapters.ProgramsAdapter
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Comment
import java.text.SimpleDateFormat
import java.util.Locale


class FragmentoCommentsPublication: Fragment() {
    private lateinit var binding: FragmentoCommentsPublicationBinding
    private lateinit var currentFragment: Fragment


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentoCommentsPublicationBinding.inflate(inflater, container, false)
        return binding.root
    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val publicationId = arguments?.getString("PUBLICACION_ID") ?: return

        getInfoComment(publicationId)

        binding.backContainer.setOnClickListener {

            parentFragmentManager.popBackStack()
        }
    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun getInfoComment(publicationId: String) {
        showProgressBar()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repositoryPublication = RepositoryPublication()
                val result: Document? = repositoryPublication.getPublicationId(publicationId, LoginActivity.token)
                withContext(Dispatchers.Main) {
                    if (result != null) {
                        if (result.comments.isNotEmpty()) {
                            val adapter = CommentsAdapter(result.comments)
                            binding.comentariosList.adapter = adapter
                            binding.comentariosList.layoutManager = LinearLayoutManager(context)
                        } else {
                            showError("No se encontraron comentarios.")
                        }
                    } else {
                        showError("No se encontraron comentarios.")
                    }
                    hideProgressBar()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    showError("Error al obtener los detalles del curso: ${e.message}")
                    hideProgressBar()
                }
                e.printStackTrace() // Log the exception
            }
        }
    }

    private fun showError(message: String) {
        // Show a Toast or a dialog with the error message
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
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
     * Called when the fragment's view is destroyed.
     */

}