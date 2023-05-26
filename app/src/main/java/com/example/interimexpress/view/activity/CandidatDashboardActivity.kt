package com.example.interimexpress.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.interimexpress.R

class CandidatDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidat_dashboard)

        val logout = findViewById<ImageView>(R.id.logout)

        logout.setOnClickListener {
            logout()
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
