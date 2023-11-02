package com.example.kotlin.robertoruizapp.data.network.model.Becas

import java.util.Date

class Document (
    val _id: String,
    val name: String,
    val description: String,
    val organization: String,
    val location: String,
    val email: String,
    val phone: String,
    val image: String,
    val sector: String,
    val startDate: Date,
    val endDate: Date,
)

