package com.example.kotlin.robertoruizapp.data.network.model.companyCertification

import com.example.kotlin.robertoruizapp.data.network.model.companyCertification.Data

data class CertificacionEmpresaObj (
    val `data`: Data,
    val results: Int?,
    val status: String
)