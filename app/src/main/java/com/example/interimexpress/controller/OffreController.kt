package com.example.interimexpress.controller

import android.content.ContentValues.TAG
import android.util.Log
import com.example.interimexpress.model.InitialData
import com.example.interimexpress.model.Offre
import com.google.android.gms.tasks.Task
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import java.util.concurrent.TimeUnit

class OffreController {

    private val db = FirebaseFirestore.getInstance()
    private val offresCollection = db.collection("offres")
    private val versionDocument = db.collection("system").document("offreVersion")

    fun getOffre(id: String): Task<DocumentSnapshot> {
        return offresCollection.document(id).get()
    }

    fun getOffres(): Task<QuerySnapshot> {
        //return offresCollection.get()
        return offresCollection.limit(10).get()
    }

    fun getOffresByCity(city: String): Task<QuerySnapshot> {
        return offresCollection.whereEqualTo("adresse", city).get()
    }

    fun getAllOffres(): Task<QuerySnapshot> {
        return offresCollection.get()
    }

    fun getTop10Offres(): Task<List<Offre>> {
        return offresCollection.get().continueWith { task ->
            val offres = task.result?.toObjects(Offre::class.java)
            if (offres != null) {
                offres.sortWith(Comparator { offre1, offre2 ->
                    val currentTime = Timestamp.now().toDate().time
                    val dayInMillis = TimeUnit.DAYS.toMillis(1)

                    val daysSinceCreation1 = TimeUnit.MILLISECONDS.toDays(currentTime - offre1.dateCreation?.toDate()?.time!!)
                    val daysSinceCreation2 = TimeUnit.MILLISECONDS.toDays(currentTime - offre2.dateCreation?.toDate()?.time!!)

                    val score1 = offre1.nbrClick - daysSinceCreation1
                    val score2 = offre2.nbrClick - daysSinceCreation2

                    score2.compareTo(score1) // On tri en ordre décroissant
                })
                offres.take(10)
            } else {
                emptyList()
            }
        }
    }


    fun insertOffre(offre: Offre) {
        Log.d("OffreController", "Attempting to insert offre: $offre")
        offresCollection.add(offre)
            .addOnSuccessListener { documentReference ->
                Log.d("OffreController", "Offre inserted with ID: ${documentReference.id}")
                offre.id = documentReference.id // Update offre's id
                offresCollection.document(documentReference.id).update("id", documentReference.id) // Update id in database
                    .addOnSuccessListener {
                        Log.d("OffreController", "Offre's id updated successfully in Firestore.")
                    }
                    .addOnFailureListener { exception ->
                        Log.e("OffreController", "Error updating offre's id in Firestore", exception)
                    }
            }
            .addOnFailureListener { exception ->
                Log.e("OffreController", "Error inserting offre", exception)
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

    fun incrementOffreClick(id: String) {
        val offreRef = db.collection("offres").document(id)

        offreRef.update("nbrClick", FieldValue.increment(1))
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }

}
