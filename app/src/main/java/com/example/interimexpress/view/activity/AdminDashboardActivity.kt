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
import com.example.interimexpress.controller.AdminController
import com.example.interimexpress.controller.GestionnaireController
import com.example.interimexpress.model.Admin
import com.example.interimexpress.model.Gestionnaire


class AdminDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)

        val logout = findViewById<ImageView>(R.id.logout)

        logout.setOnClickListener {
            logout()
        }

        val sharedPreferences = getSharedPreferences("InterimExpress", Context.MODE_PRIVATE)
        val mail = sharedPreferences.getString("userMail", "")

        val adminController = AdminController()


        val empTask = adminController.getAdmin(mail.toString())

        empTask.addOnSuccessListener { document ->
            if (document.exists()) {
                val admin = document.toObject(Admin::class.java)
                findViewById<TextView>(R.id.lemail).setText(admin?.adresseMail)

            }
        }

        val layout_carte = findViewById<ConstraintLayout>(R.id.layout_carte)
        layout_carte.setOnClickListener{
            val intent = Intent(this, CreerGestionnaireActivity::class.java)
            startActivity(intent)
        }


        val layout_carte2 = findViewById<FrameLayout>(R.id.layout_carte2)
        layout_carte2.setOnClickListener{
            val intent = Intent(this, GererGestionnairesActivity::class.java)
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