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

    private var _binding: FragmentoPublicacionesBinding? = null
    private val binding get() = _binding!!

    private val onCommentClickListener = object : PublicationAdapter.OnCommentClickListener {
        override fun OnCommentClicked(publicationId: String) {
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
        binding.publicacionList.adapter = PublicationAdapter(emptyList(), onCommentClickListener)
        binding.publicacionList.layoutManager = LinearLayoutManager(context)

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
                        val adapter = PublicationAdapter(result.data, onCommentClickListener)
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