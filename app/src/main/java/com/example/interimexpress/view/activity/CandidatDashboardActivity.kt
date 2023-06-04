package com.example.interimexpress.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.interimexpress.R
import com.example.interimexpress.controller.CandidatController
import com.example.interimexpress.controller.EmployeurController
import com.example.interimexpress.controller.PostulerController
import com.example.interimexpress.model.Candidat
import com.example.interimexpress.model.Employeur
import com.example.interimexpress.view.fragment.FooterCandidatFragment
import java.text.SimpleDateFormat
import java.util.*

class CandidatDashboardActivity : AppCompatActivity() {
    private val postulerController = PostulerController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidat_dashboard)


        val sharedPreferences = getSharedPreferences("InterimExpress", Context.MODE_PRIVATE)
        val mail = sharedPreferences.getString("userMail", "")

        val candidatController = CandidatController()

        val candidatTask = candidatController.getCandidat(mail.toString())


        candidatTask.addOnSuccessListener { document ->
            if (document.exists()) {
                val candidat = document.toObject(Candidat::class.java)

                val nomComplet = "${candidat?.nom} ${candidat?.prenom}"
                findViewById<TextView>(R.id.lenom).text = nomComplet
                findViewById<TextView>(R.id.lemail).setText(candidat?.adresseMail)


                val empTask2 = postulerController.getUntreatedPostulerByCandidatId(candidat?.adresseMail.toString())
                empTask2.addOnSuccessListener { document ->
                    val footerFragment = supportFragmentManager.findFragmentById(R.id.footerFragment) as? FooterCandidatFragment
                    if (document.size()!=0) {
                        footerFragment?.setNotificationIconActive(true)
                    }
                    else{
                        footerFragment?.setNotificationIconActive(false)
                    }
                }
            }
        }


        val logout = findViewById<ImageView>(R.id.logout)

        logout.setOnClickListener {
            logout()
        }

        val layout_carte = findViewById<ConstraintLayout>(R.id.layout_carte)
        layout_carte.setOnClickListener{
            val intent = Intent(this, RechercheOffresActivity::class.java)
            startActivity(intent)
        }

        val layout_carte2 = findViewById<FrameLayout>(R.id.layout_carte2)
        layout_carte2.setOnClickListener{
            val intent = Intent(this, MesNotifsActivity::class.java)
            startActivity(intent)
        }

        val layout_carte3 = findViewById<FrameLayout>(R.id.layout_carte3)
        layout_carte3.setOnClickListener{
            val intent = Intent(this, OffresFavoriActivity::class.java)
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
