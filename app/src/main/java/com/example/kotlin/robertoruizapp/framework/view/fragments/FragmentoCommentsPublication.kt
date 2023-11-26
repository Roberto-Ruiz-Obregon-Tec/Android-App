package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.app.AlertDialog
import android.graphics.Color
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
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.CourseRepository
import com.example.kotlin.robertoruizapp.data.RepositoryPublication
import com.example.kotlin.robertoruizapp.data.network.model.publication.CommentRequest
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
            // Return to the previous fragment
            parentFragmentManager.popBackStack()
        }
        binding.buttonMensaje.setOnClickListener {
            val commentText = binding.editTextComentario.text.toString().trim()
            if (commentText.isNotEmpty()) {
                sendComment(publicationId, commentText)
            } else {
                Toast.makeText(context, "Por favor, escribe un comentario.", Toast.LENGTH_SHORT).show()
            }
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
                    if (result != null && result.comments.isNotEmpty()) {
                        if (result.comments.isNotEmpty()) {
                            val adapter = CommentsAdapter(result.comments)
                            binding.comentariosList.adapter = adapter
                            binding.comentariosList.layoutManager = LinearLayoutManager(context)
                            binding.textViewNoComments.visibility = View.GONE

                        } else {
                            binding.textViewNoComments.visibility = View.VISIBLE
                        }
                    } else {
                        binding.textViewNoComments.visibility = View.VISIBLE
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

    private fun sendComment(publicationId: String, commentText: String) {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Enviando Comentario")
        alertDialog.setMessage("Tú comentario será revisado para su publicación.\n\nEsto puede tardar algunos días")
        alertDialog.setPositiveButton("OK", null)
        alertDialog.setNegativeButton("Cancelar", null)

        val dialog = alertDialog.create()
        dialog.setOnShowListener {
            val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            positiveButton.setTextColor(Color.RED)
            positiveButton.setOnClickListener {
                processComment(publicationId, commentText)
                dialog.dismiss() 
            }


            val negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
            negativeButton.setTextColor(Color.RED)
            negativeButton.setOnClickListener {
                dialog.dismiss()
            }
        }
        dialog.show()
    }


    private fun processComment(publicationId: String, commentText: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repositoryPublication = RepositoryPublication()
                val commentRequest = CommentRequest(comment = commentText, publication = publicationId)
                val response = repositoryPublication.createPublicationComment(LoginActivity.token, commentRequest)

                withContext(Dispatchers.Main) {
                    if (response != null) {
                        Toast.makeText(context, "Comentario enviado con éxito.", Toast.LENGTH_SHORT).show()
                        binding.editTextComentario.setText("")
                    } else {
                        showError("Error al enviar el comentario.")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    showError("Error al enviar el comentario: ${e.message}")
                }
                e.printStackTrace()
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