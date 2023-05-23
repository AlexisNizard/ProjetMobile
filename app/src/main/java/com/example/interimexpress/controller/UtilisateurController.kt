package com.example.interimexpress.controller

import android.util.Log
import com.example.interimexpress.model.InitialData
import com.example.interimexpress.model.Utilisateur
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class UtilisateurController {

    private val db = FirebaseFirestore.getInstance()
    private val utilisateursCollection = db.collection("utilisateurs")
    private val versionDocument = db.collection("system").document("utilisateurVersion")

    fun getUtilisateur(id: String): Task<DocumentSnapshot> {
        return utilisateursCollection.document(id).get()
    }

    fun insertUtilisateur(utilisateur: Utilisateur) {
        utilisateur.id?.let {
            utilisateursCollection.document(it.toString()).set(utilisateur).addOnFailureListener { exception ->
                Log.e("UtilisateurController", "Erreur lors de l'insertion d'un utilisateur", exception)
            }
        }
    }

    fun checkAndUpdateData() {
        versionDocument.get().addOnSuccessListener { document ->
            val currentVersion = document.getLong("version")?.toInt() ?: 0
            if (currentVersion < InitialData.VERSION) {
                for (utilisateur in InitialData.utilisateurs) {
                    insertUtilisateur(utilisateur)
                }
                versionDocument.set(mapOf("version" to InitialData.VERSION))
            }
        }.addOnFailureListener { exception ->
            Log.e("UtilisateurController", "Erreur lors de la récupération de la version", exception)
        }
    }
}
