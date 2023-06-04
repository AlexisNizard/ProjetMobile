package com.example.interimexpress.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.interimexpress.R
import com.example.interimexpress.controller.GestionnaireController
import com.example.interimexpress.model.Gestionnaire


class MesGestionnairesAdapter(private val gestionnaires: MutableList<Gestionnaire>, private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<MesGestionnairesAdapter.GestionnaireViewHolder>() {

    interface OnItemClickListener {
        fun onDeleteClick(gestionnaire: Gestionnaire)
    }

    inner class GestionnaireViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val mailGestio: TextView = view.findViewById(R.id.mailGestio)
        val del: ImageView = view.findViewById(R.id.del)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GestionnaireViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mes_gestionnaires, parent, false)
        return GestionnaireViewHolder(view)
    }

    override fun onBindViewHolder(holder: GestionnaireViewHolder, position: Int) {
        val gestionnaire = gestionnaires[position]
        holder.mailGestio.text = gestionnaire.adresseMail
        holder.del.setOnClickListener {
            itemClickListener.onDeleteClick(gestionnaire)
        }
    }

    fun removeItem(gestionnaire: Gestionnaire) {
        val position = gestionnaires.indexOf(gestionnaire)
        if (position != -1) {
            gestionnaires.removeAt(position)
            notifyItemRemoved(position)
        }
    }


    override fun getItemCount() = gestionnaires.size
}
