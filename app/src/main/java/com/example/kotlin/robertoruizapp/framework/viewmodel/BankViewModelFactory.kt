package com.example.kotlin.robertoruizapp.framework.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin.robertoruizapp.domain.BankListRequirement

class BankViewModelFactory(
    private val bankListRequirement: BankListRequirement
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BankViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BankViewModel(bankListRequirement) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}