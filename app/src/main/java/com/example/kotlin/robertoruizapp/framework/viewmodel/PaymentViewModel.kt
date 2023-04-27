package com.example.kotlin.robertoruizapp.framework.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin.robertoruizapp.data.network.model.ApiService
import com.example.kotlin.robertoruizapp.data.network.model.NetworkModuleDI

import com.example.kotlin.robertoruizapp.data.network.model.Inscripcion.Pago
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


class PaymentViewModel: ViewModel() {

    var PaymentLiveData: MutableLiveData<Pago?> = MutableLiveData()

    fun getInscriptionObserver(): MutableLiveData<Pago?> {
        return PaymentLiveData
    }
    fun startPayment(token: String, parts: RequestBody, course: String?) {
        val retroService = NetworkModuleDI.getRetroInstance().create(ApiService::class.java)
        val imagePart = MultipartBody.Part.createFormData("image", "image.jpg", parts)
        val call = retroService.postPago(token, imagePart , course)

        //val call = retroService.postPago(token, parts, course)
            call.enqueue(object: Callback<Pago> {
                override fun onFailure(call: Call<Pago>, t: Throwable) {
                    Log.d("Falla de llamada", t.toString())
                    PaymentLiveData.postValue(null)
            }

            override fun onResponse(call: Call<Pago>, response: Response<Pago>) {
                if(response.isSuccessful) {
                    Timber.tag("Salida").d(response.toString())
                    PaymentLiveData.postValue(response.body())
                } else {
                    PaymentLiveData.postValue(null)
                }
            }
        })
    }


}


