package com.example.interimexpress.view.activity


import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.interimexpress.R
import com.example.interimexpress.adapter.NationalitySpinnerAdapter
import com.example.interimexpress.controller.UtilisateurController
import com.example.interimexpress.model.Utilisateur
import com.hbb20.CountryCodePicker
import java.util.*
class CandidatRegisterActivity : AppCompatActivity() {

    private lateinit var ccp: CountryCodePicker
    private lateinit var editTextPhoneNumber: EditText
    private lateinit var nationalitySpinner: Spinner

    private lateinit var utilisateurController: UtilisateurController
    private lateinit var nomEditText: EditText
    private lateinit var prenomEditText: EditText
    private lateinit var adresseMailEditText: EditText
    private lateinit var motDePasseEditText: EditText

    private lateinit var daySpinner: AutoCompleteTextView
    private lateinit var monthSpinner: AutoCompleteTextView
    private lateinit var yearSpinner: AutoCompleteTextView


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidat_register)

        ccp = findViewById(R.id.countryCodePicker)
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber)
        nationalitySpinner = findViewById(R.id.spinnerNationality)

        daySpinner = findViewById(R.id.daySpinner)
        monthSpinner = findViewById(R.id.monthSpinner)
        yearSpinner = findViewById(R.id.yearSpinner)


        setupSpinner()
        setupCountryCodePicker()
        setupSpinners()

        nomEditText = findViewById(R.id.editTextNom)
        prenomEditText = findViewById(R.id.editTextPrenom)
        adresseMailEditText = findViewById(R.id.editTextMail)
        motDePasseEditText = findViewById(R.id.editTextPassword)
        nationalitySpinner = findViewById(R.id.spinnerNationality)
        ccp = findViewById(R.id.countryCodePicker)
        utilisateurController = UtilisateurController()

        val registerButton = findViewById<Button>(R.id.inscrire_button)
        registerButton.setOnClickListener {
            registerUser()
        }




    }

    private fun setupSpinner() {
        val nationalities = resources.getStringArray(R.array.nationalities)
        val adapter = NationalitySpinnerAdapter(this, nationalities)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        nationalitySpinner.adapter = adapter
    }

    private fun setupSpinners() {
        val days = (1..31).map { it.toString() }.toTypedArray()
        val months = (1..12).map { it.toString() }.toTypedArray()
        val years = (1900..Calendar.getInstance().get(Calendar.YEAR)).map { it.toString() }.reversed().toTypedArray()

        val dayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, days)
        daySpinner.setAdapter(dayAdapter)

        val monthAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, months)
        monthSpinner.setAdapter(monthAdapter)

        val yearAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, years)
        yearSpinner.setAdapter(yearAdapter)
    }


    private fun setupCountryCodePicker() {
        ccp.setCustomDialogTextProvider(object : CountryCodePicker.CustomDialogTextProvider {
            override fun getCCPDialogTitle(language: CountryCodePicker.Language, defaultTitle: String): String {
                return when (language) {
                    CountryCodePicker.Language.FRENCH -> "Séléctionner un pays"
                    else -> defaultTitle
                }
            }

            override fun getCCPDialogSearchHintText(language: CountryCodePicker.Language, defaultSearchHintText: String): String {
                return when (language) {
                    CountryCodePicker.Language.FRENCH -> "Rechercher"
                    else -> defaultSearchHintText
                }
            }

            override fun getCCPDialogNoResultACK(language: CountryCodePicker.Language, defaultNoResultACK: String): String {
                return defaultNoResultACK
            }
        })

        ccp.registerCarrierNumberEditText(editTextPhoneNumber)
    }


    private fun registerUser() {
        val nom = nomEditText.text.toString()
        val prenom = prenomEditText.text.toString()
        val adresseMail = adresseMailEditText.text.toString()
        val motDePasse = motDePasseEditText.text.toString()
        val nationalite = nationalitySpinner.selectedItem.toString()
        val numTelephone = ccp.fullNumberWithPlus
        val dateNaissance = "${daySpinner.text}-${monthSpinner.text}-${yearSpinner.text}"

        val utilisateur = Utilisateur(
            id = adresseMail, // Ici, nous utilisons l'adresse e-mail comme ID car elle est unique pour chaque utilisateur.
            nom = nom,
            prenom = prenom,
            adresseMail = adresseMail,
            motDePasse = motDePasse,
            role = "Candidat", // Pour cette activité, nous fixons le rôle à "Candidat".
            nationalite = nationalite,
            numTelephone = numTelephone,
            dateNaissance = dateNaissance
        )

        utilisateurController.insertUtilisateur(utilisateur)

        val intent = Intent(this, ConfirmationInscriptionActivity::class.java)
        startActivity(intent)
    }



}
