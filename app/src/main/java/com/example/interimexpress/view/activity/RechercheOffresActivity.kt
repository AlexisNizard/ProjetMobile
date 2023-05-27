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
import com.example.interimexpress.adapter.GeocodingUtils
import com.example.interimexpress.adapter.OffreAdapter
import com.example.interimexpress.controller.OffreController
import com.example.interimexpress.model.Offre
import com.google.android.material.slider.RangeSlider
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class RechercheOffresActivity : AppCompatActivity() {


    private lateinit var offreController: OffreController
    private lateinit var offreAdapter: OffreAdapter
    private lateinit var offresRecyclerView: RecyclerView

    private lateinit var debEditText: Button
    private lateinit var finEditText: Button
    private lateinit var settings_: ImageView
    private lateinit var rechercher_button : Button

    private var bool_c = 1

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

        val (latitude, longitude) = MainActivity.getLocationFromSharedPrefs(this)
        println("Latitude: $latitude, Longitude: $longitude")


        val imgProfil = findViewById<ImageView>(R.id.logo);

        imgProfil.setOnClickListener {
            val intent = Intent(this, CandidatDashboardActivity::class.java)
            startActivity(intent)
        }



        offresRecyclerView = findViewById(R.id.offres_recycler_view)
        offresRecyclerView.layoutManager = LinearLayoutManager(this)

        offreController = OffreController()

        offreAdapter = OffreAdapter(emptyList())
        offresRecyclerView.adapter = offreAdapter

        val location = MainActivity.getLocationFromSharedPrefs(this)
        val geocodingUtils = GeocodingUtils(this)

        val cityName = geocodingUtils.getCityName(location.first, location.second)
        if (cityName != null) {
            offreController.getOffresByCity(cityName).addOnSuccessListener { documents ->
                if (documents != null) {
                    println(cityName)
                    val offres = documents.toObjects(Offre::class.java)
                    println(offres.size)
                    offreAdapter.updateOffreList(offres)
                }
            }
        } else {
            println("on entre dans le else")
            offreController.getTop10Offres().addOnSuccessListener { offres ->
                if (offres != null) {
                    println(offres.size)
                    offreAdapter.updateOffreList(offres)
                }
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


        rechercher_button = findViewById(R.id.rechercher_button)
        rechercher_button.setOnClickListener {
                searchOffres()
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

        if (bool_c==1){
            register_container3.visibility = View.VISIBLE
            register_container4.visibility = View.VISIBLE
            register_container5.visibility = View.VISIBLE
            bool_c=0
        }else{
            register_container3.visibility = View.GONE
            register_container4.visibility = View.GONE
            register_container5.visibility = View.GONE
            bool_c=1
        }

    }

    private fun searchOffres() {
        // Obtenir les valeurs de tous les champs de saisie
        val quoi = findViewById<EditText>(R.id.register_button).text.toString()
        val ou = findViewById<EditText>(R.id.register_button2).text.toString()
        val typeContrat = findViewById<Spinner>(R.id.type_dropdown).selectedItem.toString()
        val deb = findViewById<Button>(R.id.deb).text.toString()
        val fin = findViewById<Button>(R.id.fin).text.toString()
        val minSalary = findViewById<EditText>(R.id.min_salary).text.toString().toDoubleOrNull()
        val maxSalary = findViewById<EditText>(R.id.max_salary).text.toString().toDoubleOrNull()

        offreController.getAllOffres().addOnSuccessListener { documents ->
            if (documents != null) {
                val allOffres = documents.toObjects(Offre::class.java)

                val filteredOffres = allOffres.filter { offre ->
                    val isQuoiValid = quoi.isEmpty() || offre.titre?.contains(quoi, ignoreCase = true) == true || offre.entreprise?.contains(quoi, ignoreCase = true) == true
                    val isOuValid = ou.isEmpty() || offre.adresse?.contains(ou, ignoreCase = true) == true || offre.cp?.contains(ou, ignoreCase = true) == true
                    val isTypeValid = typeContrat == "Tout type d'emploi" || offre.typeContrat?.toUpperCase() == typeContrat.toUpperCase()
                    val isDebValid = deb == "Début" || offre.dateDebut?.toDate()?.after(stringToDate(deb)) == true
                    val isFinValid = fin == "Fin" || offre.dateFin?.toDate()?.before(stringToDate(fin)) == true
                    val isMinSalaryValid = minSalary == null || offre.remuneration?.compareTo(minSalary) ?: -1 >= 0
                    val isMaxSalaryValid = maxSalary == null || offre.remuneration?.compareTo(maxSalary) ?: 1 <= 0


                    isQuoiValid && isOuValid && isTypeValid && isDebValid && isFinValid && isMinSalaryValid && isMaxSalaryValid
                }

                offreAdapter.updateOffreList(filteredOffres)
            }
        }
    }

    private fun stringToDate(dateString: String): Date? {
        if (dateString == "Début" || dateString == "Fin") {
            return null
        }
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE)
        return format.parse(dateString)
    }
}

