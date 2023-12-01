package com.example.kotlin.robertoruizapp.framework.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.robertoruizapp.data.network.model.BankAccount.BankDocument
import com.example.kotlin.robertoruizapp.data.network.model.BankAccount.BankObject
import com.example.kotlin.robertoruizapp.domain.BankListRequirement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BankViewModel() : ViewModel() {

    val bankList = MutableLiveData<ArrayList<BankDocument>?>()
    private val getBankListRequirement = BankListRequirement()

    fun getListBanks(){
        viewModelScope.launch(Dispatchers.IO){
            val result: BankObject? = getBankListRequirement()

            Log.d("prueba", "setupreclycler: ${result}")

            val bancos : ArrayList<BankDocument> = ArrayList(result?.data?.accounts)

            CoroutineScope(Dispatchers.Main).launch {
                bankList.postValue(bancos)
            }

        }
    }
}