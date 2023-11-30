package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.InfoFunRepository
import com.example.kotlin.robertoruizapp.data.network.model.InfoFoundation.InfoObject
import com.example.kotlin.robertoruizapp.data.network.model.InfoFoundation.Document
import com.example.kotlin.robertoruizapp.databinding.FragmentoFrroBinding
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentoFRRO : Fragment() {
    private var _binding: FragmentoFrroBinding? = null
    private val binding get() = _binding!!

    /**
     * Create the fragment view.
     *
     *
     * @param inflater The LayoutInflater used to inflate the fragment.
     * @param container The container where the fragment will be inserted.
     * @param savedInstanceState A Bundle containing data from the previous state of the fragment.
     * @return The root view of the inflated fragment.
     */

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentoFrroBinding.inflate(inflater, container, false)

        binding.faceBook.setOnClickListener {
            openFacebookPage()
        }

        binding.instagram.setOnClickListener{
            openInstagramPage()
        }

        binding.twitter.setOnClickListener{
            openTwitterPage()
        }

        return binding.root
    }

    private fun openFacebookPage() {
        val url = "https://www.facebook.com/fundacionruizobregon"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)

        // Verifica si hay una aplicación que pueda manejar el intent
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            // Muestra un mensaje de error o realiza una acción alternativa
            Toast.makeText(context, "No se encontró una aplicación para abrir el enlace", Toast.LENGTH_SHORT).show()
        }
    }
    private fun openInstagramPage() {
        val url = "https://www.instagram.com/frobertoruizobregon/"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)

        // Verifica si hay una aplicación que pueda manejar el intent
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            // Muestra un mensaje de error o realiza una acción alternativa
            Toast.makeText(context, "No se encontró una aplicación para abrir el enlace", Toast.LENGTH_SHORT).show()
        }
    }
    private fun openTwitterPage() {
        val url = "https://x.com/Fundacion_RRO?t=yqMTcPKoz3hEWkD5ro-OUA&s=08"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)

        // Verifica si hay una aplicación que pueda manejar el intent
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            // Muestra un mensaje de error o realiza una acción alternativa
            Toast.makeText(context, "No se encontró una aplicación para abrir el enlace", Toast.LENGTH_SHORT).show()
        }
    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getInfo()
    }

    /**
     * Obtain Information  and update your view.
     *
     * Make an asynchronous request to obtain foundation Information and, once obtained,
     * Updates the list in the UI.
     */

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getInfo() {
        showProgressBar()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val infoFunRepository = InfoFunRepository()
                val result: InfoObject? = infoFunRepository.getInfo(LoginActivity.token)

                if (result != null && result.data.info.isNotEmpty()) {
                    withContext(Dispatchers.Main) {
                        val phone = result.data.info.first()?.phone
                        binding.phoneTextView.text = phone
                        val descripcion = result.data.info.first()?.description
                        binding.descripcionFun.text = descripcion
                        val email = result.data.info.first()?.email
                        binding.emailFun.text = email
                        val ubicacion = result.data.info.first()?.location
                        binding.ubicacion.text = ubicacion

                        binding.phoneTextView.setOnClickListener {
                            phone?.let { phoneNumber ->
                                val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                                startActivity(dialIntent)
                            }
                        }
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
    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.phoneTextView.visibility = View.INVISIBLE // Ocultar la lista mientras carga
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
        binding.phoneTextView.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}