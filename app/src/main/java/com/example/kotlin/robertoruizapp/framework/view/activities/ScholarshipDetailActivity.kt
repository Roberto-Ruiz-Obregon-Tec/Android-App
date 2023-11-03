package com.example.kotlin.robertoruizapp.framework.view.activities

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.network.model.Becas.BecasObjeto
import com.example.kotlin.robertoruizapp.databinding.ActivityInfoBecasBinding
import com.example.kotlin.robertoruizapp.databinding.ActivityProgramDetailBinding
import com.example.kotlin.robertoruizapp.domain.ProgramInfoRequirement
import com.example.kotlin.robertoruizapp.domain.ScholarshipInfoRequirement
import com.example.kotlin.robertoruizapp.framework.adapters.ScholarshipAdapter
import com.example.kotlin.robertoruizapp.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

/**
 * ProgramDetailActivity class that manages the activity actions
 */
class ScholarshipDetailActivity : Activity() {
    private lateinit var binding: ActivityInfoBecasBinding
    private var scholarshipID: String? = null

    /**
     * When the activity is created sets up binding and viewmodel
     * alsi initializes the manageIntent, Binding and Listener methods
     *
     * @param savedInstanceState the state of the activity / fragment
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        manageIntent()
        initializeBinding()

    }



    /**
     * Initializes the binding information of the view
     */
    private fun initializeBinding() {
        binding = ActivityInfoBecasBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * Extracts the binding information passed from [ProgramFragment]
     * and sets the view
     */
    private fun manageIntent() {
        if (intent != null) {
            scholarshipID = intent.getStringExtra(Constants.ID_SCHOLARSHIP)
        }
        var scholarshipID: String = scholarshipID.toString()


        CoroutineScope(Dispatchers.IO).launch {
            val ScholarshipInfoRequirement = ScholarshipInfoRequirement()
            val result: BecasObjeto? = ScholarshipInfoRequirement(scholarshipID.toInt())

            if (result?.data?.document?.endDate != null) {
                CoroutineScope(Dispatchers.Main).launch {

                    val urlImage = result.data.document.image
                    val activity = this@ScholarshipDetailActivity
                    val imageView: ImageView = activity.findViewById(R.id.imageView)

                    val aux = result.data.document
                    val inputFormat =
                        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
                    val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    val dateStart = inputFormat.parse(aux.startDate.toString())
                    val dateEnd = inputFormat.parse(aux.endDate.toString())
                    val formattedStartDate = outputFormat.format(dateStart!!)
                    val formattedEndDate = outputFormat.format(dateEnd!!)

                    binding.nombreBecaInfo.text = aux.name
                    binding.desBeca.text = aux.description
                    binding.fechaBeca.text = formattedStartDate
                    binding.sectorBeca.text = aux.sector
                    binding.lugarBeca.text = aux.location
                    binding.orgaBeca.text = aux.organization
                    binding.fechaLimite.text = formattedEndDate
                    binding.email.text = aux.email
                    binding.telefono.text = aux.phone


                    Glide.with(activity).load(urlImage.toString())
                        .apply(
                            RequestOptions.placeholderOf(R.mipmap.ic_launcher)
                                .error(R.mipmap.ic_launcher)
                        )
                        .into(imageView)
                }
            } else {
                CoroutineScope(Dispatchers.Main).launch {

                    val urlImage = result?.data?.document?.image
                    val activity = this@ScholarshipDetailActivity
                    val imageView: ImageView = activity.findViewById(R.id.imageView)

                    val aux = result?.data?.document
                    val inputFormat =
                        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
                    val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    val dateStart = inputFormat.parse(aux?.startDate.toString())
                    val formattedStartDate = outputFormat.format(dateStart!!)

                    if (aux != null) {
                        binding.nombreBecaInfo.text = aux.name
                    }
                    if (aux != null) {
                        binding.desBeca.text = aux.description
                    }
                    binding.fechaBeca.text = formattedStartDate
                    if (aux != null) {
                        binding.sectorBeca.text = aux.sector
                    }
                    if (aux != null) {
                        binding.lugarBeca.text = aux.location
                    }
                    if (aux != null) {
                        binding.orgaBeca.text = aux.organization
                    }
                    if (aux != null) {
                        binding.email.text = aux.email
                    }
                    if (aux != null) {
                        binding.telefono.text = aux.phone
                    }

                    Glide.with(activity).load(urlImage.toString())
                        .apply(
                            RequestOptions.placeholderOf(R.mipmap.ic_launcher)
                                .error(R.mipmap.ic_launcher)
                        )
                        .into(imageView)
                }
            }
        }
    }
}


