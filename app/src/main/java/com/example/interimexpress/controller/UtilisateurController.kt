package com.example.interimexpress.controller

/*class UtilisateurController {

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
}*/

/*open class UtilisateurController<T: Utilisateur>(
    collectionName: String
) {

    protected val db = FirebaseFirestore.getInstance()
    protected val utilisateursCollection: CollectionReference = db.collection(collectionName)
    protected val versionDocument = db.collection("system").document("utilisateurVersion")

    fun getUtilisateur(id: String): Task<DocumentSnapshot> {
        return utilisateursCollection.document(id).get()
    }

    fun insertUtilisateur(utilisateur: T) {
        utilisateur.id?.let {
            utilisateursCollection.document(it.toString()).set(utilisateur).addOnFailureListener { exception ->
                Log.e("UtilisateurController", "Erreur lors de l'insertion d'un utilisateur", exception)
            }
        }
    }

    // Other common methods...
}*/
