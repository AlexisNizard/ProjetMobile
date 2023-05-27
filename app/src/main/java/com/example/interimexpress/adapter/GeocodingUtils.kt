package com.example.interimexpress.adapter

import android.content.Context
import android.location.Geocoder
import java.util.Locale

class GeocodingUtils(private val context: Context) {

    fun getCityName(lat: Double, long: Double): String? {
        // Si les coordonnées sont à 0,0, on renvoie null pour indiquer qu'aucune ville n'a été trouvée.
        if (lat == 0.0 && long == 0.0) {
            return null
        }
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses = geocoder.getFromLocation(lat, long, 1)
        return addresses?.get(0)?.locality
    }
}