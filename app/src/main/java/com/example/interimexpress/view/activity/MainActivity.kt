package com.example.interimexpress.view.activity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.interimexpress.R

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnConnecter = findViewById<Button>(R.id.login_button)
        val btnRegister = findViewById<Button>(R.id.register_button)
        val txtContinueAnonyme = findViewById<TextView>(R.id.continue_anonymously)

        btnConnecter.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnRegister.setOnClickListener {
            val intent = Intent(this, ChoisirRoleActivity::class.java)
            startActivity(intent)
        }

        txtContinueAnonyme.setOnClickListener {
            val intent = Intent(this, RechercheOffresActivity::class.java)
            startActivity(intent)
        }

        /*loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }*/


        /*login_button.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }*/
    }
}