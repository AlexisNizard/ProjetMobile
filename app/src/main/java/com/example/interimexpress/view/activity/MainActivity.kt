package com.example.interimexpress.view.activity

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.interimexpress.R
import com.example.interimexpress.controller.*
import com.google.firebase.FirebaseApp
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var offreController : OffreController
    private lateinit var candidatController: CandidatController
    private lateinit var employeurController: EmployeurController
    private lateinit var postulerController: PostulerController
    private lateinit var agenceController: AgenceController
    private val LOCATION_PERMISSION_REQUEST_CODE = 1
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences("InterimExpress", Context.MODE_PRIVATE)
        val userRole = sharedPreferences.getString("userRole", "")
        if (userRole == "Candidat") {
            val intent = Intent(this, RechercheOffresActivity::class.java)
            startActivity(intent)
            genDB()
        } else if (userRole == "Employeur") {
            val intent = Intent(this, EmployeurDashboardActivity::class.java)
            startActivity(intent)
            genDB()
        }
        else if (userRole == "Agence") {
            val intent = Intent(this, AgenceDashboardActivity::class.java)
            startActivity(intent)
            genDB()
        }
        else {
            setContentView(R.layout.activity_main)

            genDB()

            val btnConnecter = findViewById<Button>(R.id.login_button)
            val btnRegister = findViewById<Button>(R.id.register_button)
            val txtContinueAnonyme = findViewById<TextView>(R.id.continue_anonymously)

            btnConnecter.setOnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }

            btnRegister.setOnClickListener {
                val intent = Intent(this, ChoisirRoleActivity::class.java)
                startActivity(intent)
            }

            txtContinueAnonyme.setOnClickListener {
                val intent = Intent(this, RechercheOffresActivity::class.java)
                startActivity(intent)
            }

            // Request location permission
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
            ) {
                // Permission is not granted
                if (!hasShownRationale()) {
                    // Show an explanation to the user
                    AlertDialog.Builder(this, R.style.CustomAlertDialogStyle)
                        .setTitle("Permission de localisation nécessaire")
                        .setMessage("Cette application a besoin de la permission de localisation pour afficher des recommandations d'offres adaptées à votre localisation.")
                        .setPositiveButton("Accepter") { dialog, which ->
                            setHasShownRationale()
                            ActivityCompat.requestPermissions(
                                this,
                                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                                LOCATION_PERMISSION_REQUEST_CODE
                            )
                        }
                        .setNegativeButton("Refuser") { dialog, which ->
                            dialog.dismiss()
                            setHasShownRationale()
                        }
                        .create().show()
                } else {
                    // No explanation needed; request the permission
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        LOCATION_PERMISSION_REQUEST_CODE
                    )
                }
            } else {
                getLocation()
            }
        }
    }

    private fun genDB(){
        offreController = OffreController()
        candidatController = CandidatController()
        employeurController = EmployeurController()
        postulerController = PostulerController()
        agenceController = AgenceController()

        offreController.checkAndUpdateData()
        candidatController.checkAndUpdateData()
        employeurController.checkAndUpdateData()
        postulerController.checkAndUpdateData()
        agenceController.checkAndUpdateData()
    }

    private fun hasShownRationale(): Boolean {
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return false
        return sharedPref.getBoolean("HAS_SHOWN_RATIONALE", false)
    }

    private fun setHasShownRationale() {
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putBoolean("HAS_SHOWN_RATIONALE", true)
            apply()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permission is granted. Continue with location-related task
                    getLocation()
                } else {
                    // Permission denied. Disable the functionality that depends on this permission
                    Toast.makeText(this, "Permission refusée", Toast.LENGTH_SHORT).show()
                    latitude = 0.0
                    longitude = 0.0
                }
                return
            }
            else -> {
                // Ignore all other requests
            }
        }
    }

    private fun getLocation() {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            val location: Location? = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            // Got last known location. In some rare situations this can be null.
            if (location != null) {
                latitude = location.latitude
                longitude = location.longitude

                latitude =48.866667
                longitude =2.333333
                saveLocationInSharedPrefs(latitude, longitude)
                println("Latitude: $latitude, Longitude: $longitude")
                println("et la ville : ")
                println(getCityName(latitude,longitude))
                println("et l'inverse")
                val montpellierCoordinates = getCoordinates("Montpellier")
                println(montpellierCoordinates)
            }
            else {
                saveLocationInSharedPrefs(0.0, 0.0)  // save default values in SharedPreferences
                println("Location null")
            }
        }
    }


    private fun getCityName(lat: Double, long: Double): String? {
        val geocoder = Geocoder(this, Locale.getDefault())
        val addresses = geocoder.getFromLocation(lat, long, 1)
        return addresses?.get(0)?.locality
    }

    private fun getCoordinates(cityName: String): Pair<Double, Double>? {
        val geocoder = Geocoder(this, Locale.getDefault())
        val addresses = geocoder.getFromLocationName(cityName, 1)
        if (addresses != null && addresses.isNotEmpty()) {
            val latitude = addresses[0]?.latitude
            val longitude = addresses[0]?.longitude
            if (latitude != null && longitude != null) {
                return Pair(latitude, longitude)
            }
        }
        return null
    }

    private fun saveLocationInSharedPrefs(lat: Double, lon: Double) {
        val sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putFloat("LATITUDE", lat.toFloat())
            putFloat("LONGITUDE", lon.toFloat())
            apply()
        }
    }

    companion object {
        fun getLocationFromSharedPrefs(context: Context): Pair<Double, Double> {
            val sharedPref = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val latitude = sharedPref.getFloat("LATITUDE", 0f).toDouble()
            val longitude = sharedPref.getFloat("LONGITUDE", 0f).toDouble()
            return Pair(latitude, longitude)
        }
    }



}
