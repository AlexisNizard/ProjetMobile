package com.example.interimexpress.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.interimexpress.R
import com.example.interimexpress.controller.CandidatController
import com.example.interimexpress.controller.EmployeurController
import com.example.interimexpress.model.Candidat
import com.example.interimexpress.model.Employeur

class EmployeurDashboardActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employeur_dashboard)

        val logout = findViewById<ImageView>(R.id.logout)

        logout.setOnClickListener {
            logout()
        }

        val sharedPreferences = getSharedPreferences("InterimExpress", Context.MODE_PRIVATE)
        val mail = sharedPreferences.getString("userMail", "") // récupérer l'email de l'utilisateur

        // Obtenez une instance du CandidatController
        val employeurController = EmployeurController()

        // Récupérez le document pour cet utilisateur
        val empTask = employeurController.getEmployeur(mail.toString())

        empTask.addOnSuccessListener { document ->
            if (document.exists()) {
                val employeur = document.toObject(Employeur::class.java)
                findViewById<TextView>(R.id.lemail).setText(employeur?.adresseMail)

            }
        }
        val layout_carte = findViewById<ConstraintLayout>(R.id.layout_carte)
        layout_carte.setOnClickListener{
            val intent = Intent(this, CreerOffreEmploiActivity::class.java)
            startActivity(intent)
        }

        val mes_offr = findViewById<FrameLayout>(R.id.layout_carte2)
        mes_offr.setOnClickListener{
            val intent = Intent(this, MesOffresActivity::class.java)
            startActivity(intent)
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