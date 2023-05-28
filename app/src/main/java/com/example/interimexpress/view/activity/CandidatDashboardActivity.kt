package com.example.interimexpress.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.interimexpress.R
import com.example.interimexpress.controller.CandidatController
import com.example.interimexpress.controller.PostulerController
import com.example.interimexpress.model.Candidat
import java.text.SimpleDateFormat
import java.util.*

class CandidatDashboardActivity : AppCompatActivity() {
    private val postulerController = PostulerController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidat_dashboard)

        val logout = findViewById<ImageView>(R.id.logout)

        logout.setOnClickListener {
            logout()
        }

        val layout_carte = findViewById<ConstraintLayout>(R.id.layout_carte)
        layout_carte.setOnClickListener{
            val intent = Intent(this, RechercheOffresActivity::class.java)
            startActivity(intent)
        }

        val sharedPreferences = getSharedPreferences("InterimExpress", Context.MODE_PRIVATE)
        val mail = sharedPreferences.getString("userMail", "") // récupérer l'email de l'utilisateur

        // Obtenez une instance du CandidatController
        val candidatController = CandidatController()

        // Récupérez le document pour cet utilisateur
        val candidatTask = candidatController.getCandidat(mail.toString())

        candidatTask.addOnSuccessListener { document ->
            if (document.exists()) {
                val candidat = document.toObject(Candidat::class.java)

                val nomComplet = "${candidat?.nom} ${candidat?.prenom}"
                findViewById<TextView>(R.id.lenom).text = nomComplet
                findViewById<TextView>(R.id.lemail).setText(candidat?.adresseMail)

                // Récupérez les postulants non traités pour cet employeur
                postulerController.getUntreatedPostulerByCandidatId(candidat?.adresseMail.toString())
                    .addOnSuccessListener { postulerDocuments ->
                        val numberOfUntreatedPostuler = postulerDocuments.size()
                        if(numberOfUntreatedPostuler > 0){
                            // Mettre à jour la valeur dans les préférences partagées
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
