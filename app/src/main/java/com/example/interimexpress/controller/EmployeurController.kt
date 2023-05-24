package com.example.interimexpress.controller

import android.util.Log
import com.example.interimexpress.model.Employeur
import com.example.interimexpress.model.InitialData
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class EmployeurController {

    private val db = FirebaseFirestore.getInstance()
    private val employeursCollection = db.collection("employeurs")
    private val versionDocument = db.collection("system").document("employeurVersion")

    fun getEmployeur(id: String): Task<DocumentSnapshot> {
        return employeursCollection.document(id).get()
    }

    fun insertEmployeur(employeur: Employeur) {
        employeur.adresseMail?.let {
            employeursCollection.document(it.toString()).set(employeur).addOnFailureListener { exception ->
                Log.e("EmployeurController", "Erreur lors de l'insertion d'un employeur", exception)
            }
        }
    }

    fun checkAndUpdateData() {
        versionDocument.get().addOnSuccessListener { document ->
            val currentVersion = document.getLong("version")?.toInt() ?: 0
            if (currentVersion < InitialData.VERSION) {
                for (employeur in InitialData.employeurs) {
                    insertEmployeur(employeur)
                }
                versionDocument.set(mapOf("version" to InitialData.VERSION))
            }
        }.addOnFailureListener { exception ->
            Log.e("EmployeurController", "Erreur lors de la récupération de la version", exception)
        }
    }
}
