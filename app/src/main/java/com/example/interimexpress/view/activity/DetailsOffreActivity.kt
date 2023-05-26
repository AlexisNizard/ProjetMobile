package com.example.interimexpress.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.example.interimexpress.R
import com.example.interimexpress.controller.OffreController
import com.example.interimexpress.databinding.ActivityDetailsOffreBinding
import com.example.interimexpress.model.Offre
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.FirebaseFirestore

class DetailsOffreActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailsOffreBinding
    private lateinit var offreController: OffreController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsOffreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("InterimExpress", Context.MODE_PRIVATE)
        val userRole = sharedPreferences.getString("userRole", "")
        //println(userRole)
        if (userRole == "Candidat") {

        } else {
            masquer_vue_anonyme();
        }


        binding.logo.setOnClickListener {
            val intent = Intent(this, CandidatDashboardActivity::class.java)
            startActivity(intent)
        }

        offreController = OffreController()

        val id : String = intent.getStringExtra("offre_id") ?: "1" // Si aucun extra "offre_id" n'est trouvé, "1" sera utilisé par défaut.
        readData(id)


        binding.rechercherButton.setOnClickListener {
            if (userRole == "Candidat") {
                val intent = Intent(this, PostulerOffreActivity::class.java)
                startActivity(intent)
            } else {
                faire_apparaitre_co_inscr();
            }
        }

        binding.rechercherButton1.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.rechercherButton2.setOnClickListener {
            val intent = Intent(this, CandidatRegisterActivity::class.java)
            startActivity(intent)
        }

        binding.rechercherOffreSimi.setOnClickListener{
            addRechAnnSimi()
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

    private fun addRechAnnSimi() {
        val chipGroupContainer: ConstraintLayout = findViewById(R.id.chipGroupContainer)
        val sendButton: Button = findViewById(R.id.sendButton)

        chipGroupContainer.visibility = View.VISIBLE
        sendButton.visibility = View.VISIBLE
    }

    private fun faire_apparaitre_co_inscr(){
        val veuillez_identif: TextView = findViewById(R.id.veuillez_identif)
        veuillez_identif.visibility = View.VISIBLE


        val rechercher_button1: Button = findViewById(R.id.rechercher_button1)
        rechercher_button1.visibility = View.VISIBLE

        val rechercher_button2: Button = findViewById(R.id.rechercher_button2)
        rechercher_button2.visibility = View.VISIBLE

        val rechercher_button: Button = findViewById(R.id.rechercher_button)
        rechercher_button.visibility = View.GONE


        val constraintLayout1: ConstraintLayout = findViewById(R.id.parent_constr)
        val constraintSet1 = ConstraintSet()
        constraintSet1.clone(constraintLayout1)

        constraintSet1.connect(R.id.veuillez_identif, ConstraintSet.TOP, R.id.titre4, ConstraintSet.BOTTOM)
        constraintSet1.applyTo(constraintLayout1)

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
