package com.example.interimexpress.model
import com.example.interimexpress.databinding.SpinnerRemunerationBinding
import com.google.firebase.Timestamp

data class Offre(
    var id: String? = null,
    var titre: String? = null,
    var entreprise: String? = null,
    var adresse: String? = null,
    var cp: String? = null,
    var mail: String? = null,
    var typeContrat: String? = null,
    var remuneration: Double? = null,
    var dateDebut: Timestamp? = null,
    var dateFin: Timestamp? = null,
    var description: String? = null,
    var dateCreation: Timestamp? = null,
    var nbrClick: Int = 0
)


