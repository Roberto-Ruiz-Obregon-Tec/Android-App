package com.example.kotlin.robertoruizapp.data.network.model.certificaciones


class Document(
    val _id: String,
    val name : String,
    val description : String,
    val adquisitionDate : String
)
{
    override fun toString(): String {
        return "Document(_id='$_id', name='$name', description='$description', adquisitionDate='$adquisitionDate')"
    }
}
