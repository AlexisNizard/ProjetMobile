package com.example.interimexpress.controller

import com.example.interimexpress.model.DatabaseHelper
import com.example.interimexpress.model.Offre

class OffreController(private val dbHelper: DatabaseHelper) {

    fun insertOffre(titre: String, entreprise: String, adresse: String, description: String): Boolean {
        return dbHelper.insertData(titre, entreprise, adresse, description)
    }

    fun getOffre(id: Int): Offre? {
        val cursor = dbHelper.readableDatabase.rawQuery("SELECT * FROM ${DatabaseHelper.TABLE_NAME} WHERE ${DatabaseHelper.COLUMN_ID} = $id", null)
        var offre: Offre? = null
        if (cursor.moveToFirst()) {
            val titreIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_TITRE)
            val entrepriseIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ENTREPRISE)
            val adresseIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ADRESSE)
            val descriptionIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_DESCRIPTION)

            val titre = if (titreIndex != -1) cursor.getString(titreIndex) else ""
            val entreprise = if (entrepriseIndex != -1) cursor.getString(entrepriseIndex) else ""
            val adresse = if (adresseIndex != -1) cursor.getString(adresseIndex) else ""
            val description = if (descriptionIndex != -1) cursor.getString(descriptionIndex) else ""

            offre = Offre(id, titre, entreprise, adresse, description)
        }
        cursor.close()
        return offre
    }

}
