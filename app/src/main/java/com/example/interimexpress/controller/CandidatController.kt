package com.example.interimexpress.controller

import android.util.Log
import com.example.interimexpress.model.Candidat
import com.example.interimexpress.model.InitialData
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class CandidatController {

    private val db = FirebaseFirestore.getInstance()
    private val candidatsCollection = db.collection("candidats")
    private val versionDocument = db.collection("system").document("candidatVersion")

    fun getCandidat(id: String): Task<DocumentSnapshot> {
        return candidatsCollection.document(id).get()
    }

    fun insertCandidat(candidat: Candidat) {
        candidat.adresseMail?.let {
            candidatsCollection.document(it.toString()).set(candidat).addOnFailureListener { exception ->
                Log.e("CandidatController", "Erreur lors de l'insertion d'un candidat", exception)
            }
        }
    }

    fun checkAndUpdateData() {
        versionDocument.get().addOnSuccessListener { document ->
            val currentVersion = document.getLong("version")?.toInt() ?: 0
            if (currentVersion < InitialData.VERSION) {
                for (candidat in InitialData.candidats) {
                    insertCandidat(candidat)
                }
                versionDocument.set(mapOf("version" to InitialData.VERSION))
            }
        }.addOnFailureListener { exception ->
            Log.e("CandidatController", "Erreur lors de la récupération de la version", exception)
        }
    }
}