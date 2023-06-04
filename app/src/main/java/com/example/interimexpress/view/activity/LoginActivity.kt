package com.example.interimexpress.view.activity


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.interimexpress.R
import com.example.interimexpress.controller.*
import com.example.interimexpress.model.Employeur
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextMail: TextInputEditText
    private lateinit var editTextPassword: TextInputEditText
    private lateinit var loginButton: Button
    private val candidatController = CandidatController()
    private val employeurController = EmployeurController()
    private val agenceController = AgenceController()
    private val adminController = AdminController()
    private val gestionnaireController= GestionnaireController()

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
        val autoFillAgence = findViewById<Button>(R.id.autoFillAgence)
        val autoFillAdmin = findViewById<Button>(R.id.autoFillAdmin)
        val autoFillGestionnaire = findViewById<Button>(R.id.autoFillGestionnaire)

        autoFillCandidat.setOnClickListener {
            editTextMail.setText("john.doe@example.com")
            editTextPassword.setText("password123")
        }

        autoFillEmployeur.setOnClickListener {
            editTextMail.setText("contact1@entrepriseA.com")
            editTextPassword.setText("password123")
        }

        autoFillAgence.setOnClickListener {
            editTextMail.setText("contact1@entrepriseB.com")
            editTextPassword.setText("password123")
        }

        autoFillAdmin.setOnClickListener {
            editTextMail.setText("admin@gmail.com")
            editTextPassword.setText("admin")
        }
        autoFillGestionnaire.setOnClickListener {
            editTextMail.setText("gestionnaire1@gmail.com")
            editTextPassword.setText("mdp")
        }


//FIN TESTING
    }

    private fun login() {
        val mail = editTextMail.text.toString()
        val password = editTextPassword.text.toString()

        candidatController.getCandidatByMailAndPassword(mail, password).addOnSuccessListener { result ->
            if (!result.isEmpty) {
                val role = result.documents[0]["role"] as String?
                saveUserRole(mail, role)
            } else {
                employeurController.getEmployeurByMailAndPassword(mail, password).addOnSuccessListener { result ->
                    if (!result.isEmpty) {
                        val role = result.documents[0]["role"] as String?
                        saveUserRole(mail, role)
                    } else {
                        agenceController.getAgenceByMailAndPassword(mail, password).addOnSuccessListener { result ->
                            if (!result.isEmpty) {
                                val role = result.documents[0]["role"] as String?
                                saveUserRole(mail, role)
                            } else {
                                // Ajout des cas pour Admin et Gestionnaire
                                adminController.getAdminByMailAndPassword(mail, password).addOnSuccessListener { result ->
                                    if (!result.isEmpty) {
                                        val role = result.documents[0]["role"] as String?
                                        saveUserRole(mail, role)
                                    } else {
                                        gestionnaireController.getGestionnaireByMailAndPassword(mail, password).addOnSuccessListener { result ->
                                            if (!result.isEmpty) {
                                                val role = result.documents[0]["role"] as String?
                                                saveUserRole(mail, role)
                                            } else {
                                                // Aucun utilisateur trouvé, afficher un message d'erreur
                                                Toast.makeText(this, "Aucun utilisateur trouvé", Toast.LENGTH_SHORT).show()
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    private fun saveUserRole(mail: String, role: String?) {
        val sharedPreferences = getSharedPreferences("InterimExpress", Context.MODE_PRIVATE)
        sharedPreferences.edit()
            .putString("userRole", role)
            .putString("userMail", mail)
            .apply()

        val intent = when(role) {
            "Candidat" -> Intent(this, RechercheOffresActivity::class.java)
            "Agence" -> Intent(this, AgenceDashboardActivity::class.java)
            "Admin" -> Intent(this, AdminDashboardActivity::class.java)
            "Gestionnaire" -> Intent(this, GestionnaireDashboardActivity::class.java)
            else -> Intent(this, MainActivity::class.java)
        }

        if(role=="Employeur"){
            val employeurController = EmployeurController()
            val empTask = employeurController.getEmployeur(mail)

            empTask.addOnSuccessListener { document ->
                if (document.exists()) {
                    val employeur = document.toObject(Employeur::class.java)
                    if (employeur?.valide == 0) {
                        val intent = Intent(this, EmployeurDashboardLockedActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else {
                        val intent = Intent(this, EmployeurDashboardActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }
        else{
            startActivity(intent)
            finish()
        }
    }


}
