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
import com.example.interimexpress.adapter.MesEmployeursAdapter
import com.example.interimexpress.adapter.MesGestionnairesAdapter
import com.example.interimexpress.controller.EmployeurController
import com.example.interimexpress.model.Employeur
import com.example.interimexpress.model.Gestionnaire
import com.example.interimexpress.view.fragment.FooterCandidatFragment

class GererEmployeursActivity : AppCompatActivity(), MesEmployeursAdapter.OnItemClickListener {

    private val employeurController = EmployeurController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gerer_employeurs)

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

        employeurController.getAllEmployeurs().addOnSuccessListener { documents ->
            val gestionnaires = documents.map { document ->
                document.toObject(Employeur::class.java)
            }.toMutableList()

            val recyclerView = findViewById<RecyclerView>(R.id.offres_recycler_view)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = MesEmployeursAdapter(gestionnaires, this)
        }.addOnFailureListener { exception ->
            Log.e("GererGestionnairesActivity", "Erreur lors de la récupération des gestionnaires", exception)
        }
    }


    override fun onDeleteClick(gestionnaire: Employeur) {
        AlertDialog.Builder(this)
            .setTitle("Suppression du gestionnaire")
            .setMessage("Êtes-vous sûr de vouloir supprimer cet employeur ?\nToutes ses offres d'emploi seront également supprimés de la base de données. ?")
            .setPositiveButton("Oui") { dialog, _ ->
                gestionnaire.adresseMail?.let {
                    employeurController.deleteEmployeur(it)
                        .addOnSuccessListener {
                            (findViewById<RecyclerView>(R.id.offres_recycler_view).adapter as MesEmployeursAdapter).removeItem(gestionnaire)
                            Toast.makeText(this, "L'employeur a été supprimé.", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener { exception ->
                            Log.e("GererEmployeursActivity", "Erreur lors de la suppression de l'employeur", exception)
                            Toast.makeText(this, "Erreur lors de la suppression de l'employeur.", Toast.LENGTH_SHORT).show()
                        }
                    dialog.dismiss()
                }
            }
            .setNegativeButton("Non") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}