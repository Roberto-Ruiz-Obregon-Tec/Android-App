package com.example.kotlin.robertoruizapp.framework.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin.robertoruizapp.data.network.model.BankAccount.BankDocument
import com.example.kotlin.robertoruizapp.databinding.ItemBankBinding

class BankAdapter(private val bankList: List<BankDocument>) : RecyclerView.Adapter<BankAdapter.BankViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankViewHolder {
        val binding = ItemBankBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BankViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BankViewHolder, position: Int) {
        val bank = bankList[position]
        holder.bind(bank)
    }

    override fun getItemCount() = bankList.size

    class BankViewHolder(private val binding: ItemBankBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bankDocument: BankDocument) {
            with(binding) {

                Glide.with(itemView.context).load(bankDocument.bankImage).into(imagenBanco)

                cuentaCurso.text = bankDocument.accountNumber.toString()
                cursoCosto.text = bankDocument.propietary
            }
        }
    }
}