package com.example.kotlin.robertoruizapp.data.network.model.certificaciones

/*
    * Created by Dante Perez 2/11/2023
    * A UserDocument data class representing a certification object.
    *
    * Parameters:
    * _id: A String representing the id of the document.
    * name: A String representing the name of the document.
    * description: A String representing the description of the document.
    * adquisitionDate: A String representing the adquisition date of the document.
    *
    * Methods:
    * Override toString(): Returns a String representation of the UserDocument object.
    *
 */
class Document(
    val _id: String,
    val name : String,
    val description : String,
    val adquisitionDate : String
)
{
    override fun toString(): String {
        return "UserDocument(_id='$_id', name='$name', description='$description', adquisitionDate='$adquisitionDate')"
    }
}
