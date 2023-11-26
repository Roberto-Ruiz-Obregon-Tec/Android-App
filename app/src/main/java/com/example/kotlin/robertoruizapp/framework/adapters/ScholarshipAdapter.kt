package com.example.kotlin.robertoruizapp.framework.adapters

import androidx.recyclerview.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.network.model.Becas.Document
import java.text.SimpleDateFormat
import java.util.Locale


/**
 * Scholarship adapter
 *
 * @property becas: Receives the list of Becas and modifies the view.
 * @constructor Create empty Scholarship adapter
 */
class ScholarshipAdapter(private val becas: List<Document>) :
    RecyclerView.Adapter<ScholarshipAdapter.ViewHolder>() {
    /**
     * View holder
     *
     * @constructor
     *
     * @param view: Hold the card that is going to be modified
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val becaName: TextView = view.findViewById(R.id.titulo_beca_card)
        val becaDescription: TextView = view.findViewById(R.id.des_beca)
        val becaDate: TextView = view.findViewById(R.id.fecha_beca)
        val becaSector: TextView = view.findViewById(R.id.tipo_beca)
        val becaOrga: TextView = view.findViewById(R.id.orga_beca)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_becas, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beca = becas[position]
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        if (beca.startDate != null)
        {
            val date = inputFormat.parse(beca.startDate)
            val formattedDate = outputFormat.format(date)
            holder.becaDate.text = formattedDate
        }
        else{
            holder.becaDate.text = "25/12/2023"
        }
        holder.becaName.text = beca?.name
        holder.becaDescription.text = beca?.description
        holder.becaSector.text = beca?.sector
        holder.becaOrga.text = beca?.organization
    }

    override fun getItemCount() = becas.size
}


