package com.example.kotlin.robertoruizapp.framework.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.framework.viewmodel.SignUpActivityViewModel
import com.example.kotlin.robertoruizapp.data.network.model.signup.SignUp


class SignUpActivity : AppCompatActivity() {
    private lateinit var viewModel: SignUpActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val generos = arrayOf("Hombre", "Mujer", "Prefiero no decir")
        val gender = findViewById<Spinner>(R.id.spinnerSex)
        gender.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, generos)

        val estudios = arrayOf("Ninguno", "Primaria", "Secundaria", "Preparatoria", "Universidad", "Maestria", "Doctorado")
        val studies = findViewById<Spinner>(R.id.spinnerEducation)
        studies.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, estudios)

        val name: EditText = findViewById(R.id.editTextName)
        val edad: EditText = findViewById<EditText>(R.id.editTextAge)
        val job: EditText = findViewById<EditText>(R.id.editTextJob)
        val postalCode: EditText = findViewById<EditText>(R.id.editTextPostalCode)
        val email: EditText = findViewById<EditText>(R.id.editTextEmail)
        val password: EditText = findViewById<EditText>(R.id.editTextPassword)
        val cnfPassword: EditText = findViewById<EditText>(R.id.editTextCnfPassword)

        fun initViewModel() {
            viewModel = ViewModelProvider(this)[SignUpActivityViewModel::class.java]
            viewModel.getSignUpNewUserObserver().observe(this) {

                if (it == null) {
                    Toast.makeText(
                        this@SignUpActivity,
                        "Failed to register User",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        this@SignUpActivity,
                        "Successfully register User",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        initViewModel()

        val btnRegister = findViewById<Button>(R.id.buttonRegister)
        val btnGoLogin = findViewById<Button>(R.id.buttonGoLogin)
        fun signUpUser() {

            val ageInt: Int = edad.text.toString().toIntOrNull() ?: 0
            val pcInt: Int = postalCode.text.toString().toIntOrNull() ?: 0
            val selectedGender = gender.selectedItem.toString()
            val selectedStudies = studies.selectedItem.toString()
            val user = SignUp(
                name.text.toString(),
                ageInt,
                selectedGender,
                job.text.toString(),
                selectedStudies,
                pcInt,
                email.text.toString(),
                password.text.toString(),
                cnfPassword.text.toString(),
            )
            viewModel.signUpNewUser(user)

        }

        btnRegister.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            signUpUser()
            startActivity(intent)

        }

        btnGoLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }

    }
}