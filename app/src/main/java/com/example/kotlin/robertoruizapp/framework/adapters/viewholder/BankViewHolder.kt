package com.example.kotlin.robertoruizapp.framework.adapters.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.kotlin.robertoruizapp.data.network.model.BankAccount.BankDocument
import com.example.kotlin.robertoruizapp.databinding.ItemBankBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewHolder class for displaying bank information in a RecyclerView.
 *
 * @param binding The data binding instance for the bank item layout.
 */
class BankViewHolder(private val binding: ItemBankBinding) : RecyclerView.ViewHolder(binding.root) {
    /**
     * Binds bank data to the ViewHolder.
     *
     * @param item The [BankDocument] object containing bank information.
     * @param context The [Context] object for loading images.
     */
    fun bind(item: BankDocument, context: Context){
        binding.cuentaCurso.text = item.bank
        binding.cursoCosto.text = item.accountNumber

        CoroutineScope(Dispatchers.Main).launch {
            val urlImage = item.bankImage

            val requestOptions =  RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .priority(Priority.HIGH)

            Glide.with(context).load(urlImage)
                .apply(requestOptions)
                .into(binding.imagenBanco)
        }
    }

}