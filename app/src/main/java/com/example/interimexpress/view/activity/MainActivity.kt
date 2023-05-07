package com.example.interimexpress.view.activity


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.example.interimexpress.R
import com.example.interimexpress.model.DatabaseManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class MainActivity : AppCompatActivity() {

    private lateinit var databaseManager: DatabaseManager

    /*private val LOCATION_PERMISSION_REQUEST_CODE = 1
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var lastLocation: Location? = null*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*checkLocationPermission()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)*/


        databaseManager = DatabaseManager.getInstance(this)

        // Insertion les données initiales dans la base de données
        databaseManager.insertInitialData()

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

    }

    /*private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                showPermissionExplanationDialog()
            } else {
                requestLocationPermission()
            }
        } else {
            getLastLocation()
        }
    }

    private fun showPermissionExplanationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Permission de localisation requise")
            .setMessage("L'application a besoin de votre localisation pour afficher des annonces autour de vous.")
            .setPositiveButton("OK") { _, _ ->
                requestLocationPermission()
            }
            .create()
            .show()
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // La permission de localisation a été accordée
                    getLastLocation()
                } else {
                    // La permission a été refusée, vous pouvez montrer un message d'erreur
                }
            }
        }
    }

    private fun getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                lastLocation = it
                // Vous pouvez utiliser la variable lastLocation pour accéder à la localisation de l'utilisateur
            }
        }
    }*/

}