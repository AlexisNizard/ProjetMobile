package com.example.interimexpress.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
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
    }
}