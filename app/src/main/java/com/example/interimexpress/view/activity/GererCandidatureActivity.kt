package com.example.interimexpress.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.interimexpress.R
import com.example.interimexpress.adapter.CandidaturesRecuesAdapter
import com.example.interimexpress.adapter.MesOffresAdapter
import com.example.interimexpress.adapter.OffreAdapter
import com.example.interimexpress.controller.OffreController
import com.example.interimexpress.controller.PostulerController
import com.example.interimexpress.model.Offre
import com.example.interimexpress.model.Postuler

class GererCandidatureActivity : AppCompatActivity() {

    private lateinit var offreController: OffreController
    private lateinit var offreAdapter: OffreAdapter
    private lateinit var offresRecyclerView: RecyclerView
    private lateinit var postulerController: PostulerController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gerer_candidature)

        val sharedPreferences = getSharedPreferences("InterimExpress", Context.MODE_PRIVATE)
        val mail = sharedPreferences.getString("userMail", "") // récupérer l'email de l'utilisateur

        val imgProfil = findViewById<ImageView>(R.id.logo);
        imgProfil.setOnClickListener {
            val intent = Intent(this, EmployeurDashboardActivity::class.java)
            startActivity(intent)
        }

        offreController = OffreController()
        offreAdapter = CandidaturesRecuesAdapter(mutableListOf())

        postulerController = PostulerController()
        loadPostulersByEmployer(mail.toString())

        offresRecyclerView = findViewById(R.id.offres_recycler_view)
        offresRecyclerView.layoutManager = LinearLayoutManager(this)
        offresRecyclerView.adapter = offreAdapter


        /*offresRecyclerView = findViewById(R.id.offres_recycler_view)
        offresRecyclerView.layoutManager = LinearLayoutManager(this)

        offreController = OffreController()

        offreAdapter = CandidaturesRecuesAdapter(mutableListOf())
        offresRecyclerView.adapter = offreAdapter


        offreController.getOffresByOwner(mail.toString()).addOnSuccessListener { documents ->
            if (documents != null) {
                val offres = documents.toObjects(Offre::class.java)
                println(offres)
                offreAdapter.updateOffreList(offres)
            }
        }*/

    }

    private fun loadPostulersByEmployer(employerId: String) {
        postulerController.getPostulersByEmployer(employerId, 0).addOnSuccessListener { documents ->
            if (documents != null) {
                val postulers = documents.toObjects(Postuler::class.java)
                for (postuler in postulers) {
                    loadAndAddOffer(postuler.offerId!!, postuler.id!!)
                }
            }
        }
    }

    private fun loadAndAddOffer(offerId: String, postulerId: String) {
        offreController.getOffre(offerId).addOnSuccessListener { documentSnapshot ->
            val offre = documentSnapshot.toObject(Offre::class.java)
            offre?.let {
                it.id = postulerId  // set postulerId to be used later
                val newOffreList = offreAdapter.offreList.toMutableList()
                newOffreList.add(it)
                offreAdapter.updateOffreList(newOffreList)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val fragment = supportFragmentManager.findFragmentById(R.id.footerFragment)
        if (fragment != null && fragment.isAdded()) {
            EmployeurDashboardActivity.Utility.updateNotificationIcon(this, fragment)
        }
    }



}