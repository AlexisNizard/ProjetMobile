package com.example.interimexpress.model

data class Utilisateur(
    var id: String? = null,
    var nom: String? = null,
    var prenom: String? = null,
    var adresseMail: String? = null,
    var motDePasse: String? = null
)