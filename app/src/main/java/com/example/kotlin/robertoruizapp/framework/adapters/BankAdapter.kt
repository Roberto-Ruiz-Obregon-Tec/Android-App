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

class BankAdapter : RecyclerView.Adapter<BankViewHolder>() {
    var data:ArrayList<BankDocument> = ArrayList()
    lateinit var context: Context

    fun BankAdapter(basicData : ArrayList<BankDocument>, context: Context){
        this.data = basicData
        this.context = context
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankViewHolder {
        val binding = ItemBankBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BankViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: BankViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, context)
    }


}