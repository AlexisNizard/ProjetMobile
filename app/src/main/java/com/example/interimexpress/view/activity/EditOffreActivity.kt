package com.example.interimexpress.view.activity

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.interimexpress.R
import com.example.interimexpress.controller.OffreController
import com.example.interimexpress.model.Offre

class EditOffreActivity : AppCompatActivity() {
    private lateinit var offreController: OffreController
    private lateinit var offre: Offre
    private lateinit var titreEditText: EditText
    private lateinit var titre2EditText: EditText
    private lateinit var titre3EditText: EditText
    private lateinit var titre4EditText: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_offre)

        offreController = OffreController()
        titreEditText = findViewById(R.id.titre)
        titre2EditText = findViewById(R.id.titre2)
        titre3EditText = findViewById(R.id.titre3)
        titre4EditText = findViewById(R.id.titre4)
        saveButton = findViewById(R.id.rechercher_button)

        val imgProfil = findViewById<ImageView>(R.id.logo);
        imgProfil.setOnClickListener {
            val intent = Intent(this, EmployeurDashboardActivity::class.java)
            startActivity(intent)
        }


        val offreId = intent.getStringExtra("offre_id")
        if (offreId != null) {
            offreController.getOffre(offreId).addOnSuccessListener { document ->
                offre = document.toObject(Offre::class.java)!!
                titreEditText.setText(offre.titre)
                titre2EditText.setText(offre.entreprise)
                titre3EditText.setText(offre.adresse)
                titre4EditText.setText(offre.description)
            }
        }

        saveButton.setOnClickListener {
            updateOffre()
        }

    }

    private fun updateOffre() {
        offre.titre = titreEditText.text.toString()
        offre.entreprise = titre2EditText.text.toString()
        offre.adresse = titre3EditText.text.toString()
        offre.description = titre4EditText.text.toString()

        offreController.updateOffre(offre).addOnSuccessListener {
            // Le document a été mis à jour avec succès
            val intent = Intent(this, MesOffresActivity::class.java)
            Toast.makeText(this, "Offre mise à jour avec succès", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }.addOnFailureListener { e ->
            // Une erreur est survenue lors de la mise à jour du document
            Log.w(TAG, "Error updating document", e)
        }
    }
}