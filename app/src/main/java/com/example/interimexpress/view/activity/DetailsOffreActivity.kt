package com.example.interimexpress.view.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.interimexpress.R
import com.example.interimexpress.controller.OffreController
import com.example.interimexpress.model.DatabaseHelper
import com.example.interimexpress.model.DatabaseManager

class DetailsOffreActivity : AppCompatActivity() {

    private lateinit var databaseManager: DatabaseManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_offre)

        databaseManager = DatabaseManager.getInstance(this)

        // Récupére les données de la base de données et met à jour les TextViews
        val offre = databaseManager.offreController.getOffre(1)
        offre?.let {
            val titreTextView = findViewById<TextView>(R.id.titre)
            val entrepriseTextView = findViewById<TextView>(R.id.titre2)
            val adresseTextView = findViewById<TextView>(R.id.titre3)
            val descriptionTextView = findViewById<TextView>(R.id.titre4)

            titreTextView.text = it.titre
            entrepriseTextView.text = it.entreprise
            adresseTextView.text = it.adresse
            descriptionTextView.text = it.description
        }


    }
}