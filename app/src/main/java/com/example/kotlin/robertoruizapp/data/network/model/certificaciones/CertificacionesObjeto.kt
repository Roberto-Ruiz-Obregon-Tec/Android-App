package com.example.kotlin.robertoruizapp.data.network.model.certificaciones
import com.example.kotlin.robertoruizapp.data.network.model.certificaciones.Data

data class CertificacionesObjeto(
    val `data`: Data,
    val results: Int?,
    val status: String
)
