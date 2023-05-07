package com.example.interimexpress.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "OffresDB"
        private const val DATABASE_VERSION = 3

        // Table Offre
        const val TABLE_NAME = "offre"
        const val COLUMN_ID = "idOffre"
        const val COLUMN_TITRE = "titre"
        const val COLUMN_ENTREPRISE = "entreprise"
        const val COLUMN_ADRESSE = "adresse"
        const val COLUMN_DESCRIPTION = "description"

        // Table Utilisateur
        const val TABLE_NAME_UTILISATEUR = "utilisateur"
        const val COLUMN_ID_UTILISATEUR = "idUtilisateur"
        const val COLUMN_NOM = "nom"
        const val COLUMN_PRENOM = "prenom"
        const val COLUMN_ADRESSE_MAIL = "adresseMail"
        const val COLUMN_MOT_DE_PASSE = "motDePasse"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Création de la table Offre
        val createTable =
            "CREATE TABLE $TABLE_NAME (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_TITRE TEXT, " +
                    "$COLUMN_ENTREPRISE TEXT, " +
                    "$COLUMN_ADRESSE TEXT, " +
                    "$COLUMN_DESCRIPTION TEXT)"
        db.execSQL(createTable)

        // Création de la table Utilisateur
        val createTableUtilisateur = "CREATE TABLE $TABLE_NAME_UTILISATEUR (" +
                "$COLUMN_ID_UTILISATEUR INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_NOM TEXT, " +
                "$COLUMN_PRENOM TEXT, " +
                "$COLUMN_ADRESSE_MAIL TEXT, " +
                "$COLUMN_MOT_DE_PASSE TEXT)"
        db.execSQL(createTableUtilisateur)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Mise à jour de la table Offre et Utilisateur
        if (newVersion > oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_UTILISATEUR")
            onCreate(db)
        }
    }

    fun insertData(titre: String, entreprise: String, adresse: String, description: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_TITRE, titre)
        contentValues.put(COLUMN_ENTREPRISE, entreprise)
        contentValues.put(COLUMN_ADRESSE, adresse)
        contentValues.put(COLUMN_DESCRIPTION, description)
        val result = db.insert(TABLE_NAME, null, contentValues)
        return result != -1L
    }

    fun insertUtilisateur(nom: String, prenom: String, adresseMail: String, motDePasse: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_NOM, nom)
        contentValues.put(COLUMN_PRENOM, prenom)
        contentValues.put(COLUMN_ADRESSE_MAIL, adresseMail)
        contentValues.put(COLUMN_MOT_DE_PASSE, motDePasse)

        val result = db.insert(TABLE_NAME_UTILISATEUR, null, contentValues)
        return result != -1L
    }
}
