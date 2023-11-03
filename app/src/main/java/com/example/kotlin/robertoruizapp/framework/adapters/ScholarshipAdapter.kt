package com.example.kotlin.robertoruizapp.framework.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.robertoruizapp.data.network.model.Becas.Document
import com.example.kotlin.robertoruizapp.databinding.ItemProgramaBinding
import com.example.kotlin.robertoruizapp.databinding.ListElementBecasBinding
import com.example.kotlin.robertoruizapp.framework.adapters.viewholder.ProgramViewHolder
import com.example.kotlin.robertoruizapp.framework.adapters.viewholder.ScholarshipViewHolder

/**
 * ProgramAdapter class that has ProgramAdapter Constructor, onCreateViewholder,
 * onBindViewHolder and getItemCount methods to bind information
 *
 */
class ScholarshipAdapter : RecyclerView.Adapter<ScholarshipViewHolder>() {
    lateinit var data: ArrayList<Document>
    private lateinit var context: Context

    /**
     * Constructor for ProgramAdapter
     *
     * @param basicData list of the data acquired from API
     * @param context view context
     */
    fun ProgramAdapter(basicData: List<Document>, context: Context){
        this.data = basicData as ArrayList<Document>
        this.context = context
    }

    /**
     * Overrides the fun onCreateViewHolder to bin the information extracted from the [ListElementsBeca]
     * and set up the LayoutInflater
     *
     * @param parent gets the ViewGroup
     * @param viewType in an Integer
     *
     * @return ProgramViewHolder type with the information binded
     */


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScholarshipViewHolder {
        val binding = ListElementBecasBinding.inflate(LayoutInflater.from(parent.context), parent,
            false)
        return ScholarshipViewHolder(binding)
    }

    /**
     * Overrides the fun onBindViewHolder to assign the information to the cell view
     *
     * @param holder a ScholarshipViewHolder Object that will have the information binded
     * @param position the position of the holder in the data[position]
     */
    override fun onBindViewHolder(holder: ScholarshipViewHolder, position: Int){
        val item = data[position]
        holder.bind(item,context)
    }

    /**
     * returns the number of items in the data object received from the API
     *
     * @return an n Integer representing the number of items
     */
    override fun getItemCount(): Int {
        return data.size
    }
}