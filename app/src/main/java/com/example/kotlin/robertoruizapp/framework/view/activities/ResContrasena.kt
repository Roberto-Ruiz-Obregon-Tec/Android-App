package com.example.kotlin.robertoruizapp.framework.view.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.framework.viewmodel.ResContrasenaViewModel

class ResContrasena : AppCompatActivity() {
    private lateinit var botonEnviar: Button
    private lateinit var editTextEmail: EditText
    private lateinit var editTextConfirmEmail: EditText
    private lateinit var viewModel: ResContrasenaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rescontrasena)

        viewModel = ViewModelProvider(this).get(ResContrasenaViewModel::class.java)

        botonEnviar = findViewById(R.id.buttonSend)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextConfirmEmail = findViewById(R.id.editTextConfirmEmail)

        botonEnviar.setOnClickListener {
            val email = editTextEmail.text.toString().trim()
            val confirmEmail = editTextConfirmEmail.text.toString().trim()

            if (email.isNotEmpty() && confirmEmail.isNotEmpty() && email == confirmEmail) {
                // Observar los datos del ViewModel
                viewModel.checkEmail(email).observe(this) { isEmailRegistered ->
                    if (isEmailRegistered) {
                        Toast.makeText(
                            this,
                            "Si el correo está registrado, recibirás un enlace para restablecer tu contraseña.",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            this,
                            "El correo electrónico no está registrado.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            } else {
                Toast.makeText(this, "Los correos electrónicos no coinciden o están vacíos.", Toast.LENGTH_LONG).show()
            }
        }
    }
}
