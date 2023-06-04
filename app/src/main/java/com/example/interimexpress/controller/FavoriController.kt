package com.example.interimexpress.controller

import android.util.Log
import com.example.interimexpress.model.Favori
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class FavoriController {
    private val db = FirebaseFirestore.getInstance()
    private val favorisCollection = db.collection("favoris")

    fun addFavori(favori: Favori) {
        favorisCollection.add(favori).addOnFailureListener { exception ->
            Log.e("FavoriController", "Erreur lors de l'ajout d'un favori", exception)
        }
    }

    fun removeFavori(id: String) {
        favorisCollection.document(id).delete().addOnFailureListener { exception ->
            Log.e("FavoriController", "Erreur lors de la suppression d'un favori", exception)
        }
    }

    fun isFavori(email: String, idOffre: String): Task<QuerySnapshot> {
        return favorisCollection
            .whereEqualTo("email", email)
            .whereEqualTo("idOffre", idOffre)
            .limit(1)
            .get()
    }

    fun getFavorisForUser(email: String): Task<QuerySnapshot> {
        return favorisCollection
            .whereEqualTo("email", email)
            .get()
    }
}
