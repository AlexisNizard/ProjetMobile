package com.example.interimexpress.controller

import android.util.Log
import com.example.interimexpress.model.Employeur
import com.example.interimexpress.model.InitialData
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.SetOptions

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

    fun getEmployeurByMailAndPassword(mail: String, password: String): Task<QuerySnapshot> {
        return employeursCollection
            .whereEqualTo("adresseMail", mail)
            .whereEqualTo("motDePasse", password)
            .limit(1)
            .get()
    }

    fun getAllEmployeurs(): Task<QuerySnapshot> {
        return employeursCollection.get()
    }

    fun getEmployeursNonValides(): Task<QuerySnapshot> {
        return employeursCollection.whereEqualTo("valide", 0).get()
    }

    fun getEmployeursNonRep(): Task<QuerySnapshot> {
        return employeursCollection.whereEqualTo("repondu", 0).get()
    }


    fun deleteEmployeur(adresseMail: String): Task<Void> {
        return employeursCollection.document(adresseMail).delete()
    }

    fun acceptEmployeur(emailEmployeur: String) {
        val docRef = employeursCollection.document(emailEmployeur)
        docRef.update("repondu", 1, "valide", 1)
            .addOnSuccessListener {
                Log.d("EmployeurController", "Employeur has been accepted successfully")
            }
            .addOnFailureListener { exception ->
                Log.e("EmployeurController", "Error accepting employeur", exception)
            }
    }

    fun refuseEmployeur(emailEmployeur: String) {
        val docRef = employeursCollection.document(emailEmployeur)
        docRef.update("repondu", 1)
            .addOnSuccessListener {
                Log.d("EmployeurController", "Employeur has been refused successfully")
            }
            .addOnFailureListener { exception ->
                Log.e("EmployeurController", "Error refusing employeur", exception)
            }
    }

}
