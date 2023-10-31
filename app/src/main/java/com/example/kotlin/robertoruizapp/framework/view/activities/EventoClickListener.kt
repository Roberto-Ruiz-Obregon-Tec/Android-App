package com.example.kotlin.robertoruizapp.framework.view.activities

import com.example.kotlin.robertoruizapp.data.network.model.Eventos.Document
interface EventoClickListener {
    fun onClick(document: Document)
}