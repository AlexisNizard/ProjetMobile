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
import com.example.interimexpress.adapter.MesGestionnairesAdapter
import com.example.interimexpress.controller.GestionnaireController
import com.example.interimexpress.model.Gestionnaire

class GererGestionnairesActivity  : AppCompatActivity(), MesGestionnairesAdapter.OnItemClickListener {

    private val gestionnaireController = GestionnaireController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gerer_gestionnaires)

        val imgProfil = findViewById<ImageView>(R.id.logo);
        imgProfil.setOnClickListener {
            val intent = Intent(this, AdminDashboardActivity::class.java)
            startActivity(intent)
        }

        gestionnaireController.getAllGestionnaires().addOnSuccessListener { documents ->
            val gestionnaires = documents.map { document ->
                document.toObject(Gestionnaire::class.java)
            }.toMutableList()

            val recyclerView = findViewById<RecyclerView>(R.id.offres_recycler_view)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = MesGestionnairesAdapter(gestionnaires, this)
        }.addOnFailureListener { exception ->
            Log.e("GererGestionnairesActivity", "Erreur lors de la récupération des gestionnaires", exception)
        }

    }

    override fun onDeleteClick(gestionnaire: Gestionnaire) {
        AlertDialog.Builder(this)
            .setTitle("Suppression du gestionnaire")
            .setMessage("Êtes-vous sûr de vouloir supprimer ce gestionnaire ?")
            .setPositiveButton("Oui") { dialog, _ ->
                gestionnaire.adresseMail?.let {
                    gestionnaireController.deleteGestionnaire(it)
                        .addOnSuccessListener {
                            (findViewById<RecyclerView>(R.id.offres_recycler_view).adapter as MesGestionnairesAdapter).removeItem(gestionnaire)
                            Toast.makeText(this, "Le gestionnaire a été supprimé.", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener { exception ->
                            Log.e("GererGestionnairesActivity", "Erreur lors de la suppression du gestionnaire", exception)
                            Toast.makeText(this, "Erreur lors de la suppression du gestionnaire.", Toast.LENGTH_SHORT).show()
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

