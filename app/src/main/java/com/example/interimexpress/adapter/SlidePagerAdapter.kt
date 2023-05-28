package com.example.interimexpress.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.interimexpress.R
import com.example.interimexpress.view.activity.CandidatDashboardActivity
import com.example.interimexpress.view.activity.Service

class SlidePagerAdapter(private val services: List<Service>, private val context: Context) : RecyclerView.Adapter<SlidePagerAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.slideTitle)
        val description: TextView = view.findViewById(R.id.slideDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slide_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return services.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val service = services[position]

        holder.title.text = service.title
        holder.description.text = service.description
    }
}