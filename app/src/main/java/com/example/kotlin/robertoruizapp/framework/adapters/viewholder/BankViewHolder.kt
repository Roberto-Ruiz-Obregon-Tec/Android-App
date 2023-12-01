package com.example.kotlin.robertoruizapp.framework.adapters.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.robertoruizapp.data.network.model.BankAccount.BankDocument
import com.example.kotlin.robertoruizapp.databinding.ItemBankBinding

class BankViewHolder(private val binding: ItemBankBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: BankDocument, context: Context){
        binding.cuentaCurso.text = item.bank
        binding.cursoCosto.text = item.accountNumber.toString()
    }

}