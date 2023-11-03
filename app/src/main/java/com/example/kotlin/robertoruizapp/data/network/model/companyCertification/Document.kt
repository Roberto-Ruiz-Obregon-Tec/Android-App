package com.example.kotlin.robertoruizapp.data.network.model.companyCertification

class Document (
    val _id: String,
    val postalCode: String,
    val name: String,
    val description: String,
    val phone: String,
    val certifications: List<String>
)