package com.example.kotlin.robertoruizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< HEAD
import com.example.kotlin.robertoruizapp.data.CertificacionesRepository
import com.example.kotlin.robertoruizapp.data.network.model.certificaciones.CertificacionesObjeto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

=======
import com.example.kotlin.robertoruizapp.data.companyCertificationRepository
import com.example.kotlin.robertoruizapp.data.network.model.companyCertification.CertificacionEmpresaObj
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
>>>>>>> develop

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}