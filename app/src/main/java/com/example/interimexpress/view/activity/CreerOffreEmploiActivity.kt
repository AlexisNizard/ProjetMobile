package com.example.interimexpress.view.activity

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.ContentValues.TAG
import android.content.Context
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
import com.example.interimexpress.controller.EmployeurController
import com.example.interimexpress.controller.OffreController
import com.example.interimexpress.model.Candidat
import com.example.interimexpress.model.Employeur
import com.example.interimexpress.model.Offre
import com.google.firebase.Timestamp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*

class CreerOffreEmploiActivity : AppCompatActivity() {

    private lateinit var offreController: OffreController
    private lateinit var titreOffreEditText: EditText
    //private lateinit var nomEntrepriseEditText: EditText
    //private lateinit var codePostalEditText: EditText
    //private lateinit var villeAutoComplete: AutoCompleteTextView
    private lateinit var contratSpinner: Spinner
    private lateinit var remunerationEditText: EditText
    private lateinit var debEditText: Button
    private lateinit var finEditText: Button
    private lateinit var descriptionEditText: EditText
    private lateinit var creerOffreButton: Button
    private lateinit var editTextlienOffre: EditText


    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creer_offre_emploi)


        offreController = OffreController()

        titreOffreEditText = findViewById(R.id.editTextTitreOffre)
        //nomEntrepriseEditText = findViewById(R.id.editTextNomEntreprise)
        //codePostalEditText = findViewById(R.id.editTextCodePostal)
        //villeAutoComplete = findViewById(R.id.autoCompleteVille)
        descriptionEditText = findViewById(R.id.editTextDescription)
        contratSpinner = findViewById(R.id.type_dropdown)
        remunerationEditText = findViewById(R.id.editTextRemuneration)
        debEditText = findViewById(R.id.deb)
        finEditText = findViewById(R.id.fin)
        editTextlienOffre = findViewById(R.id.editTextlienOffre)

        creerOffreButton = findViewById(R.id.btn_creationOffre)

        /*codePostalEditText.addTextChangedListener(object : TextWatcher {
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
        })*/

        //Pour TypeContrat*
        ArrayAdapter.createFromResource(
            this,
            R.array.contract_types,
            R.layout.spinner_remuneration
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_item2)
            contratSpinner.adapter = adapter
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



        debEditText = findViewById(R.id.deb)
        finEditText = findViewById(R.id.fin)

        debEditText.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this, R.style.DatePickerDialogTheme, { _, chosenYear, monthOfYear, dayOfMonth ->
                debEditText.setText("$dayOfMonth/$monthOfYear/$chosenYear")
            }, year, month, day).show()

        }

        finEditText.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this, R.style.DatePickerDialogTheme, { _, chosenYear, monthOfYear, dayOfMonth ->
                finEditText.setText("$dayOfMonth/$monthOfYear/$chosenYear")
            }, year, month, day).show()

        }

        creerOffreButton.setOnClickListener {
            Log.d("CreerOffreEmploiActivity", "Creer Offre button clicked.")
            registerOffre()
            finish()
        }
    }

    private fun registerOffre(){
        val titre = titreOffreEditText.text.toString()
        /*val entreprise = nomEntrepriseEditText.text.toString()
        val adresse = codePostalEditText.text.toString()
        val cp = villeAutoComplete.text.toString()*/
        val description = descriptionEditText.text.toString()
        val contrat = contratSpinner.selectedItem.toString()
        val remunerationString = remunerationEditText.text.toString()
        val remuneration = if (remunerationString.isNullOrBlank()) null else remunerationString.toDouble()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val dateDebutString = debEditText.text.toString()
        val dateDebut = if (dateDebutString.isNullOrBlank()) null else Timestamp(dateFormat.parse(dateDebutString))
        val dateFinString = finEditText.text.toString()
        val dateFin = if (dateFinString.isNullOrBlank()) null else Timestamp(dateFormat.parse(dateFinString))

        val editTextlienOffre = if (editTextlienOffre.text.toString()=="") null else editTextlienOffre.text.toString()


        val sharedPreferences = getSharedPreferences("InterimExpress", Context.MODE_PRIVATE)
        val mail = sharedPreferences.getString("userMail", "") // récupérer l'email de l'utilisateur

        // Obtenez une instance du CandidatController
        val employeurController = EmployeurController()

        // Récupérez le document pour cet utilisateur
        val empTask = employeurController.getEmployeur(mail.toString())

        // Si la requête est réussie, utilisez les données pour remplir les champs de texte
        empTask.addOnSuccessListener { document ->
            if (document.exists()) {
                val emp = document.toObject(Employeur::class.java)

                val offre = Offre(
                    id = null, // L'id sera généré automatiquement par Firestore
                    titre = titre,
                    entreprise = emp?.nomEntreprise,
                    adresse = emp?.ville,
                    cp=emp?.codePostal,
                    mail = emp?.adresseMail,
                    typeContrat = contrat,
                    remuneration = remuneration,
                    dateDebut = dateDebut,
                    dateFin = dateFin,
                    description = description,
                    dateCreation = Timestamp(Date()),
                    nbrClick = 0,
                    lienOffre = editTextlienOffre
                )

                offreController.insertOffre(offre)

            }
        }

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
