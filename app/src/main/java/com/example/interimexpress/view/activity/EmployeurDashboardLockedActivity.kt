package com.example.interimexpress.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.interimexpress.R
import com.example.interimexpress.controller.EmployeurController
import com.example.interimexpress.model.Employeur

class EmployeurDashboardLockedActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employeur_dashboard_locked)


        val logout = findViewById<ImageView>(R.id.logout)

        logout.setOnClickListener {
            logout()
        }

        val sharedPreferences = getSharedPreferences("InterimExpress", Context.MODE_PRIVATE)
        val mail = sharedPreferences.getString("userMail", "")

        val employeurController = EmployeurController()

        val empTask = employeurController.getEmployeur(mail.toString())

        empTask.addOnSuccessListener { document ->
            if (document.exists()) {
                val employeur = document.toObject(Employeur::class.java)
                findViewById<TextView>(R.id.lemail).setText(employeur?.adresseMail)
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