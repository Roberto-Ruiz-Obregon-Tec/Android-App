package com.example.kotlin.robertoruizapp.data.network.model.Eventos

data class Document (

    val ubicacion: String,
    val _id: String,
    val nombre: String,
    val descripcion: String,
    val fecha_Inicio: String,
    val fecha_Fin: String,
    val fotoUrl: String
)