package com.example.kotlin.robertoruizapp.framework.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.robertoruizapp.data.network.model.BankAccount.BankDocument
import com.example.kotlin.robertoruizapp.domain.BankListRequirement
import kotlinx.coroutines.launch

class BankViewModel(private val bankListRequirement: BankListRequirement) : ViewModel() {

    private val _bankList = MutableLiveData<List<BankDocument>>()
    val bankList: LiveData<List<BankDocument>> = _bankList

    init {
        viewModelScope.launch {
            val token = "tu_token" // Debes obtener tu token de alguna manera segura.
            bankListRequirement.invoke(token)?.let { bankObject ->
                //_bankList.postValue(bankObject.data.banks)
                Log.d("prueba", "zi: ${bankObject}")
            }
        }
    }
}