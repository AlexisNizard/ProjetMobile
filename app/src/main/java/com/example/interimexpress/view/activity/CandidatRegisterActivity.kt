package com.example.interimexpress.view.activity


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.interimexpress.R
import com.example.interimexpress.adapter.NationalitySpinnerAdapter
import com.example.interimexpress.controller.CandidatController
import com.example.interimexpress.model.Candidat
import com.hbb20.CountryCodePicker
import java.util.*
class CandidatRegisterActivity : AppCompatActivity() {

    private lateinit var ccp: CountryCodePicker
    private lateinit var editTextPhoneNumber: EditText
    private lateinit var nationalitySpinner: Spinner

    private lateinit var candidatController: CandidatController
    private lateinit var nomEditText: EditText
    private lateinit var prenomEditText: EditText
    private lateinit var adresseMailEditText: EditText
    private lateinit var motDePasseEditText: EditText

    private lateinit var editTextDate: EditText



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidat_register)

        ccp = findViewById(R.id.countryCodePicker)
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber)
        nationalitySpinner = findViewById(R.id.spinnerNationality)
        editTextDate = findViewById(R.id.editTextDate)


        setupSpinner()
        setupCountryCodePicker()
        setupDateEditText()

        nomEditText = findViewById(R.id.editTextNom)
        prenomEditText = findViewById(R.id.editTextPrenom)
        adresseMailEditText = findViewById(R.id.editTextMail)
        motDePasseEditText = findViewById(R.id.editTextPassword)
        nationalitySpinner = findViewById(R.id.spinnerNationality)
        ccp = findViewById(R.id.countryCodePicker)
        candidatController = CandidatController()

        val registerButton = findViewById<Button>(R.id.inscrire_button)
        registerButton.setOnClickListener {
            registerCandidat()
        }




    }

    private fun setupSpinner() {
        val nationalities = resources.getStringArray(R.array.nationalities)
        val adapter = NationalitySpinnerAdapter(this, nationalities)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        nationalitySpinner.adapter = adapter
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


    private fun registerCandidat() {
        val nom = nomEditText.text.toString()
        val prenom = prenomEditText.text.toString()
        val adresseMail = adresseMailEditText.text.toString()
        val motDePasse = motDePasseEditText.text.toString()
        val nationalite = nationalitySpinner.selectedItem.toString()
        val numTelephone = ccp.fullNumberWithPlus
        val dateNaissance = editTextDate.text.toString()


        val candidat = Candidat(
            adresseMail = adresseMail, // Ici, nous utilisons l'adresse e-mail comme ID car elle est unique pour chaque utilisateur.
            nom = nom,
            prenom = prenom,
            motDePasse = motDePasse,
            role = "Candidat", // Pour cette activité, nous fixons le rôle à "Candidat".
            nationalite = nationalite,
            numTelephone = numTelephone,
            dateNaissance = dateNaissance
        )

        candidatController.insertCandidat(candidat)

        val intent = Intent(this, ConfirmationInscriptionActivity::class.java)
        startActivity(intent)
    }

    private fun setupDateEditText() {
        editTextDate.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val str = s.toString()
                if ((str.length == 2 || str.length == 5) && count != 0 && !str.endsWith("/")) {
                    editTextDate.setText("$str/")
                    editTextDate.setSelection(str.length + 1)
                }

                if (str.length == 10 && !isValidDate(str)) {
                    editTextDate.error = "Date invalide"
                }
            }
        })
    }

    private fun isValidDate(date: String): Boolean {
        val parts = date.split("/")
        if (parts.size != 3) {
            return false
        }

        val day = parts[0].toIntOrNull() ?: return false
        val month = parts[1].toIntOrNull() ?: return false
        val year = parts[2].toIntOrNull() ?: return false

        if (day !in 1..31 || month !in 1..12 || year < 1900 || year > Calendar.getInstance().get(Calendar.YEAR)) {
            return false
        }

        val calendar = Calendar.getInstance()
        calendar.isLenient = false
        calendar.set(year, month - 1, day)
        return try {
            calendar.time
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }





}
