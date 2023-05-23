package com.example.interimexpress.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.interimexpress.R
import com.example.interimexpress.adapter.OffreAdapter
import com.example.interimexpress.controller.OffreController
import com.example.interimexpress.model.Offre
import com.google.firebase.Timestamp
import java.util.concurrent.TimeUnit

class RechercheOffresActivity : AppCompatActivity() {


    private lateinit var offreController: OffreController
    private lateinit var offreAdapter: OffreAdapter
    private lateinit var offresRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recherche_offres)

        val imgProfil = findViewById<ImageView>(R.id.logo);

        imgProfil.setOnClickListener {
            val intent = Intent(this, CandidatDashboardActivity::class.java)
            startActivity(intent)
        }


        offresRecyclerView = findViewById(R.id.offres_recycler_view)
        offresRecyclerView.layoutManager = LinearLayoutManager(this)

        offreController = OffreController()

        offreController.getOffres().addOnSuccessListener { documents ->
            if (documents != null) {
                val offres = documents.toObjects(Offre::class.java)
                offreAdapter = OffreAdapter(offres)
                offresRecyclerView.adapter = offreAdapter
            }
        }


        /*
        val imgProfil = findViewById<ImageView>(R.id.logo);
        val offreLayout = findViewById<ConstraintLayout>(R.id.offre)
        val titre = findViewById<TextView>(R.id.titre)
        val societe = findViewById<TextView>(R.id.societe)
        val adresse = findViewById<TextView>(R.id.adresse)
        val description = findViewById<TextView>(R.id.description)
        val datePublic = findViewById<TextView>(R.id.date_public)

        offreController = OffreController()
        val id : String = "1"

        offreController.getOffre(id).addOnSuccessListener { document ->
            if (document != null) {
                titre.text = document.getString("titre")
                societe.text = document.getString("entreprise")
                adresse.text = document.getString("adresse")
                description.text = document.getString("description")

                val dateCreation = document.getTimestamp("dateCreation") ?: Timestamp.now()
                val daysSinceCreation = TimeUnit.MILLISECONDS.toDays(
                    Timestamp.now().toDate().time - dateCreation.toDate().time)

                datePublic.text = if (daysSinceCreation == 0L) {
                    "Offre publiée depuis moins d'1 jour"
                } else {
                    "Offre publiée il y a $daysSinceCreation jours"
                }
            }
        }

        imgProfil.setOnClickListener {
            val intent = Intent(this, CandidatDashboardActivity::class.java)
            startActivity(intent)
        }

        offreLayout.setOnClickListener {
            val intent = Intent(this, DetailsOffreActivity::class.java)
            startActivity(intent)
        }

         */
    }
}

