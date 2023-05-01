package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.mypokedexapp.viewmodel.MainViewModel
import com.example.kotlin.robertoruizapp.databinding.FragmentoHomeBinding
/**
 * FragmentHome class that manages the fragment actions
 */
class FragmentoHome : Fragment() {

    private var _binding: FragmentoHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        _binding = FragmentoHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initializeListeners()
        val imageView = binding.fondoImagen

        imageView.setOnClickListener {
            goToNewFragment()
        }
        return root
    }

    /**
     * Initializes the listeners for the buttons in the home view
     *
     */
    private fun initializeListeners() {
        binding.twitterIcon.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://twitter.com/Fundacion_RRO")
            startActivity(openURL)
        }

        binding.instaIcon.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.instagram.com/frobertoruizobregon/")
            startActivity(openURL)
        }

        binding.faceLogo.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.facebook.com/fundacionruizobregon/")
            startActivity(openURL)
        }

        binding.whaLogo.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://wa.me/524428205425")
            startActivity(openURL)
        }

        binding.telefono1.setOnClickListener(){
            val phone  = "tel:" + "442  214 4020"
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse(phone)))

        }

        binding.telefono2.setOnClickListener() {
            val phone = "tel:" + "442  214 2380"
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse(phone)))

        }
        binding.telefono3.setOnClickListener(){
            val phone  = "tel:" + "442  214 3033"
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse(phone)))

        }
    }

    private fun goToNewFragment() {

        val contenedor = (context as FragmentActivity).findViewById<ViewGroup>(R.id.frag_home)
        contenedor.removeAllViews()

        val fragmentoNuevo = FragmentoMisCursos()
        val transaction = (context as FragmentActivity).supportFragmentManager.beginTransaction()

        transaction.replace(R.id.frag_home, fragmentoNuevo)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    /**
     * Sets the binding to Null after the fragment is destoroyed
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}




