package com.example.interimexpress.model

data class Postuler(
    var id: String? = null,
    var userId: String? = null,
    var employerId: String? = null,
    var offerId: String? = null,
    var nom: String? = null,
    var prenom: String? = null,
    var dateNaissance: String? = null,
    var nationalite: String? = null,
    var cv: String? = null,
    var lm: String? = null,
    var traite:Int? = 0,
    var accept:Int? = 0
)