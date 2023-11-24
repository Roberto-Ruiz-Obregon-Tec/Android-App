package com.example.kotlin.robertoruizapp.framework.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.robertoruizapp.data.ProgramsRepository
import com.example.kotlin.robertoruizapp.data.network.model.Programs.ProgramsObj
import com.example.kotlin.robertoruizapp.databinding.FragmentoProgramasBinding
import com.example.kotlin.robertoruizapp.framework.adapters.ProgramsAdapter
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A Fragment responsible for displaying a list of programs.
 */
class FragmentoProgramas : Fragment() {
    private var _binding: FragmentoProgramasBinding? = null
    private val binding get() = _binding!!

    /**
     * Called when the fragment's view is created. Inflates the layout for programs
     * and returns the root view.
     *
     * @param inflater The LayoutInflater object that can be used to inflate views.
     * @param container The parent view that the fragment's UI should be attached to.
     * @param savedInstanceState The saved instance state of the fragment, if any.
     *
     * @return The root View for the fragment's UI.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentoProgramasBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Called when the fragment's view has been created. This method initiates the process
     * of fetching program data and displaying it in the UI.
     *
     * @param view The root View of the fragment's UI.
     * @param savedInstanceState The saved instance state of the fragment, if any.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPrograms()
    }

    /**
     * Fetches program data and populates the UI with the retrieved information.
     */
    private fun getPrograms() {
        showProgressBar()
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val programsRepository = ProgramsRepository()
                val result: ProgramsObj? = programsRepository.getPrograms(LoginActivity.token)

                if (result != null) {
                    withContext(Dispatchers.Main) {
                        val adapter = ProgramsAdapter(result.data.programs)
                        binding.programasList.adapter = adapter
                        binding.programasList.layoutManager = LinearLayoutManager(context)
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

    /**
     * Displays the progress bar and hides the course list.
     */
    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.programasList.visibility = View.INVISIBLE
    }

    /**
     * Hides the progress bar and displays the course list.
     */
    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
        binding.programasList.visibility = View.VISIBLE
    }

    /**
     * Method called when the fragment view is destroyed.
     * Clear the reference to the object to avoid memory leaks.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
