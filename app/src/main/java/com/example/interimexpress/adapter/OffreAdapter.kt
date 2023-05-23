package com.example.interimexpress.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.interimexpress.R
import com.example.interimexpress.model.Offre
import com.example.interimexpress.view.activity.DetailsOffreActivity
import com.google.firebase.Timestamp
import java.util.concurrent.TimeUnit

class OffreAdapter(private var offreList: List<Offre>) : RecyclerView.Adapter<OffreAdapter.OffreViewHolder>() {

    inner class OffreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titre: TextView = view.findViewById(R.id.titre)
        val societe: TextView = view.findViewById(R.id.societe)
        val adresse: TextView = view.findViewById(R.id.adresse)
        val description: TextView = view.findViewById(R.id.description)
        val datePublic: TextView = view.findViewById(R.id.date_public)

        init {
            view.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val intent = Intent(view.context, DetailsOffreActivity::class.java)
                    intent.putExtra("offre_id", offreList[position].id) // Remplacez `.id` par la manière dont vous accédez à l'id de l'offre.
                    view.context.startActivity(intent)
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
        holder.titre.text = offre.titre
        holder.societe.text = offre.entreprise
        holder.adresse.text = offre.adresse
        holder.description.text = offre.description

        val dateCreation = offre.dateCreation ?: Timestamp.now()
        val daysSinceCreation = TimeUnit.MILLISECONDS.toDays(
            Timestamp.now().toDate().time - dateCreation.toDate().time)

        holder.datePublic.text = if (daysSinceCreation == 0L) {
            "Offre publiée depuis moins d'1 jour"
        } else {
            "Offre publiée il y a $daysSinceCreation jours"
        }
    }

    override fun getItemCount() = offreList.size
}
