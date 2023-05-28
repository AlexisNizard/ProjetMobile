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
import androidx.fragment.app.Fragment
import com.example.interimexpress.R
import com.example.interimexpress.controller.CandidatController
import com.example.interimexpress.controller.EmployeurController
import com.example.interimexpress.controller.PostulerController
import com.example.interimexpress.model.Candidat
import com.example.interimexpress.model.Employeur

class EmployeurDashboardActivity  : AppCompatActivity() {
    private val postulerController = PostulerController()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employeur_dashboard)

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

                postulerController.getUntreatedPostulerByEmployerId(employeur?.adresseMail.toString())
                    .addOnSuccessListener { postulerDocuments ->
                        val numberOfUntreatedPostuler = postulerDocuments.size()
                        println("la val : $numberOfUntreatedPostuler")
                        println("l'id : ${mail.toString()}")
                        if(numberOfUntreatedPostuler > 0){
                            val sharedPreferences = getSharedPreferences("InterimExpress", Context.MODE_PRIVATE)
                            sharedPreferences.edit().putBoolean("hasUntreatedPostuler", true).apply()
                        }
                        else{
                            val sharedPreferences = getSharedPreferences("InterimExpress", Context.MODE_PRIVATE)
                            sharedPreferences.edit().putBoolean("hasUntreatedPostuler", false).apply()
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.e("EmployeurDashboardActivity", "Error getting untreated postuler", exception)
                    }
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

        val layout_carte3 = findViewById<FrameLayout>(R.id.layout_carte3)
        layout_carte3.setOnClickListener{
            val intent = Intent(this, GererCandidatureActivity::class.java)
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

    object Utility {
        fun updateNotificationIcon(context: Context, fragment: Fragment) {
            val sharedPreferences = context.getSharedPreferences("InterimExpress", Context.MODE_PRIVATE)
            val hasUntreatedPostuler = sharedPreferences.getBoolean("hasUntreatedPostuler", false)
            if(hasUntreatedPostuler) {
                val notificationIcon = fragment.view?.findViewById<ImageView>(R.id.notification_icon)
                notificationIcon?.setImageResource(R.drawable.baseline_notifications_active_24)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val fragment = supportFragmentManager.findFragmentById(R.id.footerFragment)
        if (fragment != null && fragment.isAdded()) {
            Utility.updateNotificationIcon(this, fragment)
        }
    }


}