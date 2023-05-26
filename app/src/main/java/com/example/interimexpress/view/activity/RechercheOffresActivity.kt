package com.example.interimexpress.view.activity

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.interimexpress.R
import com.example.interimexpress.adapter.OffreAdapter
import com.example.interimexpress.controller.OffreController
import com.example.interimexpress.model.Offre
import com.google.android.material.slider.RangeSlider
import com.google.firebase.Timestamp
import java.util.*
import java.util.concurrent.TimeUnit

class RechercheOffresActivity : AppCompatActivity() {


    private lateinit var offreController: OffreController
    private lateinit var offreAdapter: OffreAdapter
    private lateinit var offresRecyclerView: RecyclerView

    private lateinit var debEditText: Button
    private lateinit var finEditText: Button
    private lateinit var settings_: ImageView

    private var click = 1

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

        debEditText = findViewById(R.id.deb)
        finEditText = findViewById(R.id.fin)

        debEditText.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this, { _, chosenYear, monthOfYear, dayOfMonth ->
                debEditText.setText("$dayOfMonth/$monthOfYear/$chosenYear")
            }, year, month, day).show()
        }

        finEditText.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this, { _, chosenYear, monthOfYear, dayOfMonth ->
                finEditText.setText("$dayOfMonth/$monthOfYear/$chosenYear")
            }, year, month, day).show()
        }
        val items = arrayOf("Tout type d'emploi","CDI", "CDD", "Stage", "Alternance")

        val adapter = object : ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                if (view is TextView) {
                    view.setTextColor(Color.BLACK) // Changer la couleur ici.
                }
                return view
            }

            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getDropDownView(position, convertView, parent)
                if (view is TextView) {
                    view.setTextColor(Color.BLACK) // Changer la couleur ici.
                }
                return view
            }
        }

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item2)

        val spinner: Spinner = findViewById(R.id.type_dropdown)
        spinner.adapter = adapter

        settings_ = findViewById(R.id.settings_)
        var click = 1
        settings_.setOnClickListener{
            recherche_avancee()
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

    private fun recherche_avancee() {
        val register_container3: ConstraintLayout = findViewById(R.id.register_container3)
        val register_container4: ConstraintLayout = findViewById(R.id.register_container4)
        val register_container5: ConstraintLayout = findViewById(R.id.register_container5)

        if (click==1){
            register_container3.visibility = View.VISIBLE
            register_container4.visibility = View.VISIBLE
            register_container5.visibility = View.VISIBLE
            click=0
        }else{
            register_container3.visibility = View.GONE
            register_container4.visibility = View.GONE
            register_container5.visibility = View.GONE
            click=1
        }

    }
}

