package com.example.interimexpress.view.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Spinner
import android.widget.TextView
import com.example.interimexpress.R

class PaiementAbonnementActivity : AppCompatActivity() {

    private lateinit var tv_abonnement_choisi : TextView
    private lateinit var tv_modePaiement : TextView
    private lateinit var tv_prix_choisi : TextView
    private lateinit var tv_abonnementChoisiValue : TextView
    private lateinit var tv_modePaiementValue : TextView
    private lateinit var tv_prixChoisiValue : TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paiement_abonnement)

        tv_abonnement_choisi = findViewById(R.id.tv_abonnement_choisi)
        tv_modePaiement = findViewById(R.id.tv_mode_paiement)
        tv_prix_choisi = findViewById(R.id.tv_prix_choisi)
        tv_abonnementChoisiValue = findViewById(R.id.tv_abonnementChoisiValue)
        tv_modePaiementValue = findViewById(R.id.tv_modePaiementValue)
        tv_prixChoisiValue = findViewById(R.id.tv_prixChoisiValue)

        val abonnementChoisi = intent.getStringExtra("abonnementChoisi")
        val modePaiement = intent.getStringExtra("modePaiement")
        val prixChoisi = intent.getIntExtra("prixChoisi", 0)

        tv_abonnementChoisiValue.text = abonnementChoisi
        tv_prixChoisiValue.text = "$prixChoisi€"
        tv_modePaiementValue.text = modePaiement
    }
}