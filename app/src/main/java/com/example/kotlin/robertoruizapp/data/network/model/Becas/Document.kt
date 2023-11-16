package com.example.kotlin.robertoruizapp.data.network.model.Becas

import java.util.Date

/**
 * Document
 *
 * @property id: id of the scholarship
 * @property name: name of the scholarship
 * @property description: description of the scholarship
 * @property organization: organization of the scholarship
 * @property sector: sector of the scholarship
 * @property location: location of the scholarship
 * @property email: email where you can contact the scholarship organization
 * @property image: image of the scholarship
 * @property phone: phone where you can contact the scholarship organization
 * @property startDate: date of the start of the scholarship
 * @property endDate: date of the end of the scholarship
 * @constructor Create empty Document
 */
class Document (
    val id: String,
    val name: String,
    val description: String,
    val organization: String,
    val sector: String,
    val location: String,
    val email:String,
    val image:String,
    val phone:String,
    val startDate: String,
    val endDate: String,
)

