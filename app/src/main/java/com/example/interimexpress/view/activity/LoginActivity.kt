package com.example.interimexpress.view.activity


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.interimexpress.R
import com.example.interimexpress.controller.CandidatController
import com.example.interimexpress.controller.EmployeurController
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextMail: TextInputEditText
    private lateinit var editTextPassword: TextInputEditText
    private lateinit var loginButton: Button
    private val candidatController = CandidatController()
    private val employeurController = EmployeurController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextMail = findViewById(R.id.champ_mail)
        editTextPassword = findViewById(R.id.editTextPassword)
        loginButton = findViewById(R.id.login_button)

        loginButton.setOnClickListener {
            login()
        }

        //TESTING
        val autoFillCandidat = findViewById<Button>(R.id.autoFillCandidat)
        val autoFillEmployeur = findViewById<Button>(R.id.autoFillEmployeur)

        autoFillCandidat.setOnClickListener {
            editTextMail.setText("john.doe@example.com")
            editTextPassword.setText("password123")
        }

        autoFillEmployeur.setOnClickListener {
            editTextMail.setText("contact1@entrepriseA.com")
            editTextPassword.setText("password123")
        }
//FIN TESTING
    }

    private fun login() {
        val mail = editTextMail.text.toString()
        val password = editTextPassword.text.toString()

        candidatController.getCandidatByMailAndPassword(mail, password).addOnSuccessListener { result ->
            if (!result.isEmpty) {
                val role = result.documents[0]["role"] as String?
                saveUserRole(role)
            } else {
                employeurController.getEmployeurByMailAndPassword(mail, password).addOnSuccessListener { result ->
                    if (!result.isEmpty) {
                        val role = result.documents[0]["role"] as String?
                        saveUserRole(role)
                    } else {
                        // Aucun utilisateur trouvé, afficher un message d'erreur
                        Toast.makeText(this, "Aucun utilisateur trouvé", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun saveUserRole(role: String?) {
        val sharedPreferences = getSharedPreferences("InterimExpress", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("userRole", role).apply()

        val intent = if (role == "Candidat") {
            Intent(this, RechercheOffresActivity::class.java)
        } else if (role == "Employeur") {
            Intent(this, EmployeurDashboardActivity::class.java)
        } else {
            Intent(this, MainActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}
