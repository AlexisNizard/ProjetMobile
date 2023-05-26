package com.example.interimexpress.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.interimexpress.R

class ChoisirAbonnementActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var radioGroup: RadioGroup
    private lateinit var boutonPayer: Button

    private val prixAbonnements = hashMapOf(
        "Abonnement ponctuel" to 5, // 5€
        "Abonnement mensuel" to 20, // 20€
        "Abonnement trimestriel" to 50, // 50€
        "Abonnement semestriel" to 90, //90€
        "Abonnement annuel" to 160, //160€
        "Abonnement indéterminé" to 20, //20€
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choisir_abonnement)

        spinner = findViewById(R.id.spinner)
        radioGroup = findViewById(R.id.radioGroup)
        boutonPayer = findViewById(R.id.bouton_payer)

        // Créer un ArrayAdapter à l'aide d'un tableau de chaînes prédéfini et d'un layout spinner simple
        ArrayAdapter.createFromResource(
            this,
            R.array.abonnements_array, // Un tableau de chaînes dans res/values/strings.xml qui contient les types d'abonnements
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Spécifier l'apparence à utiliser lorsque la liste des choix apparaît
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Appliquer l'adaptateur au Spinner
            spinner.adapter = adapter
        }

        boutonPayer.setOnClickListener {
            val abonnementChoisi = spinner.selectedItem.toString()
            val modePaiementChoisiId = radioGroup.checkedRadioButtonId

            // Vérification du mode de paiement.
            if(modePaiementChoisiId == -1){
                Toast.makeText(this, "Veuillez choisir un mode de paiement.", Toast.LENGTH_SHORT).show()
            }else{
                val modePaiement = when (modePaiementChoisiId) {
                    R.id.radioButton_cb -> "Carte Bancaire"
                    R.id.radioButton_prelevement -> "Prélèvement Automatique"
                    else -> ""
                }
                val prixChoisi = prixAbonnements[abonnementChoisi]

                // Conversion de centimes en euros.
                Toast.makeText(this, "Abonnement choisi: $abonnementChoisi, Mode de paiement: $modePaiement, Prix: $prixChoisi", Toast.LENGTH_SHORT).show()

                // Créer une intention pour lancer PaiementAbonnementActivity.
                val intent = Intent(this, PaiementAbonnementActivity::class.java).apply {
                    // Ajouter l'abonnement et le mode de paiement en tant que données extra.
                    putExtra("abonnementChoisi", abonnementChoisi)
                    putExtra("modePaiement", modePaiement)
                    putExtra("prixChoisi", prixChoisi)
                }

                // Lancer PaiementAbonnementActivity.
                startActivity(intent)
            }
        }

    }
}
