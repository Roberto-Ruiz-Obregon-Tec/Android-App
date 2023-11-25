package com.example.kotlin.robertoruizapp.framework.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _filtroActual = MutableLiveData<String>()
    val filtroActual: LiveData<String> = _filtroActual

    fun setFiltroActual(filtro: String) {
        _filtroActual.value = filtro
    }
}