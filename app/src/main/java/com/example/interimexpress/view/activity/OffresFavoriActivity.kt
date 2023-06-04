package com.example.interimexpress.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.interimexpress.R
import com.example.interimexpress.adapter.OffreAdapter
import com.example.interimexpress.controller.CandidatController
import com.example.interimexpress.controller.FavoriController
import com.example.interimexpress.controller.OffreController
import com.example.interimexpress.controller.PostulerController
import com.example.interimexpress.model.Candidat
import com.example.interimexpress.model.Favori
import com.example.interimexpress.model.Offre
import com.example.interimexpress.view.fragment.FooterCandidatFragment

class OffresFavoriActivity : AppCompatActivity() {
    lateinit var favoriController: FavoriController
    lateinit var offreController: OffreController
    lateinit var offresRecyclerView: RecyclerView
    lateinit var offreAdapter: OffreAdapter
    private val postulerController = PostulerController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offres_favori)


        val sharedPreferences = getSharedPreferences("InterimExpress", Context.MODE_PRIVATE)
        val userRole = sharedPreferences.getString("userRole", "")
        val mail = sharedPreferences.getString("userMail", "")

        val candidatController = CandidatController()

        val candidatTask = candidatController.getCandidat(mail.toString())


        candidatTask.addOnSuccessListener { document ->
            if (document.exists()) {
                val candidat = document.toObject(Candidat::class.java)

                val empTask2 = postulerController.getUntreatedPostulerByCandidatId(candidat?.adresseMail.toString())
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
            val intent = Intent(this, CandidatDashboardActivity::class.java)
            startActivity(intent)
        }

        favoriController = FavoriController()
        offreController = OffreController()

        offresRecyclerView = findViewById(R.id.offres_recycler_view)
        offresRecyclerView.layoutManager = LinearLayoutManager(this)

        offreAdapter = OffreAdapter(mutableListOf())
        offresRecyclerView.adapter = offreAdapter


        if (mail != null) {
            favoriController.getFavorisForUser(mail).addOnSuccessListener { documents ->
                val favoris = documents.toObjects(Favori::class.java)
                val offres = mutableListOf<Offre>()
                println("favories : $favoris")
                println("offres  : $offres")
                favoris.forEach { favori ->
                    offreController.getOffre(favori.idOffre!!).addOnSuccessListener { offre ->
                        offre.toObject(Offre::class.java)?.let { offres.add(it) }
                        offreAdapter.updateOffreList(offres)
                    }
                }
            }
        }
    }
}
