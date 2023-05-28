package com.example.interimexpress.view.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.interimexpress.R
import com.example.interimexpress.adapter.OffreAdapter
import com.example.interimexpress.controller.OffreController
import com.example.interimexpress.model.Offre

class MesOffresActivity  : AppCompatActivity() {

    private lateinit var offreController: OffreController
    private lateinit var offreAdapter: OffreAdapter
    private lateinit var offresRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mes_offres)

        val sharedPreferences = getSharedPreferences("InterimExpress", Context.MODE_PRIVATE)
        val mail = sharedPreferences.getString("userMail", "") // récupérer l'email de l'utilisateur

        offresRecyclerView = findViewById(R.id.offres_recycler_view)
        offresRecyclerView.layoutManager = LinearLayoutManager(this)

        offreController = OffreController()

        offreAdapter = OffreAdapter(emptyList())
        offresRecyclerView.adapter = offreAdapter

        offreController.getOffresByOwner(mail.toString()).addOnSuccessListener { documents ->
            if (documents != null) {
                val offres = documents.toObjects(Offre::class.java)
                offreAdapter.updateOffreList(offres)
            }
        }
    }
}