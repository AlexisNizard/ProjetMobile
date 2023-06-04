package com.example.interimexpress.model

import java.io.Serializable

data class Employeur(
    var adresseMail: String? = null,
    var nomEntreprise: String? = null,
    var nomService: String? = null,
    var nomSousService: String? = null,
    var numeroSiret: String? = null,
    var nomContact1: String? = null,
    var prenomContact1: String? = null,
    var nomContact2: String? = null,
    var prenomContact2: String? = null,
    var adresseMail2: String? = null,
    var motDePasse: String? = null,
    var role: String? = null,
    var numTelephone1: String? = null,
    var numTelephone2: String? = null,
    var adresse: String? = null,
    var codePostal: String? = null,
    var ville: String? = null,
    var lienSiteWeb: String? = null,
    var lienLinkedin: String? = null,
    var lienFacebook: String? = null,
    var valide: Int = 0,
    var repondu: Int=0
)  : Serializable
