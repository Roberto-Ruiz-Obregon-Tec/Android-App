package com.example.kotlin.robertoruizapp.framework.view.activities

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
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
    private var profilePicture: String = ""

    /**
     * Sets the information for the current activity when creating the view
     *
     * @param savedInstanceState the state of the view
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val generos = arrayOf("Hombre", "Mujer", "Otro")
        val gender = findViewById<Spinner>(R.id.spinnerGender)
        gender.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, generos)

        val interests = arrayOf("Selecciona uno o más intereses","Interés 1","Interés 2","Interés 3")
        val interest = findViewById<Spinner>(R.id.spinnerInterests)
        interest.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, interests)

        val name: EditText = findViewById(R.id.editTextName)
        val lastName: EditText = findViewById(R.id.editTextLastName)
        val email: EditText = findViewById<EditText>(R.id.editTextMail)
        val edad: EditText = findViewById<EditText>(R.id.editTextAge)
        val occupation: EditText = findViewById<EditText>(R.id.editTextOccupation)
        val postalCode: EditText = findViewById<EditText>(R.id.editTextPC)
        val company: EditText = findViewById<EditText>(R.id.editTextCompany)
        val companyESR: CheckBox = findViewById<CheckBox>(R.id.checkboxESR)
        val password: EditText = findViewById<EditText>(R.id.editTextPassword)
        val cnfPassword: EditText = findViewById<EditText>(R.id.editTextConfirmPass)

        val uploadPicBtn= findViewById<Button>(R.id.uploadProfilePic)
        uploadPicBtn.setOnClickListener {
            val iGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(iGallery, 3)
        }

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
        fun signUpUser() {

            val ageInt: Int = edad.text.toString().toIntOrNull() ?: 0
            val pcInt: Int = postalCode.text.toString().toIntOrNull() ?: 0
            val selectedGender = gender.selectedItem.toString()
            val selectedInterests = interest.selectedItem.toString()
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

        btnRegister.setOnClickListener {
            if (validateInput(
                    name.text.toString(),
                    lastName.text.toString(),
                    email.text.toString(),
                    edad.text.toString(),
                    gender.selectedItem.toString(),
                    occupation.text.toString(),
                    postalCode.text.toString(),
                    interest.selectedItem.toString(),
                    company.toString(),
                    companyESR.isChecked,
                    profilePicture,
                    password.text.toString(),
                    cnfPassword.text.toString()
                )) {
                signUpUser()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                Timber.tag("INPUT_VALIDATOR").d("Error Validating Inputs")
            }
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && data != null){
            val selectedImage: Uri = data.getData()!!
            val img: ImageView = findViewById(R.id.imageViewProfilePic)
            val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedImage)
            img.setImageBitmap(bitmap)
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            val bytes: ByteArray = byteArrayOutputStream.toByteArray()
            val base64Image: String = Base64.encodeToString(bytes, Base64.DEFAULT)
            profilePicture = base64Image
        } else {
            Toast.makeText(applicationContext, "Seleccione una imagen!", Toast.LENGTH_SHORT).show()
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
        company: String?,
        companyESR: Boolean?,
        profilePicture: String,
        password: String,
        cnfPassword: String
    ):Boolean {
        if (name.isEmpty() || lastName.isEmpty()) {
            Toast.makeText(this, "El nombre no puede estar vacío.", Toast.LENGTH_SHORT).show()
            return false
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Ingrese un correo electrónico válido.", Toast.LENGTH_SHORT).show()
            return false
        }

        if (age.isEmpty()) {
            Toast.makeText(this, "La edad no puede estar vacía.", Toast.LENGTH_SHORT).show()
            return false
        }

        if (selectedGender == "Seleccione su género") {
            Toast.makeText(this, "Por favor, seleccione su Preferencia.", Toast.LENGTH_SHORT).show()
            return false
        }

        if (occupation.isEmpty()) {
            Toast.makeText(this, "La Ocupación no puede estar vacía.", Toast.LENGTH_SHORT).show()
            return false
        }

        if (postalCode.isEmpty()) {
            Toast.makeText(this, "El código postal no puede estar vacío.", Toast.LENGTH_SHORT).show()
            return false
        }

        if (interests.isEmpty()) {
            Toast.makeText(this, "Por favor, seleccione al menos un interes.", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.isEmpty() || cnfPassword.isEmpty()) {
            Toast.makeText(this, "La contraseña no puede estar vacía.", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password != cnfPassword) {
            Toast.makeText(this, "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

}