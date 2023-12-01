package com.example.kotlin.robertoruizapp.framework.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.robertoruizapp.data.CourseRepository
import com.example.kotlin.robertoruizapp.data.network.model.ApiService
import com.example.kotlin.robertoruizapp.data.network.model.Inscripcion.Inscription
import com.example.kotlin.robertoruizapp.data.network.model.Inscripcion.Result
import com.example.kotlin.robertoruizapp.data.network.model.NetworkModuleDI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * FichaPagoViewModel that manages the activity actions
 */
class FichaPagoViewModel: ViewModel() {

    val voucherLiveData = MutableLiveData<String?>()
    val postInscription = CourseRepository()

    fun sendNum(token: String, course: String, num: String){
        viewModelScope.launch(Dispatchers.IO) {
            val result: String? = postInscription.postInscription(course, num, token)
            CoroutineScope(Dispatchers.Main).launch {
                voucherLiveData.postValue(result)
            }
        }}

}

