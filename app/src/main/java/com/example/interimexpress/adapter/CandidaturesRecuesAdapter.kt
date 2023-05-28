package com.example.interimexpress.adapter

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.interimexpress.R
import com.example.interimexpress.controller.OffreController
import com.example.interimexpress.model.Offre
import com.example.interimexpress.view.activity.AccpRefCandidature
import com.example.interimexpress.view.activity.EditOffreActivity
import com.example.interimexpress.view.activity.EmployeurDashboardActivity
import com.example.interimexpress.view.activity.MainActivity
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.roundToInt

class CandidaturesRecuesAdapter (offreList: MutableList<Offre>) : OffreAdapter(offreList) {
    private val offreController = OffreController()

    // Surchargez OffreViewHolder pour MesOffresAdapter.
    inner class MesOffresViewHolder(view: View) : OffreAdapter.OffreViewHolder(view) {

        init {
            // Supprimez le OnClickListener pour le ConstraintLayout.
            view.setOnClickListener(null)

        }
    }

    override fun onBindViewHolder(holder: OffreViewHolder, position: Int) {
        val offre = offreList[position]
        holder.titre?.text = offre.titre

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, AccpRefCandidature::class.java)
            intent.putExtra("postulerId", offre.id)  // pass postulerId
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.candid_item, parent, false)
        println(offreList)
        return MesOffresViewHolder(view)
    }
}