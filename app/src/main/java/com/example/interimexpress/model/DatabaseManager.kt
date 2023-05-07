package com.example.interimexpress.model

import android.content.Context
import com.example.interimexpress.controller.OffreController
import com.example.interimexpress.controller.UtilisateurController

class DatabaseManager private constructor(context: Context) {
    private val dbHelper = DatabaseHelper(context)
    val offreController = OffreController(dbHelper)
    val utilisateurController = UtilisateurController(dbHelper)

    fun insertInitialData() {
        InitialData.offres.forEach { offre ->
            offreController.insertOffre(offre.titre, offre.entreprise, offre.adresse, offre.description)
        }

        InitialData.utilisateurs.forEach { utilisateur ->
            utilisateurController.insertUtilisateur(
                utilisateur.nom,
                utilisateur.prenom,
                utilisateur.adresseMail,
                utilisateur.motDePasse
            )
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: DatabaseManager? = null

        fun getInstance(context: Context): DatabaseManager =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: DatabaseManager(context).also { INSTANCE = it }
            }
    }
}
