package com.example.interimexpress.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.interimexpress.R
import com.example.interimexpress.controller.EmployeurController
import com.example.interimexpress.controller.GestionnaireController
import com.example.interimexpress.controller.PostulerController
import com.example.interimexpress.model.Employeur
import com.example.interimexpress.model.Gestionnaire
import com.example.interimexpress.view.fragment.FooterCandidatFragment

class GestionnaireDashboardActivity : AppCompatActivity() {

    private val employeurController = EmployeurController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestionnaire_dashboard)

        val empTask2 = employeurController.getEmployeursNonRep()
        empTask2.addOnSuccessListener { document ->
            val footerFragment = supportFragmentManager.findFragmentById(R.id.footerFragment) as? FooterCandidatFragment
            if (document.size()!=0) {
                footerFragment?.setNotificationIconActive(true)

            }
            else{
                footerFragment?.setNotificationIconActive(false)
            }
        }


        val logout = findViewById<ImageView>(R.id.logout)

        logout.setOnClickListener {
            logout()
        }

        val sharedPreferences = getSharedPreferences("InterimExpress", Context.MODE_PRIVATE)
        val mail = sharedPreferences.getString("userMail", "")


        val gestionnaireController = GestionnaireController()


        val empTask = gestionnaireController.getGestionnaire(mail.toString())

        empTask.addOnSuccessListener { document ->
            if (document.exists()) {
                val gest = document.toObject(Gestionnaire::class.java)
                findViewById<TextView>(R.id.lemail).setText(gest?.adresseMail)

            }
        }

        val layout_carte2 = findViewById<FrameLayout>(R.id.layout_carte2)
        layout_carte2.setOnClickListener{
            val intent = Intent(this, NotifsCreationCompteEmpActivity::class.java)
            startActivity(intent)
        }


        val layout_carte3 = findViewById<FrameLayout>(R.id.layout_carte3)
        layout_carte3.setOnClickListener{
            val intent = Intent(this, GererEmployeursActivity::class.java)
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