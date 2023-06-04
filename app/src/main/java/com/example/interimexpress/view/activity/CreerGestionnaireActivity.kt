package com.example.interimexpress.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.interimexpress.R
import com.example.interimexpress.controller.GestionnaireController
import com.example.interimexpress.model.Gestionnaire
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

class CreerGestionnaireActivity : AppCompatActivity() {

    private lateinit var editTextMail1: TextInputEditText
    private lateinit var editTextPassword: TextInputEditText
    private lateinit var btnCreationGest: Button
    private val controller = GestionnaireController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creer_gestionnaire)

        editTextMail1 = findViewById(R.id.editTextMail1)
        editTextPassword = findViewById(R.id.editTextPassword)
        btnCreationGest = findViewById(R.id.btn_creationGest)

        btnCreationGest.setOnClickListener {
            val mail = editTextMail1.text.toString()
            val password = editTextPassword.text.toString()

            if (mail.isNotEmpty() && password.isNotEmpty()) {
                val gestionnaire = Gestionnaire(mail, password, "Gestionnaire")

                controller.insertGestionnaire(gestionnaire)
                Toast.makeText(this, "Votre gestionnaire a été créé avec succès", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, AdminDashboardActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
