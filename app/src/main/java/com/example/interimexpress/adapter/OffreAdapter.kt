package com.example.interimexpress.adapter

import android.content.Intent
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.interimexpress.R
import com.example.interimexpress.controller.OffreController
import com.example.interimexpress.model.Offre
import com.example.interimexpress.view.activity.DetailsOffreActivity
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.roundToInt

open class OffreAdapter(var offreList: MutableList<Offre>) : RecyclerView.Adapter<OffreAdapter.OffreViewHolder>() {

    private var offreController = OffreController()

    open inner class OffreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titre: TextView? = view.findViewById(R.id.titre)
        val societe: TextView? = view.findViewById(R.id.societe)
        val adresse: TextView? = view.findViewById(R.id.adresse)
        val contrat: TextView? = view.findViewById(R.id.contrat)
        val remuneration: TextView? = view.findViewById(R.id.remuneration)
        val dateDebutFin: TextView? = view.findViewById(R.id.dateDebutFin)
        val description: TextView? = view.findViewById(R.id.description)
        val datePublic: TextView? = view.findViewById(R.id.date_public)

        init {
            view.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val offreId = offreList[position].id

                    val intent = Intent(view.context, DetailsOffreActivity::class.java)
                    intent.putExtra("offre_id", offreId)
                    view.context.startActivity(intent)

                    // Ici on augmente le compteur de clics de l'offre
                    offreId?.let {
                        offreController.incrementOffreClick(it)
                    }
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.offre_item, parent, false)
        return OffreViewHolder(view)
    }

    override fun onBindViewHolder(holder: OffreViewHolder, position: Int) {
        val offre = offreList[position]
        holder.titre?.text = offre.titre
        holder.societe?.text = offre.entreprise
        val add = offre.adresse
        val cpp = offre.cp
        holder.adresse?.text = "$cpp $add"
        val type = offre.typeContrat
        val typeContratText = "Type de contrat : $type"
        val spannableTypeContrat = SpannableString(typeContratText)
        spannableTypeContrat.setSpan(StyleSpan(Typeface.BOLD), 0, "Type de contrat :".length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        holder.contrat?.text = spannableTypeContrat

        val rem =offre.remuneration?.toFloat()?.roundToInt().toString()
        val remunerationText = "Rémunération : $rem€/mois"
        val spannableRemuneration = SpannableString(remunerationText)
        spannableRemuneration.setSpan(StyleSpan(Typeface.BOLD), 0, "Rémunération :".length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        holder.remuneration?.text = spannableRemuneration

        val dateDebut = offre.dateDebut?.toDate() // Convertir Timestamp en Date
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE) // Format de date
        val dateDebutString = dateFormat.format(dateDebut) // Convertir Date en String

        val dateFin = offre.dateFin?.toDate() // Convertir Timestamp en Date

        val per: String = if (dateFin != null) {
            val dateFinString = dateFormat.format(dateFin) // Convertir Date en String
            "Période : Du $dateDebutString au $dateFinString"
        } else {
            "Période : A partir du $dateDebutString"
        }

        val spannablePer = SpannableString(per)
        spannablePer.setSpan(StyleSpan(Typeface.BOLD), 0, "Période :".length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        holder.dateDebutFin?.text = spannablePer

        holder.description?.text = offre.description

        val dateCreation = offre.dateCreation ?: Timestamp.now()
        val daysSinceCreation = TimeUnit.MILLISECONDS.toDays(
            Timestamp.now().toDate().time - dateCreation.toDate().time)

        holder.datePublic?.text = if (daysSinceCreation == 1L) {
            "Offre publiée depuis moins d'1 jour"
        } else {
            "Offre publiée il y a $daysSinceCreation jours"
        }
    }

    override fun getItemCount() = offreList.size

    open fun updateOffreList(newOffreList: MutableList<Offre>) {
        this.offreList.clear()
        this.offreList.addAll(newOffreList)
        notifyDataSetChanged()
    }

}
