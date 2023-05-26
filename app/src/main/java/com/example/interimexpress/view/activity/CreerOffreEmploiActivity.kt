package com.example.interimexpress.view.activity

import android.app.DatePickerDialog
import android.content.ContentValues.TAG
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.interimexpress.R
import com.example.interimexpress.controller.CandidatController
import com.example.interimexpress.controller.OffreController
import com.example.interimexpress.model.Offre
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

class CreerOffreEmploiActivity : AppCompatActivity() {

    private lateinit var offreController: OffreController
    private lateinit var titreOffreEditText: EditText
    private lateinit var nomEntrepriseEditText: EditText
    private lateinit var codePostalEditText: EditText
    private lateinit var villeAutoComplete: AutoCompleteTextView
    private lateinit var descriptionEditText: EditText
    private lateinit var creerOffreButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creer_offre_emploi)

        offreController = OffreController()

        titreOffreEditText = findViewById(R.id.editTextTitreOffre)
        nomEntrepriseEditText = findViewById(R.id.editTextNomEntreprise)
        codePostalEditText = findViewById(R.id.editTextCodePostal)
        villeAutoComplete = findViewById(R.id.autoCompleteVille)
        descriptionEditText = findViewById(R.id.editTextDescription)

        creerOffreButton = findViewById(R.id.btn_creationOffre)

        codePostalEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.length == 5) {
                    val retrofit = Retrofit.Builder()
                        .baseUrl("https://geo.api.gouv.fr/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

                    val service = retrofit.create(GeoApiService::class.java)

                    service.getCommunes(s.toString()).enqueue(object : Callback<List<Commune>> {
                        override fun onResponse(call: Call<List<Commune>>, response: Response<List<Commune>>) {
                            if (response.isSuccessful) {
                                val communes = response.body()
                                val adapter = ArrayAdapter<String>(
                                    this@CreerOffreEmploiActivity,
                                    android.R.layout.simple_dropdown_item_1line,
                                    communes?.map { it.nom } ?: emptyList()
                                )
                                villeAutoComplete.setAdapter(adapter)
                            } else {
                                Log.e(TAG, "API error: ${response.errorBody()}")
                            }
                        }

                        override fun onFailure(call: Call<List<Commune>>, t: Throwable) {
                            Log.e(TAG, "Network error: $t")
                        }
                    })
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })

        //Pour TypeContrat
        val spinner: Spinner = findViewById(R.id.spinnerTypeContrat)

        ArrayAdapter.createFromResource(
            this,
            R.array.contract_types,
            R.layout.spinner_remuneration
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        //Pour rémunération
        val editText: EditText = findViewById(R.id.editTextRemuneration)
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString()
                        .toIntOrNull() ?: 0 > 50000
                ) { // Si la valeur est supérieure à 50000
                    editText.error =
                        "Valeur supérieure à 50000 n'est pas autorisée"
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        //Pour DateDebut
        val editTextStartDate: EditText = findViewById(R.id.editTextDateDebut)
        editTextStartDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, { _, year, monthOfYear, dayOfMonth ->
                val selectedDate = String.format("%02d/%02d/%04d", dayOfMonth, monthOfYear + 1, year)
                editTextStartDate.setText(selectedDate)
            }, year, month, day)

            dpd.show()
        }

        //Pour DateFin
        val editTextEndDate: EditText = findViewById(R.id.editTextDateFin)
        editTextEndDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, { _, year, monthOfYear, dayOfMonth ->
                val selectedDate = String.format("%02d/%02d/%04d", dayOfMonth, monthOfYear + 1, year)
                editTextEndDate.setText(selectedDate)
            }, year, month, day)

            dpd.show()
        }

        creerOffreButton.setOnClickListener {
            Log.d("CreerOffreEmploiActivity", "Creer Offre button clicked.")
            registerOffre()
        }
    }

    private fun registerOffre(){
        val titre = titreOffreEditText.text.toString()
        val entreprise = nomEntrepriseEditText.text.toString()
        val adresse = "${codePostalEditText.text.toString()} ${villeAutoComplete.text.toString()}"
        val description = descriptionEditText.text.toString()

        val offre = Offre(
            id = null, // L'id sera généré automatiquement par Firestore
            titre = titre,
            entreprise = entreprise,
            adresse = adresse,
            description = description,
            dateCreation = null // Vous pouvez mettre à jour cette valeur si nécessaire
        )

        offreController.insertOffre(offre)
    }
}

data class Commune(
    val nom: String,
    val code: String,
    val codesPostaux: List<String>,
)

interface GeoApiService {
    @GET("/communes")
    fun getCommunes(@Query("codePostal") codePostal: String): Call<List<Commune>>
}
