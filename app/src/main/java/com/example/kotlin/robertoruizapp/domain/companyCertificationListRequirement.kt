package com.example.kotlin.robertoruizapp.domain
import com.example.kotlin.robertoruizapp.data.companyCertificationRepository
import com.example.kotlin.robertoruizapp.data.network.model.companyCertification.CertificacionEmpresaObj
class companyCertificationListRequirement {
    private val companyCertificationRepository = companyCertificationRepository()

    suspend operator fun invoke(): CertificacionEmpresaObj? = companyCertificationRepository.getCompanyCertification()
}