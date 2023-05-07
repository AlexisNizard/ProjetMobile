package com.example.interimexpress.model

data class Utilisateur(
    val id: Int,
    val nom: String,
    val prenom: String,
    val adresseMail: String,
    val motDePasse: String
)