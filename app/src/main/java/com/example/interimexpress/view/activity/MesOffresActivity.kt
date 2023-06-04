package com.example.interimexpress.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.interimexpress.R
import com.example.interimexpress.adapter.MesOffresAdapter
import com.example.interimexpress.adapter.OffreAdapter
import com.example.interimexpress.controller.EmployeurController
import com.example.interimexpress.controller.OffreController
import com.example.interimexpress.controller.PostulerController
import com.example.interimexpress.model.Employeur
import com.example.interimexpress.model.Offre
import com.example.interimexpress.view.fragment.FooterCandidatFragment

class MesOffresActivity  : AppCompatActivity() {

    private lateinit var offreController: OffreController
    private lateinit var offreAdapter: OffreAdapter
    private lateinit var offresRecyclerView: RecyclerView
    private val postulerController = PostulerController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mes_offres)

        val sharedPreferences = getSharedPreferences("InterimExpress", Context.MODE_PRIVATE)
        val mail = sharedPreferences.getString("userMail", "")

        val employeurController = EmployeurController()

        val empTask = employeurController.getEmployeur(mail.toString())

        empTask.addOnSuccessListener { document ->
            if (document.exists()) {
                val employeur = document.toObject(Employeur::class.java)

                val empTask2 = postulerController.getUntreatedPostulerByEmployerId(employeur?.adresseMail.toString())
                empTask2.addOnSuccessListener { document ->
                    val footerFragment = supportFragmentManager.findFragmentById(R.id.footerFragment) as? FooterCandidatFragment
                    if (document.size()!=0) {
                        footerFragment?.setNotificationIconActive(true)
                    }
                    else{
                        footerFragment?.setNotificationIconActive(false)
                    }
                }
            }
        }


        val imgProfil = findViewById<ImageView>(R.id.logo);
        imgProfil.setOnClickListener {
            val intent = Intent(this, EmployeurDashboardActivity::class.java)
            startActivity(intent)
        }


        offresRecyclerView = findViewById(R.id.offres_recycler_view)
        offresRecyclerView.layoutManager = LinearLayoutManager(this)

        offreController = OffreController()

        offreAdapter = MesOffresAdapter(mutableListOf())
        offresRecyclerView.adapter = offreAdapter


        offreController.getOffresByOwner(mail.toString()).addOnSuccessListener { documents ->
            if (documents != null) {
                val offres = documents.toObjects(Offre::class.java)
                println(offres)
                offreAdapter.updateOffreList(offres)
            }
        }
    }


}