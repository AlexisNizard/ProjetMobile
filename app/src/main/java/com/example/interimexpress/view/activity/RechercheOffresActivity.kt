package com.example.interimexpress.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.interimexpress.R

class RechercheOffresActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recherche_offres)

        val imgProfil = findViewById<ImageView>(R.id.logo);

        imgProfil.setOnClickListener {
            val intent = Intent(this, CandidatDashboardActivity::class.java)
            startActivity(intent)
        }

        val offreLayout = findViewById<ConstraintLayout>(R.id.offre)

        offreLayout.setOnClickListener {
            val intent = Intent(this, DetailsOffreActivity::class.java)
            startActivity(intent)
        }
    }
}