package com.example.interimexpress.controller

import com.example.interimexpress.model.DatabaseHelper
import com.example.interimexpress.model.Utilisateur

class UtilisateurController(private val dbHelper: DatabaseHelper) {

    fun insertUtilisateur(nom: String, prenom: String, adresseMail: String, motDePasse: String): Boolean {
        return dbHelper.insertUtilisateur(nom, prenom, adresseMail, motDePasse)
    }

    fun getUtilisateurById(id: Int): Utilisateur? {
        val cursor = dbHelper.readableDatabase.rawQuery("SELECT * FROM ${DatabaseHelper.TABLE_NAME_UTILISATEUR} WHERE ${DatabaseHelper.COLUMN_ID_UTILISATEUR} = $id", null)
        var utilisateur: Utilisateur? = null
        if (cursor.moveToFirst()) {
            val nom = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NOM))
            val prenom = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PRENOM))
            val adresseMail = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ADRESSE_MAIL))
            val motDePasse = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_MOT_DE_PASSE))
            utilisateur = Utilisateur(id, nom, prenom, adresseMail, motDePasse)
        }
        cursor.close()
        return utilisateur
    }

    fun getUtilisateurByEmail(adresseMail: String): Utilisateur? {
        val cursor = dbHelper.readableDatabase.rawQuery("SELECT * FROM ${DatabaseHelper.TABLE_NAME_UTILISATEUR} WHERE ${DatabaseHelper.COLUMN_ADRESSE_MAIL} = ?", arrayOf(adresseMail))
        var utilisateur: Utilisateur? = null
        if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID_UTILISATEUR))
            val nom = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NOM))
            val prenom = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PRENOM))
            val motDePasse = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_MOT_DE_PASSE))
            utilisateur = Utilisateur(id, nom, prenom, adresseMail, motDePasse)
        }
        cursor.close()
        return utilisateur
    }



}