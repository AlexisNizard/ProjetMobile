package com.example.interimexpress.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.interimexpress.R

class NationalitySpinnerAdapter(context: Context, private val nationalities: Array<String>)
    : ArrayAdapter<String>(context, R.layout.spinner_item, nationalities) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.spinner_item, parent, false)
        val nationalityText = view.findViewById<TextView>(R.id.nationality_text)
        nationalityText.text = nationalities[position]
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.spinner_dropdown_item2, parent, false)
        val nationalityText = view.findViewById<TextView>(R.id.nationality_text)
        nationalityText.text = nationalities[position]
        return view
    }
}
