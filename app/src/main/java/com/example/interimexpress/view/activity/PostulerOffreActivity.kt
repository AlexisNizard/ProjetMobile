package com.example.interimexpress.view.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.interimexpress.R
import com.example.interimexpress.adapter.NationalitySpinnerAdapter
import com.example.interimexpress.controller.CandidatController
import com.example.interimexpress.controller.OffreController
import com.example.interimexpress.controller.PostulerController
import com.example.interimexpress.model.Candidat
import com.example.interimexpress.model.Postuler
import java.text.SimpleDateFormat
import java.util.*

class PostulerOffreActivity : AppCompatActivity() {
    private lateinit var editTextDate: EditText
    private lateinit var nationalitySpinner: Spinner
    private lateinit var offreController: OffreController
    private lateinit var nationalities: Array<String>

    companion object {
        const val REQUEST_CODE_CV = 1
        const val REQUEST_CODE_LM = 2
    }

    private lateinit var postulerController: PostulerController
    private var cvUrl: Uri? = null
    private var lmUrl: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_postuler_offre)

        nationalitySpinner = findViewById(R.id.spinnerNationality)
        editTextDate = findViewById(R.id.editTextDate)
        setupDateEditText()
        setupSpinner()

        val cvButton = findViewById<Button>(R.id.cvButton)
        val lmButton = findViewById<Button>(R.id.lmButton)

        cvButton.setOnClickListener {
            selectFile(REQUEST_CODE_CV)
        }

        lmButton.setOnClickListener {
            selectFile(REQUEST_CODE_LM)
        }

        postulerController = PostulerController()
        val envoyerButton = findViewById<Button>(R.id.envoyer_candid)
        envoyerButton.setOnClickListener {
            if (cvUrl != null && lmUrl != null) {
                sendPostuler()
            } else {
                // Gérer l'erreur ici
            }
        }

        val sharedPreferences = getSharedPreferences("InterimExpress", Context.MODE_PRIVATE)
        val mail = sharedPreferences.getString("userMail", "") // récupérer l'email de l'utilisateur

        // Obtenez une instance du CandidatController
        val candidatController = CandidatController()

        // Récupérez le document pour cet utilisateur
        val candidatTask = candidatController.getCandidat(mail.toString())

        // Si la requête est réussie, utilisez les données pour remplir les champs de texte
        candidatTask.addOnSuccessListener { document ->
            if (document.exists()) {
                val candidat = document.toObject(Candidat::class.java)

                findViewById<EditText>(R.id.editTextNom).setText(candidat?.nom)
                findViewById<EditText>(R.id.editTextPrenom).setText(candidat?.prenom)
                val originalFormat = SimpleDateFormat("dd-MM-yyyy", Locale.FRANCE)
                val targetFormat = SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE)
                val date = originalFormat.parse(candidat?.dateNaissance)
                findViewById<EditText>(R.id.editTextDate).setText(targetFormat.format(date))


                val index = nationalities.indexOf(candidat?.nationalite)
                if (index != -1) {
                    // l'élément existe dans le Spinner, sélectionnez-le
                    nationalitySpinner.setSelection(index)
                }
            }
        }.addOnFailureListener { exception ->
            // Gérer l'erreur ici
            Log.e("PostulerOffreActivity", "Erreur lors de la récupération du candidat", exception)
        }



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


    private fun setupSpinner() {
        nationalities = resources.getStringArray(R.array.nationalities)
        val adapter = NationalitySpinnerAdapter(this, nationalities)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item2)
        nationalitySpinner.adapter = adapter

    }


    private fun isValidDate(date: String): Boolean {
        val parts = date.split("/")
        if (parts.size != 3) {
            return false
        }

        val day = parts[0].toIntOrNull() ?: return false
        val month = parts[1].toIntOrNull() ?: return false
        val year = parts[2].toIntOrNull() ?: return false

        if (day !in 1..31 || month !in 1..12 || year < 1900 || year > Calendar.getInstance().get(
                Calendar.YEAR)) {
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

    private fun selectFile(requestCode: Int) {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val uri = data?.data
            if (uri != null) {
                when (requestCode) {
                    REQUEST_CODE_CV -> {
                        postulerController.uploadFile(uri, "CVs/${UUID.randomUUID()}.pdf").addOnSuccessListener { url ->
                            cvUrl = url
                            // Ajouter le code ici pour changer le texte du bouton.
                            findViewById<Button>(R.id.cvButton).text = "CV Importé"
                        }.addOnFailureListener { exception ->
                            // Gérer l'erreur ici
                        }
                    }
                    REQUEST_CODE_LM -> {
                        postulerController.uploadFile(uri, "LMs/${UUID.randomUUID()}.pdf").addOnSuccessListener { url ->
                            lmUrl = url
                            // Ajouter le code ici pour changer le texte du bouton.
                            findViewById<Button>(R.id.lmButton).text = "LM Importé"
                        }.addOnFailureListener { exception ->
                            // Gérer l'erreur ici
                        }
                    }
                }
            }
        }
    }

    private fun sendPostuler() {

        // le code pour récupéré le mail de l'utilisateur
        offreController = OffreController()
        val id : String = intent.getStringExtra("offre_id") ?: "1"


        val sharedPreferences = getSharedPreferences("InterimExpress", Context.MODE_PRIVATE)
        val mail = sharedPreferences.getString("userMail", "") // récupérer l'email de l'utilisateur

        val nom = findViewById<EditText>(R.id.editTextNom).text.toString()
        val prenom = findViewById<EditText>(R.id.editTextPrenom).text.toString()
        val dateNaissance = findViewById<EditText>(R.id.editTextDate).text.toString()
        val nationalite = nationalitySpinner.selectedItem.toString()

        val loffre = offreController.getOffre(id)
        loffre.addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val entreprise = documentSnapshot.get("entreprise") as String?
                val postuler = Postuler(
                    id = UUID.randomUUID().toString(),
                    userId = mail,
                    employerId = entreprise,
                    offerId = id,
                    nom = nom,
                    prenom = prenom,
                    dateNaissance = dateNaissance,
                    nationalite = nationalite,
                    cv = cvUrl.toString(),
                    lm = lmUrl.toString()
                )
                postulerController.insertPostuler(postuler)
                val intent = Intent(this, RechercheOffresActivity::class.java)
                startActivity(intent)
                finish()
                // Afficher le Toast
                Toast.makeText(
                    this,
                    "Votre candidature a été envoyée avec succès.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }



}