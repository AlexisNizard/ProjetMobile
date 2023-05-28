package com.example.interimexpress.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.interimexpress.R

class ChoisirRoleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choisir_role)

        val imgCandidat = findViewById<ImageView>(R.id.logoCandidat);
        val imgEmployeur = findViewById<ImageView>(R.id.logoEmployeur);
        val imgAgence = findViewById<ImageView>(R.id.logoAgence);

        imgCandidat.setOnClickListener {
            val intent = Intent(this, CandidatRegisterActivity::class.java)
            startActivity(intent)
        }

        imgEmployeur.setOnClickListener {
            val intent = Intent(this,EmployeurRegisterActivity::class.java)
            startActivity(intent)
        }

        imgAgence.setOnClickListener {
            val intent = Intent(this,AgenceRegisterActivity::class.java)
            startActivity(intent)
        }

    }
}