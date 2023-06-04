package com.example.interimexpress.controller
import android.util.Log
import com.example.interimexpress.model.Admin
import com.example.interimexpress.model.Gestionnaire
import com.example.interimexpress.model.InitialData
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class AdminController {

    private val db = FirebaseFirestore.getInstance()
    private val adminsCollection = db.collection("admins")
    private val versionDocument = db.collection("system").document("adminVersion")

    fun getAdmin(id: String): Task<DocumentSnapshot> {
        return adminsCollection.document(id).get()
    }

    fun insertAdmin(admin: Admin) {
        admin.adresseMail?.let {
            adminsCollection.document(it.toString()).set(admin).addOnFailureListener { exception ->
                Log.e("AdminController", "Erreur lors de l'insertion d'un admin", exception)
            }
        }
    }

    fun checkAndUpdateData() {
        versionDocument.get().addOnSuccessListener { document ->
            val currentVersion = document.getLong("version")?.toInt() ?: 0
            if (currentVersion < InitialData.VERSION) {
                for (admin in InitialData.admins) {
                    insertAdmin(admin)
                }
                versionDocument.set(mapOf("version" to InitialData.VERSION))
            }
        }.addOnFailureListener { exception ->
            Log.e("AdminController", "Erreur lors de la récupération de la version", exception)
        }
    }

    fun getAdminByMailAndPassword(mail: String, password: String): Task<QuerySnapshot> {
        return adminsCollection
            .whereEqualTo("adresseMail", mail)
            .whereEqualTo("motDePasse", password)
            .limit(1)
            .get()
    }
}
