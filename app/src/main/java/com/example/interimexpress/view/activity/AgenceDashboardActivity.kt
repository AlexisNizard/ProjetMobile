package com.example.interimexpress.view.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.interimexpress.R

class AgenceDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agence_dashboard)

        val logout = findViewById<ImageView>(R.id.logout)
        val image1 = findViewById<ImageView>(R.id.image1)

        logout.setOnClickListener {
            logout()
        }

        image1.setOnClickListener {
            val intent = Intent(this, CreerOffreEmploiAgenceActivity::class.java)
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

