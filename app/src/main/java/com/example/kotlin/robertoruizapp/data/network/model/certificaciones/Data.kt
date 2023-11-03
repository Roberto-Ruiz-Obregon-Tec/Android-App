package com.example.kotlin.robertoruizapp.data.network.model.certificaciones
import com.example.kotlin.robertoruizapp.data.network.model.certificaciones.Document

data class Data(
    val documents: List<Document>
) {
    override fun toString(): String {
        return "Data(documents=$documents)"
    }
}