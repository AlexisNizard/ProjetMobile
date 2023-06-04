package com.example.interimexpress.controller
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import android.net.Uri
import android.util.Log
import com.example.interimexpress.model.InitialData
import com.example.interimexpress.model.Postuler
import com.google.firebase.firestore.QuerySnapshot


class PostulerController {
    private val db = FirebaseFirestore.getInstance()
    private val postulerCollection = db.collection("postuler")
    private val versionDocument = db.collection("system").document("postulerVersion")

    private val storage = FirebaseStorage.getInstance()

    fun getPostuler(id: String): Task<DocumentSnapshot> {
        return postulerCollection.document(id).get()
    }

    fun insertPostuler(postuler: Postuler) {
        postulerCollection.add(postuler)
            .addOnSuccessListener { documentReference ->
                Log.d("PostulerController", "Postuler inserted with ID: ${documentReference.id}")
                postuler.id = documentReference.id // Update postuler's id
                postulerCollection.document(documentReference.id).update("id", documentReference.id) // Update id in database
                    .addOnSuccessListener {
                        Log.d("PostulerController", "Postuler's id updated successfully in Firestore.")
                    }
                    .addOnFailureListener { exception ->
                        Log.e("PostulerController", "Error updating postuler's id in Firestore", exception)
                    }
            }
            .addOnFailureListener { exception ->
                Log.e("PostulerController", "Error inserting postuler", exception)
            }
    }

    fun checkAndUpdateData() {
        /*versionDocument.get().addOnSuccessListener { document ->
            val currentVersion = document.getLong("version")?.toInt() ?: 0
            if (currentVersion < InitialData.VERSION) {
                for (postuler in InitialData.postulers) {
                    insertPostuler(postuler)
                }
                versionDocument.set(mapOf("version" to InitialData.VERSION))
            }
        }.addOnFailureListener { exception ->
            Log.e("PostulerController", "Erreur lors de la récupération de la version", exception)
        }*/
    }


    fun uploadFile(uri: Uri, path: String): Task<Uri> {
        val fileReference = storage.reference.child(path)
        return fileReference.putFile(uri)
            .continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let { throw it }
                }
                fileReference.downloadUrl
            }
    }

    fun getUntreatedPostulerByEmployerId(employerId: String): Task<QuerySnapshot> {
        return postulerCollection
            .whereEqualTo("employerId", employerId)
            .whereEqualTo("traite", 0)
            .get()
    }

    fun getUntreatedPostulerByCandidatId(cId: String): Task<QuerySnapshot> {
        return postulerCollection
            .whereEqualTo("userId", cId)
            .whereEqualTo("traite", 1)
            .get()
    }


    fun getPostulersByEmployer(employerId: String, traite: Int): Task<QuerySnapshot> {
        return postulerCollection.whereEqualTo("employerId", employerId).whereEqualTo("traite", traite).get()
    }

    fun acceptPostuler(postulerId: String) {
        val docRef = postulerCollection.document(postulerId)
        docRef.update("traite", 1, "accept", 1)
            .addOnSuccessListener {
                Log.d("PostulerController", "Postuler has been accepted successfully")
            }
            .addOnFailureListener { exception ->
                Log.e("PostulerController", "Error accepting postuler", exception)
            }
    }

    fun refusePostuler(postulerId: String) {
        val docRef = postulerCollection.document(postulerId)
        docRef.update("traite", 1, "accept", 0)
            .addOnSuccessListener {
                Log.d("PostulerController", "Postuler has been refused successfully")
            }
            .addOnFailureListener { exception ->
                Log.e("PostulerController", "Error refusing postuler", exception)
            }
    }

}