package com.example.interimexpress.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.interimexpress.R

class AgenceRegisterActivity : AppCompatActivity() {

    private lateinit var eTNomEntreprise: EditText
    private lateinit var eTNomService: EditText
    private lateinit var eTNomSouservice: EditText
    private lateinit var eTSiret: EditText
    private lateinit var eTContact1: EditText
    private lateinit var eTContact2: EditText
    private lateinit var eTMail1: EditText
    private lateinit var eTMail2: EditText
    private lateinit var eTTel1: EditText
    private lateinit var eTTel2: EditText
    private lateinit var eTAdresse: EditText
    private lateinit var eTCodePostal: EditText
    private lateinit var eTVille: EditText
    private lateinit var eTSiteWeb: EditText
    private lateinit var eTLinkedin: EditText
    private lateinit var eTFacebook: EditText
    private lateinit var eTMdp: EditText

    private lateinit var btnInscrire : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agence_register)
        eTNomEntreprise = findViewById(R.id.editTextNomEntreprise)
        eTSiret = findViewById(R.id.editTextNumNational)
        eTMail1 = findViewById(R.id.editTextMail1)
        eTAdresse = findViewById(R.id.editTextAdresse)
        eTCodePostal = findViewById(R.id.editTextCodePostal)
        eTVille = findViewById(R.id.editTextVille)
        eTMdp = findViewById(R.id.editTextPassword)

        btnInscrire = findViewById(R.id.login_button)
        btnInscrire.setOnClickListener {
            validate()
        }
    }

    private fun validate() {
        if (eTNomEntreprise.text.isEmpty()) {
            eTNomEntreprise.error = "Le champ NomEntreprise est obligatoire"
            return
        }

        if (eTSiret.text.isEmpty()) {
            eTSiret.error = "Le champ Numéro SIRET est obligatoire"
            return
        }

        if (eTMail1.text.isEmpty()) {
            eTMail1.error = "Le champ Mail principal est obligatoire"
            return
        }

        if (eTAdresse.text.isEmpty()) {
            eTAdresse.error = "Le champ Adresse est obligatoire"
            return
        }

        if (eTCodePostal.text.isEmpty()) {
            eTCodePostal.error = "Le champ Code Postal est obligatoire"
            return
        }

        if (eTVille.text.isEmpty()) {
            eTVille.error = "Le champ Ville est obligatoire"
            return
        }

        if (eTMdp.text.isEmpty()) {
            eTMdp.error = "Le champ Mot de passe est obligatoire"
            return
        }

        // Si tout est valide, soumettre le formulaire
        submitForm()
    }

    private fun submitForm() {
        finishAffinity()
    }
}