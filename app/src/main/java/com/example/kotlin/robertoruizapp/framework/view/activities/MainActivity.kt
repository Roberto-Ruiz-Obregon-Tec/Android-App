package com.example.kotlin.robertoruizapp.framework.view.activities

import FragmentoInicio
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.kotlin.mypokedexapp.viewmodel.MainViewModel
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.databinding.ActivityMainBinding
import com.example.kotlin.robertoruizapp.framework.view.fragments.FragmentoFRRO
import com.example.kotlin.robertoruizapp.framework.view.fragments.FragmentoPerfil
import com.example.kotlin.robertoruizapp.framework.view.fragments.FragmentoFeed
import com.example.kotlin.robertoruizapp.utils.Constants

/**
 * MainActivity class that manages the activity actions
 *
 */
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var currentFragment: Fragment
    private var currentMenuOption: String? = null

    /**
     * When the activity is created sets up binding and viewmodel
     * also initializes the manageIntent, Binding and Listener methods
     *
     * @param savedInstanceState the state of the activity / fragment
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeBinding()
        initializeListeners()
        exchangeCurrentFragment(FragmentoInicio(), Constants.MENU_INICIO)
    }

    /**
     * Initializes the Listeners to bind the icons with their corresponding action
     */
    private fun initializeListeners() {
        binding.appBarMain.inicioMenu.setOnClickListener {
            selectMenuOption(Constants.MENU_INICIO)
        }
        binding.appBarMain.feedMenu.setOnClickListener {
            selectMenuOption(Constants.MENU_FEED)
        }
        binding.appBarMain.frroMenu.setOnClickListener {
            selectMenuOption(Constants.MENU_FRRO)
        }
        binding.appBarMain.perfilMenu.setOnClickListener {
            selectMenuOption(Constants.MENU_PERFIL)
        }
    }

    /**
     * Initializes the binding information of the view
     */
    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * Exchanges the currentFragment into the new fragment after the menu option is selected
     *
     * @param newFragment Fragment that will set
     * @param newMenuOption name of the menu option
     */
    private fun exchangeCurrentFragment(newFragment: Fragment, newMenuOption: String) {
        currentFragment = newFragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_content_main, currentFragment)
            .commit()
        currentMenuOption = newMenuOption
    }

    /**
     * Actions the change of the current Fragment after clicking on the menu option
     *
     * @param menuOption MenuOption that was selected by the user
     */
    private fun selectMenuOption(menuOption: String) {
        if (menuOption == currentMenuOption) {
            return
        }
        when (menuOption) {
            Constants.MENU_INICIO -> exchangeCurrentFragment(
                FragmentoInicio(), //Crear fragmento inicio, se usa home temporalmente
                Constants.MENU_INICIO
            )
            Constants.MENU_FEED -> exchangeCurrentFragment(
                FragmentoFeed(), //Crear fragmento feed
                Constants.MENU_FEED
            )
            Constants.MENU_FRRO -> exchangeCurrentFragment(
                FragmentoFRRO(), //Crear fragmento frro
                Constants.MENU_FRRO
            )
            Constants.MENU_PERFIL -> exchangeCurrentFragment(
                FragmentoPerfil(), //Cambiar por los fragmentos que usaremos
                Constants.MENU_PERFIL
            )
        }
    }
}