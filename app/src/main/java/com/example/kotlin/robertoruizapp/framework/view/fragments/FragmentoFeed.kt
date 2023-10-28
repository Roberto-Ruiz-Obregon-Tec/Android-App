package com.example.kotlin.robertoruizapp.framework.view.fragments

import com.example.kotlin.robertoruizapp.databinding.FragmentoFeedBinding
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.mypokedexapp.viewmodel.MainViewModel
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.network.model.ApiService
import com.example.kotlin.robertoruizapp.data.network.model.NetworkModuleDI
import com.example.kotlin.robertoruizapp.databinding.FragmentoHomeBinding
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import com.example.kotlin.robertoruizapp.utils.PreferenceHelper
import com.example.kotlin.robertoruizapp.utils.PreferenceHelper.get
import com.example.kotlin.robertoruizapp.utils.PreferenceHelper.set
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class FragmentoFeed : Fragment() {
    private var _binding: FragmentoFeedBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private val preferences by lazy {
        PreferenceHelper.defaultPrefs(this@FragmentoFeed.requireActivity())
    }

    /**
     * When the fragment is created sets up binding, viewmodel and progress bar
     *
     * @param inflater How the layout wil be created
     * @param container what viewmgroup the fragment belongs to
     * @param savedInstanceState the state of the activity / fragment
     *
     * @return [View] object containing the information about the fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentoFeedBinding.inflate(inflater, container, false)
        val view = binding.root

        // Aquí puedes configurar las vistas y realizar otras operaciones.

        return view
    }

}
