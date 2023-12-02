package com.example.kotlin.robertoruizapp.data.network.model.BankAccount

/**
 * Represents a bank document information.
 *
 * @property _id The unique identifier of the bank document.
 * @property bank The name of the bank associated with this document.
 * @property accountNumber The account number associated with this document.
 * @property propietary The name of the document's proprietor.
 * @property bankImage The image associated with the bank document.
 */
class BankDocument (
    val _id: String,
    val bank: String,
    val accountNumber: String,
    val propietary: String,
    val bankImage: String
)