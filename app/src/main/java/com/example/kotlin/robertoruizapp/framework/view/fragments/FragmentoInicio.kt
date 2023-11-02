package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.network.model.ApiService
import com.example.kotlin.robertoruizapp.data.network.model.NetworkModuleDI
import com.example.kotlin.robertoruizapp.databinding.FragmentoInicioBinding
import com.example.kotlin.robertoruizapp.databinding.FragmentoPerfilBinding
import com.example.kotlin.robertoruizapp.framework.view.activities.EditProfileActivity
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import com.example.kotlin.robertoruizapp.framework.viewmodel.InicioViewModel
import com.example.kotlin.robertoruizapp.framework.viewmodel.PerfilViewModel
import com.example.kotlin.robertoruizapp.utils.Constants
import com.example.kotlin.robertoruizapp.utils.PreferenceHelper
import com.example.kotlin.robertoruizapp.utils.PreferenceHelper.get
import com.example.kotlin.robertoruizapp.utils.PreferenceHelper.set
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *
 * FragmentoPerfil class for displaying the profile info of a user
 *
 * @return the view component of profile with the logout and edit methods
 *
 */
class FragmentoInicio: Fragment() {
    private var _binding : FragmentoInicioBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: InicioViewModel
    private var currentMenuOption: String? = null
    private lateinit var currentFragment: Fragment
    private val preferences by lazy {
        PreferenceHelper.defaultPrefs(this@FragmentoInicio.requireActivity())
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[InicioViewModel::class.java]
        _binding = FragmentoInicioBinding.inflate(inflater, container, false)

        val root: View = binding.root

        initializeListeners()

        return root
    }

    private fun initializeListeners() {
        binding.inicioSelectBar.buttonBecas.setOnClickListener {
            selectMenuOption(Constants.MENU_BECAS)
        }
        binding.inicioSelectBar.buttonCursos.setOnClickListener {
            selectMenuOption(Constants.MENU_CURSOS)
        }
        binding.inicioSelectBar.buttonCertificaciones.setOnClickListener {
            selectMenuOption(Constants.MENU_CERTIFICACIONES)
        }
        binding.inicioSelectBar.buttonProgramas.setOnClickListener {
            selectMenuOption(Constants.MENU_PROGRAMAS)
        }
    }

    private fun exchangeCurrentFragment(newFragment: Fragment, newMenuOption: String) {
        currentFragment = newFragment
        val transaction = (context as FragmentActivity).supportFragmentManager.beginTransaction()

        transaction.replace(R.id.nav_host_fragment_inicio, currentFragment)
        transaction.commit()
        currentMenuOption = newMenuOption
    }
    private fun selectMenuOption(menuOption: String) {
        if (menuOption == currentMenuOption) {
            return
        }
        when (menuOption) {
            Constants.MENU_BECAS -> exchangeCurrentFragment(
                FragmentoBecas(),
                Constants.MENU_BECAS
            )
            //Constants.MENU_CURSOS -> exchangeCurrentFragment(
            //    FragmentoCursos(), //Crear fragmento cursos
            //    Constants.MENU_CURSOS
            //)
            //Constants.MENU_CERTIFICACIONES -> exchangeCurrentFragment(
            //    FragmentoCertificaciones(), //Crear fragmento certificaciones
            //    Constants.MENU_CERTIFICACIONES
            //)
            // Constants.MENU_PROGRAMAS -> exchangeCurrentFragment(
            //    FragmentoProgramas(), //Cambiar por los fragmentos programas
            //    Constants.MENU_PROGRAMAS
            //)
        }
    }


}
