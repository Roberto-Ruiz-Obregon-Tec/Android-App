package com.example.kotlin.robertoruizapp.domain
import com.example.kotlin.robertoruizapp.data.companyCertificationRepository
import com.example.kotlin.robertoruizapp.data.network.model.companyCertification.CertificacionEmpresaObj
import com.example.kotlin.robertoruizapp.framework.view.activities.LoginActivity

class companyCertificationListRequirement {
    private val companyCertificationRepository = companyCertificationRepository()

    /**
     *
     * The list of business certification requirements.
     * This function operates on the repository [CompanyCertificationRepository]
     *
     * @param token The token that is necessary from [LoginActivity] to be able to perform authentication
     *
     * @return Returns the object [CertificacionEmpresaObj] with details of companies and their certifications
     */

    suspend operator fun invoke(token: String): CertificacionEmpresaObj? = companyCertificationRepository.getCompanyCertification(
        LoginActivity.token)
}