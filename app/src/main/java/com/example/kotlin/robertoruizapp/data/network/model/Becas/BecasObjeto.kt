package com.example.kotlin.robertoruizapp.data.network.model.Becas

import com.example.kotlin.robertoruizapp.data.network.model.Cursos.Data

data class BecasObjeto(
    val `data`: Data,
    val results: Int?,
    val status: String
)
