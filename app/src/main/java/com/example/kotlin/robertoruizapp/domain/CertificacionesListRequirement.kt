package com.example.kotlin.robertoruizapp.domain

import com.example.kotlin.robertoruizapp.data.network.model.certificaciones.CertificacionesObjeto
import com.example.kotlin.robertoruizapp.data.CertificacionesRepository
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity

/*
    * Created by Dante Perez 2/11/2023
    * a class that represents as intermedary between the data layer and the presentation layer.
    * It is used to get the certification data from the repository.
    * Parameters:
    * certificacionesRepository: A CertificacionesRepository object.
    *
    *
    * Methods:
    * invoke(): A suspend function that returns a CertificacionesObjeto object.
    * @return a CertificacionesObjeto object containing the certification data.
    *
 */
class CertificacionesListRequirement {

    private val certificacionesRepository = CertificacionesRepository()

    suspend operator fun invoke(token: String): CertificacionesObjeto? = certificacionesRepository.getCertificaciones(
        LoginActivity.token
    )
}