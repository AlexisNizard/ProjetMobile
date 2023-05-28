package com.example.interimexpress.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.interimexpress.R
import com.example.interimexpress.controller.CandidatController
import com.example.interimexpress.model.Candidat
import java.text.SimpleDateFormat
import java.util.*

class CandidatDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidat_dashboard)

        val logout = findViewById<ImageView>(R.id.logout)

        logout.setOnClickListener {
            logout()
        }

        val sharedPreferences = getSharedPreferences("InterimExpress", Context.MODE_PRIVATE)
        val mail = sharedPreferences.getString("userMail", "") // récupérer l'email de l'utilisateur

        // Obtenez une instance du CandidatController
        val candidatController = CandidatController()

        // Récupérez le document pour cet utilisateur
        val candidatTask = candidatController.getCandidat(mail.toString())

        candidatTask.addOnSuccessListener { document ->
            if (document.exists()) {
                val candidat = document.toObject(Candidat::class.java)

                val nomComplet = "${candidat?.nom} ${candidat?.prenom}"
                findViewById<TextView>(R.id.lenom).text = nomComplet
                findViewById<TextView>(R.id.lemail).setText(candidat?.adresseMail)

            }
        }
    }

    private fun logout() {
        // Récupère l'instance de SharedPreferences
        val sharedPreferences = getSharedPreferences("InterimExpress", Context.MODE_PRIVATE)

        // Modifie la valeur de userRole à ""
        sharedPreferences.edit().putString("userRole", "").apply()

        // Redirige vers MainActivity (ou l'activité de login) après la déconnexion
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
