package com.example.interimexpress.model
import com.google.firebase.Timestamp

data class Offre(
    var id: String? = null,
    var titre: String? = null,
    var entreprise: String? = null,
    var adresse: String? = null,
    var description: String? = null,
    var dateCreation: Timestamp? = null
)


