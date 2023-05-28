package com.example.interimexpress.view.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.interimexpress.R
import com.example.interimexpress.controller.PostulerController
import com.example.interimexpress.model.Postuler
import okhttp3.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

import java.text.SimpleDateFormat
import java.util.*

class AccpRefCandidature : AppCompatActivity() {

    private lateinit var postulerController: PostulerController
    private var postuler: Postuler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accp_ref_candidature)

        val imgProfil = findViewById<ImageView>(R.id.logo)
        imgProfil.setOnClickListener {
            val intent = Intent(this, EmployeurDashboardActivity::class.java)
            startActivity(intent)
        }

        postulerController = PostulerController()
        val id : String = intent.getStringExtra("postulerId") ?: "-1"
        if (id != "-1") {
            // Fetch postuler data from Firestore
            postulerController.getPostuler(id)
                .addOnSuccessListener { documentSnapshot ->
                    postuler = documentSnapshot.toObject(Postuler::class.java)
                    postuler?.let { populateData(it) }
                }
                .addOnFailureListener { exception ->
                    Log.e("AccpRefCandidature", "Error fetching postuler data", exception)
                }
        }

        val btnCv = findViewById<Button>(R.id.cv_c)
        val btnLm = findViewById<Button>(R.id.lm_c)

        val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        ActivityCompat.requestPermissions(this, permissions,0)


        val cvButton = findViewById<Button>(R.id.cv_c)
        cvButton.setOnClickListener {
            postuler?.let { it.cv?.let { it1 -> downloadFile(it1, "cv.pdf") } }
        }

        val lmButton = findViewById<Button>(R.id.lm_c)
        lmButton.setOnClickListener {
            postuler?.let { it.lm?.let { it1 -> downloadFile(it1, "lm.pdf") } }
        }


        val btnAc = findViewById<Button>(R.id.btn_acc)
        btnAc.setOnClickListener {
            postuler?.id?.let { it1 -> postulerController.acceptPostuler(it1) }
            val sharedPreferences = getSharedPreferences("InterimExpress", Context.MODE_PRIVATE)
            sharedPreferences.edit().putBoolean("hasUntreatedPostuler", false).apply()
            val intent = Intent(this, GererCandidatureActivity::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(
                this,
                "La candidature à été accepté avec succès.",
                Toast.LENGTH_LONG
            ).show()
        }

        val btn_ref = findViewById<Button>(R.id.btn_ref)
        btn_ref.setOnClickListener {
            postuler?.id?.let { it1 -> postulerController.refusePostuler(it1) }
            val sharedPreferences = getSharedPreferences("InterimExpress", Context.MODE_PRIVATE)
            sharedPreferences.edit().putBoolean("hasUntreatedPostuler", false).apply()
            val intent = Intent(this, GererCandidatureActivity::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(
                this,
                "La candidature à été refusé avec succès.",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    private fun populateData(postuler: Postuler) {
        val txtNom = findViewById<TextView>(R.id.nom_c)
        val txtAge = findViewById<TextView>(R.id.age_c)
        val txtNat = findViewById<TextView>(R.id.nat_c)

        txtNom.text = "${postuler.nom} ${postuler.prenom}"
        txtAge.text = "${calculateAge(postuler.dateNaissance)}"
        txtNat.text = "${postuler.nationalite}"
    }

    private fun calculateAge(dateNaissance: String?): String {
        if (dateNaissance == null) return ""

        val dob = SimpleDateFormat("MM/dd/yyyy").parse(dateNaissance)
        val dobCalendar = Calendar.getInstance()
        dobCalendar.time = dob

        val currentDate = Calendar.getInstance()
        var age = currentDate.get(Calendar.YEAR) - dobCalendar.get(Calendar.YEAR)
        if (currentDate.get(Calendar.MONTH) < dobCalendar.get(Calendar.MONTH) ||
            (currentDate.get(Calendar.MONTH) == dobCalendar.get(Calendar.MONTH) && currentDate.get(Calendar.DAY_OF_MONTH) < dobCalendar.get(Calendar.DAY_OF_MONTH))) {
            age--
        }
        return age.toString()
    }



    fun downloadFile(url: String, filename: String) {
        val request = Request.Builder().url(url).build()
        OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle the error
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val fos = FileOutputStream(File(getExternalFilesDir(null), filename))
                    fos.write(response.body?.bytes())
                    fos.close()
                } else {
                    // Handle the error
                }
            }

        })

    }




}
