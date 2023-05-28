package com.example.interimexpress.view.activity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.alimuzaffar.lib.pin.PinEntryEditText
import com.example.interimexpress.R
import com.example.interimexpress.adapter.SiretEditText
import com.example.interimexpress.controller.EmployeurController
import com.example.interimexpress.model.Employeur
import com.example.interimexpress.test.EmployeurRegisterActivityTest
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.hbb20.CountryCodePicker
import java.util.*
import kotlin.random.Random

class EmployeurRegisterActivity : AppCompatActivity() {

    //POUR LE TESTING
    private val testHelper = EmployeurRegisterActivityTest()


    private lateinit var employeurController: EmployeurController
    private lateinit var editTextNomEntreprise: EditText
    private lateinit var editTextNomService: EditText
    private lateinit var editTextNomSousService: EditText
    private lateinit var editTextNumNational: EditText
    private lateinit var editTextNom: EditText
    private lateinit var editTextPrenom: EditText
    private lateinit var editTextNom2: EditText
    private lateinit var editTextPrenom2: EditText
    private lateinit var editTextMail1: EditText
    private lateinit var editTextMail2: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextPassword_confirmation: EditText
    private lateinit var editTextPhoneNumber1: EditText
    private lateinit var editTextPhoneNumber2: EditText
    private lateinit var editTextAdresse: EditText
    private lateinit var editTextCodePostal: EditText
    private lateinit var editTextVille: EditText
    private lateinit var editTextSiteWeb: EditText
    private lateinit var editTextLinkedIn: EditText
    private lateinit var editTextFacebook: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employeur_register)

        employeurController = EmployeurController()

        editTextNomEntreprise = findViewById(R.id.editTextNomEntreprise)
        editTextNomService = findViewById(R.id.editTextNomService)
        editTextNomSousService = findViewById(R.id.editTextNomSousService)
        editTextNumNational = findViewById(R.id.editTextNumNational)
        editTextNom = findViewById(R.id.editTextNom)
        editTextPrenom = findViewById(R.id.editTextPrenom)
        editTextNom2 = findViewById(R.id.editTextNom2)
        editTextPrenom2 = findViewById(R.id.editTextPrenom2)
        editTextMail1 = findViewById(R.id.editTextMail1)
        editTextMail2 = findViewById(R.id.editTextMail2)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextPassword_confirmation = findViewById(R.id.editTextPassword_confirmation)
        editTextPhoneNumber1 = findViewById(R.id.editTextPhoneNumber1)
        editTextPhoneNumber2 = findViewById(R.id.editTextPhoneNumber2)
        editTextAdresse = findViewById(R.id.editTextAdresse)
        editTextCodePostal = findViewById(R.id.editTextCodePostal)
        editTextVille = findViewById(R.id.editTextVille)
        editTextSiteWeb = findViewById(R.id.editTextSiteWeb)
        editTextLinkedIn = findViewById(R.id.editTextLinkedIn)
        editTextFacebook = findViewById(R.id.editTextFacebook)

        val registerButton = findViewById<Button>(R.id.register_button)
        registerButton.setOnClickListener {
            registerEmployeur()
        }

        //TESTING
        val AUTOREMPLISSAGE = findViewById<Button>(R.id.AUTOREMPLISSAGE)
        AUTOREMPLISSAGE.setOnClickListener {
            findViewById<EditText>(R.id.editTextNomEntreprise).setText(testHelper.randomFromList(testHelper.noms))
            findViewById<EditText>(R.id.editTextNomService).setText(testHelper.randomFromList(testHelper.services))
            findViewById<EditText>(R.id.editTextNomSousService).setText(testHelper.randomFromList(testHelper.sousServices))
            findViewById<EditText>(R.id.editTextNumNational).setText(testHelper.randomNumber(14))
            findViewById<EditText>(R.id.editTextNom).setText(testHelper.randomFromList(testHelper.noms))
            findViewById<EditText>(R.id.editTextPrenom).setText(testHelper.randomFromList(testHelper.prenoms))
            findViewById<EditText>(R.id.editTextNom2).setText(testHelper.randomFromList(testHelper.noms))
            findViewById<EditText>(R.id.editTextPrenom2).setText(testHelper.randomFromList(testHelper.prenoms))

            val email1 = testHelper.randomEmail()
            findViewById<EditText>(R.id.editTextMail1).setText(email1)
            findViewById<EditText>(R.id.editTextMail2).setText(testHelper.randomEmail())

            val password = testHelper.randomPassword()
            findViewById<EditText>(R.id.editTextPassword).setText(password)
            findViewById<EditText>(R.id.editTextPassword_confirmation).setText(password)

            findViewById<EditText>(R.id.editTextPhoneNumber1).setText(testHelper.randomNumber(10))
            findViewById<EditText>(R.id.editTextPhoneNumber2).setText(testHelper.randomNumber(10))

            findViewById<EditText>(R.id.editTextAdresse).setText("123 rue de ${testHelper.randomFromList(testHelper.noms)}")
            findViewById<EditText>(R.id.editTextCodePostal).setText(testHelper.randomNumber(5))
            findViewById<EditText>(R.id.editTextVille).setText(testHelper.randomFromList(testHelper.villes))
            findViewById<EditText>(R.id.editTextSiteWeb).setText("www.${testHelper.randomFromList(testHelper.noms)}.com")
            findViewById<EditText>(R.id.editTextLinkedIn).setText("www.linkedin.com/in/${testHelper.randomFromList(testHelper.prenoms)}${testHelper.randomFromList(testHelper.noms)}")
            findViewById<EditText>(R.id.editTextFacebook).setText("www.facebook.com/${testHelper.randomFromList(testHelper.prenoms)}${testHelper.randomFromList(testHelper.noms)}")
        }
        //FIN TESTING


        val addContactButton: Button = findViewById(R.id.addContactButton)
        addContactButton.setOnClickListener {
            addSecondContact()
        }

        val addMailButton: Button = findViewById(R.id.addMailButton)
        addMailButton.setOnClickListener {
            addSecondMail()
        }

        val addTelButton: Button = findViewById(R.id.addTelButton)
        addTelButton.setOnClickListener {
            addSecondNum()
        }

        val addLiensButton: Button = findViewById(R.id.addLiensButton)
        addLiensButton.setOnClickListener {
            addLiens()
        }


    }

    private fun addSecondContact() {
        val textViewNomTitre2: TextView = findViewById(R.id.textViewNomTitre2)
        textViewNomTitre2.visibility = View.VISIBLE

        val textViewNom2: TextView = findViewById(R.id.textViewNom2)
        textViewNom2.visibility = View.VISIBLE

        val textViewPrenom2: TextView = findViewById(R.id.textViewPrenom2)
        textViewPrenom2.visibility = View.VISIBLE

        val editTextNom2: EditText = findViewById(R.id.editTextNom2)
        editTextNom2.visibility = View.VISIBLE

        val editTextPrenom2: EditText = findViewById(R.id.editTextPrenom2)
        editTextPrenom2.visibility = View.VISIBLE

        val textViewMail1: TextView = findViewById(R.id.textViewMail1)
        textViewMail1.visibility = View.VISIBLE

        val addContactButton: Button = findViewById(R.id.addContactButton)
        addContactButton.visibility = View.GONE

        val constraintLayout: ConstraintLayout = findViewById(R.id.parent_layout)
        val constraintSet = ConstraintSet()
        constraintSet.clone(constraintLayout)

        constraintSet.connect(R.id.textViewMail1, ConstraintSet.TOP, R.id.editTextPrenom2, ConstraintSet.BOTTOM)
        constraintSet.applyTo(constraintLayout)
    }

    private fun addSecondMail() {

        val textViewMail2: TextView = findViewById(R.id.textViewMail2)
        textViewMail2.visibility = View.VISIBLE


        val editTextMail2_layout: TextInputLayout = findViewById(R.id.editTextMail2_layout)
        editTextMail2_layout.visibility = View.VISIBLE

        val editTextMail2: TextInputEditText = findViewById(R.id.editTextMail2)
        editTextMail2.visibility = View.VISIBLE

        val addMailButton: Button = findViewById(R.id.addMailButton)
        addMailButton.visibility = View.GONE

        val constraintLayout: ConstraintLayout = findViewById(R.id.parent_layout)
        val constraintSet = ConstraintSet()
        constraintSet.clone(constraintLayout)

        constraintSet.connect(R.id.textViewMdp, ConstraintSet.TOP, R.id.editTextMail2_layout, ConstraintSet.BOTTOM)
        constraintSet.applyTo(constraintLayout)

    }

    private fun addSecondNum() {

        val textViewNumTel2: TextView = findViewById(R.id.textViewNumTel2)
        textViewNumTel2.visibility = View.VISIBLE

        val phone_input_layout2: TextInputLayout = findViewById(R.id.phone_input_layout2)
        phone_input_layout2.visibility = View.VISIBLE

        val editTextPhoneNumber2: TextInputEditText = findViewById(R.id.editTextPhoneNumber2)
        editTextPhoneNumber2.visibility = View.VISIBLE

        val countryCodePicker2: CountryCodePicker = findViewById(R.id.countryCodePicker2)
        countryCodePicker2.visibility = View.VISIBLE

        val addTelButton: Button = findViewById(R.id.addTelButton)
        addTelButton.visibility = View.GONE

        val constraintLayout: ConstraintLayout = findViewById(R.id.parent_layout)
        val constraintSet = ConstraintSet()
        constraintSet.clone(constraintLayout)

        constraintSet.connect(R.id.textViewAdresse, ConstraintSet.TOP, R.id.countryCodePicker2, ConstraintSet.BOTTOM)
        constraintSet.applyTo(constraintLayout)

    }

    private fun addLiens(){

        val textViewSiteWeb: TextView = findViewById(R.id.textViewSiteWeb)
        textViewSiteWeb.visibility = View.VISIBLE

        val editTextSiteWeb: EditText = findViewById(R.id.editTextSiteWeb)
        editTextSiteWeb.visibility = View.VISIBLE

        val textViewLinkedIn: TextView = findViewById(R.id.textViewLinkedIn)
        textViewLinkedIn.visibility = View.VISIBLE

        val editTextLinkedIn: EditText = findViewById(R.id.editTextLinkedIn)
        editTextLinkedIn.visibility = View.VISIBLE

        val textViewFacebook: TextView = findViewById(R.id.textViewFacebook)
        textViewFacebook.visibility = View.VISIBLE

        val editTextFacebook: EditText = findViewById(R.id.editTextFacebook)
        editTextFacebook.visibility = View.VISIBLE

        val addLiensButton: Button = findViewById(R.id.addLiensButton)
        addLiensButton.visibility = View.GONE

        val constraintLayout: ConstraintLayout = findViewById(R.id.parent_layout)
        val constraintSet = ConstraintSet()
        constraintSet.clone(constraintLayout)

        constraintSet.connect(R.id.register_button, ConstraintSet.TOP, R.id.editTextFacebook, ConstraintSet.BOTTOM)
        constraintSet.applyTo(constraintLayout)


    }

    private fun registerEmployeur() {
        val nomEntreprise = findViewById<EditText>(R.id.editTextNomEntreprise).text.toString()
        val nomService = findViewById<EditText>(R.id.editTextNomService).text.toString()
        val nomSousService = findViewById<EditText>(R.id.editTextNomSousService).text.toString()
        val numeroSiret = findViewById<EditText>(R.id.editTextNumNational).text.toString()
        val nomContact1 = findViewById<EditText>(R.id.editTextNom).text.toString()
        val prenomContact1 = findViewById<EditText>(R.id.editTextPrenom).text.toString()
        val nomContact2 = findViewById<EditText>(R.id.editTextNom2).text.toString()
        val prenomContact2 = findViewById<EditText>(R.id.editTextPrenom2).text.toString()
        val adresseMail = findViewById<EditText>(R.id.editTextMail1).text.toString()
        val adresseMail2 = findViewById<EditText>(R.id.editTextMail2).text.toString()
        val motDePasse = findViewById<EditText>(R.id.editTextPassword).text.toString()
        val numTelephone1 = findViewById<EditText>(R.id.editTextPhoneNumber1).text.toString()
        val numTelephone2 = findViewById<EditText>(R.id.editTextPhoneNumber2).text.toString()
        val adresse = findViewById<EditText>(R.id.editTextAdresse).text.toString()
        val codePostal = findViewById<EditText>(R.id.editTextCodePostal).text.toString()
        val ville = findViewById<EditText>(R.id.editTextVille).text.toString()
        val lienSiteWeb = findViewById<EditText>(R.id.editTextSiteWeb).text.toString()
        val lienLinkedin = findViewById<EditText>(R.id.editTextLinkedIn).text.toString()
        val lienFacebook = findViewById<EditText>(R.id.editTextFacebook).text.toString()

        val employeur = Employeur(
            adresseMail = adresseMail,
            nomEntreprise = nomEntreprise,
            nomService = nomService,
            nomSousService = nomSousService,
            numeroSiret = numeroSiret,
            nomContact1 = nomContact1,
            prenomContact1 = prenomContact1,
            nomContact2 = nomContact2,
            prenomContact2 = prenomContact2,
            adresseMail2 = adresseMail2,
            motDePasse = motDePasse,
            role = "Employeur",
            numTelephone1 = numTelephone1,
            numTelephone2 = numTelephone2,
            adresse = adresse,
            codePostal = codePostal,
            ville = ville,
            lienSiteWeb = lienSiteWeb,
            lienLinkedin = lienLinkedin,
            lienFacebook = lienFacebook
        )

        employeurController.insertEmployeur(employeur)

        val intent = Intent(this, EmployeurSlidesActivity::class.java)
        startActivity(intent)

        /*val intent = Intent(this, ConfirmationInscriptionActivity::class.java)
        startActivity(intent)*/
    }



}
