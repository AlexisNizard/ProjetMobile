package com.example.interimexpress.model

data class Candidat(
    var adresseMail: String? = null,
    var nom: String? = null,
    var prenom: String? = null,
    var motDePasse: String? = null,
    var role: String? = null,
    var nationalite: String? = null,
    var numTelephone: String? = null,
    var dateNaissance: String? = null
)