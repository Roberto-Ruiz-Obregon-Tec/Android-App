package com.example.kotlin.robertoruizapp.framework.viewmodel

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

            CoroutineScope(Dispatchers.Main).launch {
                bankList.postValue(result?.data?.banks)
            }

        }
    }
}