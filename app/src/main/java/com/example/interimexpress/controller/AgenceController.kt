package com.example.interimexpress.controller

import android.util.Log
import com.example.interimexpress.model.Agence
import com.example.interimexpress.model.InitialData
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class AgenceController {

    private val db = FirebaseFirestore.getInstance()
    private val agencesCollection = db.collection("agences")
    private val versionDocument = db.collection("system").document("agenceVersion")

    fun getAgence(id: String): Task<DocumentSnapshot> {
        return agencesCollection.document(id).get()
    }

    fun insertAgence(agence: Agence) {
        agence.adresseMail?.let {
            agencesCollection.document(it.toString()).set(agence).addOnFailureListener { exception ->
                Log.e("AgenceController", "Erreur lors de l'insertion d'une agence", exception)
            }
        }
    }

    fun checkAndUpdateData() {
        versionDocument.get().addOnSuccessListener { document ->
            val currentVersion = document.getLong("version")?.toInt() ?: 0
            if (currentVersion < InitialData.VERSION) {
                for (agence in InitialData.agences) {
                    insertAgence(agence)
                }
                versionDocument.set(mapOf("version" to InitialData.VERSION))
            }
        }.addOnFailureListener { exception ->
            Log.e("AgenceController", "Erreur lors de la récupération de la version", exception)
        }
    }

    fun getAgenceByMailAndPassword(mail: String, password: String): Task<QuerySnapshot> {
        return agencesCollection
            .whereEqualTo("adresseMail", mail)
            .whereEqualTo("motDePasse", password)
            .limit(1)
            .get()
    }
}

