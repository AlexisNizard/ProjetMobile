package com.example.interimexpress.controller

import android.util.Log
import com.example.interimexpress.model.InitialData
import com.example.interimexpress.model.Offre
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class OffreController {

    private val db = FirebaseFirestore.getInstance()
    private val offresCollection = db.collection("offres")
    private val versionDocument = db.collection("system").document("offreVersion")

    fun getOffre(id: String): Task<DocumentSnapshot> {
        return offresCollection.document(id).get()
    }

    fun getOffres(): Task<QuerySnapshot> {
        return offresCollection.limit(5).get()
    }

    fun insertOffre(offre: Offre) {
        offre.id?.let {
            offresCollection.document(it.toString()).set(offre).addOnFailureListener { exception ->
                Log.e("OffreController", "Erreur lors de l'insertion d'une offre", exception)
            }
        }
    }


    fun checkAndUpdateData() {
        versionDocument.get().addOnSuccessListener { document ->
            val currentVersion = document.getLong("version")?.toInt() ?: 0
            if (currentVersion < InitialData.VERSION) {
                // La version enregistrée dans Firebase est plus ancienne que la version actuelle,
                // donc nous devons mettre à jour les données et la version.
                for (offre in InitialData.offres) {
                    insertOffre(offre)
                }
                versionDocument.set(mapOf("version" to InitialData.VERSION)) // mettre à jour la version
            }
        }.addOnFailureListener { exception ->
            Log.e("OffreController", "Erreur lors de la récupération de la version", exception)
        }
    }

}
