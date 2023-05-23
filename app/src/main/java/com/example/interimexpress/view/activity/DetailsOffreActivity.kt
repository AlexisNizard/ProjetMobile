package com.example.interimexpress.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.interimexpress.R
import com.example.interimexpress.controller.OffreController
import com.example.interimexpress.databinding.ActivityDetailsOffreBinding
import com.example.interimexpress.model.Offre
import com.google.firebase.firestore.FirebaseFirestore

class DetailsOffreActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailsOffreBinding
    private lateinit var offreController: OffreController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsOffreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logo.setOnClickListener {
            val intent = Intent(this, CandidatDashboardActivity::class.java)
            startActivity(intent)
        }

        offreController = OffreController()

        val id : String = intent.getStringExtra("offre_id") ?: "1" // Si aucun extra "offre_id" n'est trouvé, "1" sera utilisé par défaut.
        readData(id)


        binding.rechercherButton.setOnClickListener {
            val intent = Intent(this, PostulerOffreActivity::class.java)
            startActivity(intent)
        }


    }

    private fun readData(id: String) {
        offreController.getOffre(id).addOnSuccessListener { document ->
            if (document != null) {
                val titre = document.getString("titre")
                val entreprise = document.getString("entreprise")
                val adresse = document.getString("adresse")
                val description = document.getString("description")

                binding.titre.text = titre
                binding.titre2.text = entreprise
                binding.titre3.text = adresse
                binding.titre4.text = description

            } else {
                Toast.makeText(this,"Echec, la bdd ne reconnait pas l'id ",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener { e ->
            Toast.makeText(this,"Erreur avec la bdd",Toast.LENGTH_SHORT).show()
        }
    }
}
