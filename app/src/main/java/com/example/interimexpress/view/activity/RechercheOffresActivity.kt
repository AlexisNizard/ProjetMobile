package com.example.interimexpress.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
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

        val sharedPreferences = getSharedPreferences("InterimExpress", Context.MODE_PRIVATE)
        val userRole = sharedPreferences.getString("userRole", "")
        //println(userRole)
        if (userRole == "Candidat") {

        } else {
            masquer_vue_anonyme();
        }


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


    }

    private fun masquer_vue_anonyme(){
        val logo: ImageView = findViewById(R.id.logo)
        logo.visibility = View.GONE
        val imageView: ImageView = findViewById(R.id.imageView)
        imageView.visibility = View.GONE

        // Get an instance of the fragment
        val footerFragment = supportFragmentManager.findFragmentById(R.id.footerFragment)

        // Hide the fragment
        if (footerFragment != null) {
            supportFragmentManager.beginTransaction().hide(footerFragment).commit()
        }

    }
}

