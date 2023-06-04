package com.example.interimexpress.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.interimexpress.R
import com.example.interimexpress.controller.EmployeurController
import com.example.interimexpress.model.Employeur

class AccpRefInscrEmpActivity : AppCompatActivity() {

    private lateinit var employeurController: EmployeurController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accp_ref_inscr_emp)

        val emailEmployeur = intent.getStringExtra("emailEmployeur")

        employeurController = EmployeurController()
        employeurController.getEmployeur(emailEmployeur!!).addOnSuccessListener { document ->
            if (document.exists()) {
                val employeur = document.toObject(Employeur::class.java)
                populateEmployeurDetails(employeur)
            } else {
                Log.d("AccpRefInscrEmpActivity", "No such document")
            }
        }.addOnFailureListener { exception ->
            Log.d("AccpRefInscrEmpActivity", "get failed with ", exception)
        }

        val btnAcc = findViewById<Button>(R.id.btn_acc)
        val btnRef = findViewById<Button>(R.id.btn_ref)

        btnAcc.setOnClickListener {
            employeurController.acceptEmployeur(emailEmployeur!!)
            val intent = Intent(this, NotifsCreationCompteEmpActivity::class.java)
            Toast.makeText(this, "Le compte de l'Employeur a été validé avec succès", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }

        btnRef.setOnClickListener {
            employeurController.refuseEmployeur(emailEmployeur!!)
            val intent = Intent(this, NotifsCreationCompteEmpActivity::class.java)
            Toast.makeText(this, "Le compte de l'Employeur a été refusé avec succès", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }


    }





    private fun populateEmployeurDetails(employeur: Employeur?) {
        employeur?.let {
            val nom_c = findViewById<TextView>(R.id.nom_c)
            if(it.nomEntreprise.isNullOrBlank()){
                nom_c.text = "<Non renseigné>"
            }
            else{
                nom_c.text = it.nomEntreprise
            }

            val nom_c2 = findViewById<TextView>(R.id.nom_c2)
            if(it.nomService.isNullOrBlank()){
                nom_c2.text = "<Non renseigné>"
            }
            else{
                nom_c2.text = it.nomService
            }

            val nom_c3 = findViewById<TextView>(R.id.nom_c3)
            if(it.nomSousService.isNullOrBlank()){
                nom_c3.text = "<Non renseigné>"
            }
            else{
                nom_c3.text = it.nomSousService
            }


            val nom_c4 = findViewById<TextView>(R.id.nom_c4)
            if(it.numeroSiret.isNullOrBlank()){
                nom_c4.text = "<Non renseigné>"
            }
            else{
                nom_c4.text = it.numeroSiret
            }

            val nom_c5 = findViewById<TextView>(R.id.nom_c5)
            if(it.nomContact1.isNullOrBlank()){
                nom_c5.text = "<Non renseigné>"
            }
            else{
                nom_c5.text = it.nomContact1
            }

            val nom_c6 = findViewById<TextView>(R.id.nom_c6)
            if(it.prenomContact1.isNullOrBlank()){
                nom_c6.text = "<Non renseigné>"
            }
            else{
                nom_c6.text = it.prenomContact1
            }

            val nom_c7 = findViewById<TextView>(R.id.nom_c7)
            if(it.nomContact2.isNullOrBlank()){
                nom_c7.text = "<Non renseigné>"
            }
            else{
                nom_c7.text = it.nomContact2
            }

            val nom_c8 = findViewById<TextView>(R.id.nom_c8)
            if(it.prenomContact2.isNullOrBlank()){
                nom_c8.text = "<Non renseigné>"
            }
            else{
                nom_c8.text = it.prenomContact2
            }

            val nom_c9 = findViewById<TextView>(R.id.nom_c9)
            if(it.adresseMail.isNullOrBlank()){
                nom_c9.text = "<Non renseigné>"
            }
            else{
                nom_c9.text = it.adresseMail
            }

            val nom_c10 = findViewById<TextView>(R.id.nom_c10)
            if(it.adresseMail2.isNullOrBlank()){
                nom_c10.text = "<Non renseigné>"
            }
            else{
                nom_c10.text = it.adresseMail2
            }

            val nom_c11 = findViewById<TextView>(R.id.nom_c11)
            if(it.numTelephone1.isNullOrBlank()){
                nom_c11.text = "<Non renseigné>"
            }
            else{
                nom_c11.text = it.numTelephone1
            }

            val nom_c12 = findViewById<TextView>(R.id.nom_c12)
            if(it.numTelephone2.isNullOrBlank()){
                nom_c12.text = "<Non renseigné>"
            }
            else{
                nom_c12.text = it.numTelephone2
            }

            val nom_c13 = findViewById<TextView>(R.id.nom_c13)
            if(it.adresse.isNullOrBlank()){
                nom_c13.text = "<Non renseigné>"
            }
            else{
                nom_c13.text = it.adresse
            }

            val nom_c14 = findViewById<TextView>(R.id.nom_c14)
            if(it.codePostal.isNullOrBlank()){
                nom_c14.text = "<Non renseigné>"
            }
            else{
                nom_c14.text = it.codePostal
            }

            val nom_c15 = findViewById<TextView>(R.id.nom_c15)
            if(it.ville.isNullOrBlank()){
                nom_c15.text = "<Non renseigné>"
            }
            else{
                nom_c15.text = it.ville
            }

            val nom_c16 = findViewById<TextView>(R.id.nom_c16)
            if(it.lienSiteWeb.isNullOrBlank()){
                nom_c16.text = "<Non renseigné>"
            }
            else{
                nom_c16.text = it.lienSiteWeb
            }

            val nom_c17 = findViewById<TextView>(R.id.nom_c17)
            if(it.lienLinkedin.isNullOrBlank()){
                nom_c17.text = "<Non renseigné>"
            }
            else{
                nom_c17.text = it.lienLinkedin
            }

            val nom_c18 = findViewById<TextView>(R.id.nom_c18)
            if(it.lienFacebook.isNullOrBlank()){
                nom_c18.text = "<Non renseigné>"
            }
            else{
                nom_c18.text = it.lienFacebook
            }

        }
    }

}
