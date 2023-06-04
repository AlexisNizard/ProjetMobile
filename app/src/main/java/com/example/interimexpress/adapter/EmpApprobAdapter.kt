package com.example.interimexpress.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.interimexpress.R
import com.example.interimexpress.model.Employeur
import com.example.interimexpress.view.activity.AccpRefInscrEmpActivity


class EmpApprobAdapter(private val gestionnaires: MutableList<Employeur>) : RecyclerView.Adapter<EmpApprobAdapter.GestionnaireViewHolder>() {

    inner class GestionnaireViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val titre: TextView = view.findViewById(R.id.titre)
        val societe: TextView = view.findViewById(R.id.societe)

        init {
            view.setOnClickListener {
                val position: Int = adapterPosition
                val employeur = gestionnaires[position]

                val intent = Intent(view.context, AccpRefInscrEmpActivity::class.java)
                intent.putExtra("emailEmployeur", employeur.adresseMail)
                view.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GestionnaireViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.candid_item, parent, false)
        return GestionnaireViewHolder(view)
    }

    override fun onBindViewHolder(holder: GestionnaireViewHolder, position: Int) {
        val gestionnaire = gestionnaires[position]
        holder.titre.text = gestionnaire.adresseMail
        holder.societe.text = "Accéder au profil"
    }

    fun removeItem(gestionnaire: Employeur) {
        val position = gestionnaires.indexOf(gestionnaire)
        if (position != -1) {
            gestionnaires.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount() = gestionnaires.size
}

