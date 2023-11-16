package com.example.kotlin.robertoruizapp.framework.adapters.viewholder

import android.content.Context
import android.content.Intent
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.example.kotlin.robertoruizapp.data.network.model.Becas.BecasObjeto
import com.example.kotlin.robertoruizapp.data.network.model.Becas.Document
import com.example.kotlin.robertoruizapp.databinding.ListElementBecasBinding
import com.example.kotlin.robertoruizapp.domain.ScholarshipListRequirement
import com.example.kotlin.robertoruizapp.framework.view.activities.ProgramDetailActivity
import com.example.kotlin.robertoruizapp.framework.view.activities.ScholarshipDetailActivity
import com.example.kotlin.robertoruizapp.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

/**
 * ScholarshipViewHolder class which has bind, passViewGoToScholarshipDetail
 * and getScholarshipInfo methods
 *
 * @property binding property acquired from DataBinding object
 */
class ScholarshipViewHolder(private val binding: ListElementBecasBinding) : RecyclerView.ViewHolder
    (binding.root) {

    /**
     * Create the binding from the JSON [Document] as a result of the API request
     * to the parts of the view at ProgramFragment. If some data is missing default
     * information is displayed
     *
     * @param item the information provided from API in the JSON [Document]
     * @param context Context of the  view
     */
    fun bind(item: Document, context: Context) {
        val inputStartFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputStartFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = inputStartFormat.parse(item.startDate.toString())
        val formattedStartDate = outputStartFormat.format(date!!)

        val inputEndFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputEndFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val dateEnd = inputEndFormat.parse(item.endDate.toString())
        val formattedEndtDate = outputEndFormat.format(dateEnd!!)

        binding.tituloBecaCard.text = item.name // Se le cambia el valor a la card
        binding.fechaBeca.text =  formattedStartDate
        binding.tipoBeca.text = item.sector
        binding.desBeca.text = item.description
        binding.orgaBeca.text = item.organization

        CoroutineScope(Dispatchers.Main).launch {
            val urlImage = item.image

            val requestOptions = RequestOptions()
                .priority(Priority.HIGH)

            Glide.with(context).load(urlImage)
                .apply(requestOptions)
                .into(binding.imagenBeca)
        }

        binding.buttonInfo1.setOnClickListener {
            passViewGoToProgramDetail(item._id,context)
        }


    }

    /**
     * gets information about Scholarship to load the image of the card with Glide
     *
     * @param url URL of the said program that includes de Image attribute
     * @param imageView the widget used in the layout
     * @param context context of the view
     */
    private fun getScholarshipInfo(url: String, imageView: ImageView, context: Context) {

        CoroutineScope(Dispatchers.IO).launch {
            val scholarshipListRequirement = ScholarshipListRequirement()
            val result: BecasObjeto? = scholarshipListRequirement(32)
            CoroutineScope(Dispatchers.Main).launch {
                val urlImage = result?.data?.documents?.iterator()?.next()?.image

                val requestOptions = RequestOptions()
                    .fitCenter()
                    .priority(Priority.HIGH)

                Glide.with(context).load(urlImage?.toString())
                    .apply(requestOptions)
                    .into(imageView)
            }
        }
    }

    /**
     * function that passes the information of each program to a new activity
     *
     * @param programID the ID of the program to get information
     * @param context view context for the scholarship
     */
    private fun passViewGoToProgramDetail(scholarshipID: String, context: Context) {
        val intent = Intent(context, ScholarshipDetailActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        intent.putExtra(Constants.ID_SCHOLARSHIP, scholarshipID)
        context.startActivity(intent)
    }
}