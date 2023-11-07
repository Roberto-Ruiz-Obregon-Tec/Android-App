package com.example.kotlin.robertoruizapp.data.network.model.certificaciones
import com.example.kotlin.robertoruizapp.data.network.model.certificaciones.Document

/*
    * Created by Dante Perez 2/11/2023
    * A data class representing a certification object.
    *
    * Parameters:
    *  data: A Data object containing the certification data.
    *  results: An Int representing the number of results.
    *  status: A String representing the status of the certification object.
    *
    * Methods:
    *  None.

 */
data class Data(
    val documents: List<Document>
) {
    override fun toString(): String {
        return "Data(documents=$documents)"
    }
}