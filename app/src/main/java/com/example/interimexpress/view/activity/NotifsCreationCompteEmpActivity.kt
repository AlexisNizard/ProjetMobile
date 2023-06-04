package com.example.interimexpress.view.activity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.interimexpress.R
import com.example.interimexpress.adapter.EmpApprobAdapter
import com.example.interimexpress.adapter.MesEmployeursAdapter
import com.example.interimexpress.controller.EmployeurController
import com.example.interimexpress.model.Employeur
import com.example.interimexpress.view.fragment.FooterCandidatFragment

class NotifsCreationCompteEmpActivity : AppCompatActivity() {

    private val employeurController = EmployeurController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifs_creation_compte_emp)

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

        val imgProfil = findViewById<ImageView>(R.id.logo);
        imgProfil.setOnClickListener {
            val intent = Intent(this, GestionnaireDashboardActivity::class.java)
            startActivity(intent)
        }

        employeurController.getEmployeursNonRep().addOnSuccessListener { documents ->
            val gestionnaires = documents.map { document ->
                document.toObject(Employeur::class.java)
            }.toMutableList()

            val recyclerView = findViewById<RecyclerView>(R.id.offres_recycler_view)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = EmpApprobAdapter(gestionnaires)
        }.addOnFailureListener { exception ->
            Log.e("GererGestionnairesActivity", "Erreur lors de la récupération des gestionnaires", exception)
        }
    }

}