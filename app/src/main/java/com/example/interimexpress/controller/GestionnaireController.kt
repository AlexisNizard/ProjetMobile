package com.example.interimexpress.controller

import android.util.Log
import com.example.interimexpress.model.Gestionnaire
import com.example.interimexpress.model.InitialData
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot



class GestionnaireController {

    private val db = FirebaseFirestore.getInstance()
    private val gestionnairesCollection = db.collection("gestionnaires")
    private val versionDocument = db.collection("system").document("gestionnaireVersion")

    fun getGestionnaire(id: String): Task<DocumentSnapshot> {
        return gestionnairesCollection.document(id).get()
    }

    fun insertGestionnaire(gestionnaire: Gestionnaire) {
        gestionnaire.adresseMail?.let {
            gestionnairesCollection.document(it.toString()).set(gestionnaire).addOnFailureListener { exception ->
                Log.e("GestionnaireController", "Erreur lors de l'insertion d'un gestionnaire", exception)
            }
        }
    }

    fun checkAndUpdateData() {
        versionDocument.get().addOnSuccessListener { document ->
            val currentVersion = document.getLong("version")?.toInt() ?: 0
            if (currentVersion < InitialData.VERSION) {
                for (gestionnaire in InitialData.gestionnaires) {
                    insertGestionnaire(gestionnaire)
                }
                versionDocument.set(mapOf("version" to InitialData.VERSION))
            }
        }.addOnFailureListener { exception ->
            Log.e("GestionnaireController", "Erreur lors de la récupération de la version", exception)
        }
    }

    fun getGestionnaireByMailAndPassword(mail: String, password: String): Task<QuerySnapshot> {
        return gestionnairesCollection
            .whereEqualTo("adresseMail", mail)
            .whereEqualTo("motDePasse", password)
            .limit(1)
            .get()
    }
    fun getAllGestionnaires(): Task<QuerySnapshot> {
        return gestionnairesCollection.get()
    }


    fun deleteGestionnaire(adresseMail: String): Task<Void> {
        return gestionnairesCollection.document(adresseMail).delete()
    }
}