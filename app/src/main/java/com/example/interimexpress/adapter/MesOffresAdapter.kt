package com.example.interimexpress.adapter

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.interimexpress.R
import com.example.interimexpress.controller.OffreController
import com.example.interimexpress.model.Offre
import com.example.interimexpress.view.activity.EditOffreActivity
import com.example.interimexpress.view.activity.EmployeurDashboardActivity

class MesOffresAdapter(offreList: MutableList<Offre>) : OffreAdapter(offreList) {

    private val offreController = OffreController()

    // Surchargez OffreViewHolder pour MesOffresAdapter.
    inner class MesOffresViewHolder(view: View) : OffreAdapter.OffreViewHolder(view) {
        private val editImageView: ImageView = view.findViewById(R.id.edit)
        private val deleteImageView: ImageView = view.findViewById(R.id.del)
        init {
            // Supprimez le OnClickListener pour le ConstraintLayout.
            view.setOnClickListener(null)

            // Ajoutez un OnClickListener pour l'ImageView edit.
            editImageView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val offreId = offreList[position].id
                    val intent = Intent(view.context, EditOffreActivity::class.java)
                    intent.putExtra("offre_id", offreId)
                    view.context.startActivity(intent)
                }
            }

            // Ajoutez un OnClickListener pour l'ImageView delete.
            deleteImageView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val offreId = offreList[position].id

                    // Créez une boîte de dialogue pour confirmer la suppression.
                    AlertDialog.Builder(view.context)
                        .setTitle("Suppression de l'offre")
                        .setMessage("Êtes-vous sûr de vouloir supprimer cette offre ?")
                        .setPositiveButton("Oui") { dialog, _ ->
                            offreId?.let { id ->
                                // Supprimez l'offre de la base de données.
                                offreController.deleteOffre(id)
                                // Supprimez l'offre de la liste et notifiez l'adapter.
                                offreList.removeAt(position)
                                notifyItemRemoved(position)
                            }
                            dialog.dismiss()
                        }
                        .setNegativeButton("Non") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.offre_item3, parent, false)
        println(offreList)
        return MesOffresViewHolder(view)
    }

}
