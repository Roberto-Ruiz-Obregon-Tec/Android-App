package com.example.kotlin.robertoruizapp.data.network.model.Becas


/**
 * Becas objeto
 *
 * @property data contains all the information from de api request
 * @property results number of elements in data
 * @property status mentions the status of the request
 * @constructor Create empty Becas objeto
 */
data class BecasObjeto(
    val `data`: Data,
    val results: Int?,
    val status: String
)
