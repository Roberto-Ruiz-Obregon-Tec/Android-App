package com.example.kotlin.robertoruizapp.framework.view.activities

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin.robertoruizapp.R
import com.example.kotlin.robertoruizapp.data.network.model.signup.SignUp
import com.example.kotlin.robertoruizapp.framework.viewmodel.SignUpActivityViewModel
import timber.log.Timber
import java.io.ByteArrayOutputStream

/**
 * SignUpActivity class that manages the activity actions
 *
 */
class SignUpActivity : AppCompatActivity() {
    private lateinit var viewModel: SignUpActivityViewModel

    // Declare variables for layout inputs
    private lateinit var name: EditText
    private lateinit var lastName: EditText
    private lateinit var email: EditText
    private lateinit var age: EditText
    private lateinit var genders: Array<String>
    private lateinit var gender: Spinner
    private lateinit var selectedGender: String
    private lateinit var occupation: EditText
    private lateinit var postalCode: EditText
    private lateinit var interests: Array<String>
    private lateinit var interest: Spinner
    private lateinit var selectedInterests: Array<String>
    private lateinit var company: EditText
    private lateinit var companyESR: CheckBox
    private lateinit var profilePicture: String
    private lateinit var password: EditText
    private lateinit var cnfPassword: EditText
    private lateinit var btnRegister: Button

    /**
     * Sets the information for the current activity when creating the view
     *
     * @param savedInstanceState the state of the view
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initComponents()
        initAdapters()
        initListeners()
        initViewModel()

    }

    // Initialize components from the layout inputs
    private fun initComponents() {
        name = findViewById(R.id.editTextName)
        lastName = findViewById(R.id.editTextLastName)
        email = findViewById(R.id.editTextMail)
        age = findViewById(R.id.editTextAge)
        genders = resources.getStringArray(R.array.generos)
        gender = findViewById(R.id.spinnerGender)
        occupation = findViewById(R.id.editTextOccupation)
        postalCode = findViewById(R.id.editTextPC)
        interests = resources.getStringArray(R.array.intereses)
        interest = findViewById(R.id.spinnerInterests)
        company = findViewById(R.id.editTextCompany)
        companyESR = findViewById(R.id.checkboxESR)
        profilePicture = ""
        password = findViewById(R.id.editTextPassword)
        cnfPassword = findViewById(R.id.editTextConfirmPass)
        btnRegister = findViewById(R.id.buttonRegister)
    }

    // Initialize adapters for items selected
    private fun initAdapters() {
        gender.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, genders)
        interest.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, interests)
    }

    private fun initListeners() {

        // Validate Inputs and sign up new user
        btnRegister.setOnClickListener {
            selectedGender = gender.selectedItem.toString()
            selectedInterests = arrayOf(interest.selectedItem.toString())
            if (validateInput()) {
                signUpUser()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                Timber.tag("INPUT_VALIDATOR").d("Error Validating Inputs")
            }
        }
    }

    private fun signUpUser() {
        val ageInt: Int = age.text.toString().toIntOrNull() ?: 0
        val pcInt: Int = postalCode.text.toString().toIntOrNull() ?: 0
        val user = SignUp(
            name.text.toString(),
            lastName.text.toString(),
            email.text.toString(),
            ageInt,
            selectedGender,
            occupation.text.toString(),
            pcInt,
            selectedInterests,
            company.text.toString(),
            companyESR.isChecked,
            profilePicture,
            password.text.toString()
        )
        try {
            viewModel.signUpNewUser(user)
        } catch (e: Exception) {
            Toast.makeText(
                applicationContext,
                "$e",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    private fun initViewModel() {
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

    private fun validateInput(
        name: String,
        lastName: String,
        email: String,
        age: String,
        selectedGender: String,
        occupation: String,
        postalCode: String,
        interests: String,
        password: String,
        cnfPassword: String
    ): Boolean {
        if (name.isEmpty() || lastName.isEmpty()) {
            Toast.makeText(this, "El nombre no puede estar vacío.", Toast.LENGTH_SHORT).show()
            return false
        }

        if (email.text.toString().isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
            Toast.makeText(this, "Ingrese un correo electrónico válido.", Toast.LENGTH_SHORT).show()
            return false
        }

        if (age.text.toString().isEmpty()) {
            Toast.makeText(this, "La edad no puede estar vacía.", Toast.LENGTH_SHORT).show()
            return false
        }

        if (selectedGender == genders[0]) {
            Toast.makeText(this, "Por favor, seleccione su preferencia de Genero.", Toast.LENGTH_SHORT).show()
            return false
        }

        if (postalCode.text.toString().isEmpty()) {
            Toast.makeText(this, "El código postal no puede estar vacío.", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        if (selectedInterests.size <= 1 && selectedInterests[0] == interests[0]) {
            selectedInterests = emptyArray()
        }

        if (password.text.toString().isEmpty() || cnfPassword.text.toString().isEmpty()) {
            Toast.makeText(this, "La contraseña no puede estar vacía.", Toast.LENGTH_SHORT).show()
            return false
        } else if (password.text.toString().length < 8) {
            Toast.makeText(this, "La contraseña debe contener al menos 8 caracteres.", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.text.toString() != cnfPassword.text.toString()) {
            Toast.makeText(this, "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

}