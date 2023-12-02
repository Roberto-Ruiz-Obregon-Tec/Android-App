package com.example.kotlin.robertoruizapp.framework.view.activities

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.network.ApiResponse
import com.example.kotlin.robertoruizapp.framework.viewmodel.ForgotPasswordViewModel
import com.example.kotlin.robertoruizapp.framework.viewmodel.Resource

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var botonEnviar: Button
    private lateinit var editTextEmail: EditText
    private lateinit var viewModel: ForgotPasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgotpassword)

        viewModel = ViewModelProvider(this).get(ForgotPasswordViewModel::class.java)

        botonEnviar = findViewById(R.id.buttonSend)
        editTextEmail = findViewById(R.id.editTextEmail)

        botonEnviar.setOnClickListener {
            val email = editTextEmail.text.toString().trim()

            if (email.isNotEmpty()) {
                viewModel.forgotPassword(email).observe(this) { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            if (resource.data?.success == true) {
                                Toast.makeText(
                                    this,
                                    "Si el correo está registrado, recibirás un enlace para restablecer tu contraseña.",
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                Toast.makeText(
                                    this,
                                    resource.data?.message ?: "Error desconocido",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                        is Resource.Error -> {
                            Toast.makeText(
                                this,
                                resource.message ?: "Ha ocurrido un error",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        is Resource.Loading -> {
                            Toast.makeText(
                                this,
                                resource.message ?: "Cargando..",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Por favor ingresa tu correo electrónico.", Toast.LENGTH_LONG).show()
            }
        }
    }
}
