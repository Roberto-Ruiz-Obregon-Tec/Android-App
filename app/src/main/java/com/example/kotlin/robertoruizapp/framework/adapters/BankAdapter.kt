package com.example.kotlin.robertoruizapp.framework.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin.robertoruizapp.data.network.model.BankAccount.BankDocument
import com.example.kotlin.robertoruizapp.databinding.FragmentoDetalleDePagoBinding
import com.example.kotlin.robertoruizapp.databinding.ItemBankBinding
import com.example.kotlin.robertoruizapp.framework.adapters.viewholder.BankViewHolder

/**
 * Adapter for displaying a list of banks in a RecyclerView.
 */
class BankAdapter : RecyclerView.Adapter<BankViewHolder>() {
    var data:ArrayList<BankDocument> = ArrayList()
    lateinit var context: Context

    /**
     * Initializes the BankAdapter with data and context.
     *
     * @param basicData The list of [BankDocument] objects to be displayed.
     * @param context The [Context] object used for loading images.
     */
    fun BankAdapter(basicData : ArrayList<BankDocument>, context: Context){
        this.data = basicData
        this.context = context
    }

    /**
     * Creates a new [BankViewHolder] instance by inflating the item layout.
     *
     * @param parent The parent view group in which the new view will be attached.
     * @param viewType The type of the new view.
     * @return A new [BankViewHolder] instance.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankViewHolder {
        val binding = ItemBankBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BankViewHolder(binding)
    }

    /**
     * Returns the total number of items in the dataset.
     *
     * @return The total number of items in the dataset.
     */
    override fun getItemCount(): Int {
        return data.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param holder The [BankViewHolder] that holds the view for the item.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: BankViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, context)
    }


}