package com.example.interimexpress.view.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.example.interimexpress.R
import com.example.interimexpress.controller.OffreController
import com.example.interimexpress.databinding.ActivityDetailsOffreBinding
import com.example.interimexpress.model.Offre
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.FirebaseFirestore
import com.google.cloud.translate.Translate
import com.google.cloud.translate.TranslateOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope
import com.example.interimexpress.controller.FavoriController
import com.example.interimexpress.model.Favori

class DetailsOffreActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailsOffreBinding
    private lateinit var offreController: OffreController
    private lateinit var favoriController: FavoriController
    private lateinit var sharedPreferences: SharedPreferences
    private var bool_v=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsOffreBinding.inflate(layoutInflater)
        setContentView(binding.root)


        favoriController = FavoriController()
        sharedPreferences = getSharedPreferences("InterimExpress", Context.MODE_PRIVATE)

        val userRole = sharedPreferences.getString("userRole", "")
        //println(userRole)
        if (userRole == "Candidat") {

        } else {
            masquer_vue_anonyme();
        }


        binding.logo.setOnClickListener {
            val intent = Intent(this, CandidatDashboardActivity::class.java)
            startActivity(intent)
        }

        offreController = OffreController()

        ya_t_il_lien()

        val id : String = intent.getStringExtra("offre_id") ?: "1" // Si aucun extra "offre_id" n'est trouvé, "1" sera utilisé par défaut.
        readData(id)


        binding.rechercherButton.setOnClickListener {
            if (userRole == "Candidat") {
                val intent = Intent(this, PostulerOffreActivity::class.java)
                intent.putExtra("offre_id", id)
                startActivity(intent)
            } else {
                faire_apparaitre_co_inscr();
            }
        }

        binding.rechercherButton1.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.rechercherButton2.setOnClickListener {
            val intent = Intent(this, CandidatRegisterActivity::class.java)
            startActivity(intent)
        }

        binding.rechercherOffreSimi.setOnClickListener{
            addRechAnnSimi()
        }

        /*binding.image2.setOnClickListener {
            lifecycleScope.launch {
                val translatedTitle = translateText("en", binding.titre.text.toString())
                val translatedEntreprise = translateText("en", binding.titre2.text.toString())
                val translatedAdresse = translateText("en", binding.titre3.text.toString())
                val translatedDescription = translateText("en", binding.titre4.text.toString())

                binding.titre.text = translatedTitle
                binding.titre2.text = translatedEntreprise
                binding.titre3.text = translatedAdresse
                binding.titre4.text = translatedDescription
            }
        }*/

        val userMail = sharedPreferences.getString("userMail", "")
        favoriController.isFavori(userMail!!, id).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val documents = task.result?.documents
                if (!documents.isNullOrEmpty()) {
                    binding.image3.setImageResource(R.drawable.baseline_favorite_24_red)
                    binding.image3.tag = documents[0].id
                }
            } else {
                Log.e("DetailsOffreActivity", "Erreur lors de la vérification des favoris", task.exception)
            }
        }

        binding.image3.setOnClickListener{
            add_fav()
        }

        binding.image4.setOnClickListener{
            show_fav()
        }

        binding.imageTwitter.setOnClickListener{
            // Récupérer le texte des TextViews
            val titre = binding.titre.text.toString()
            val titre2 = binding.titre2.text.toString()
            val titre3 = binding.titre3.text.toString()
            val titre4 = binding.titre4.text.toString()

            // Créer le message à partager
            val message = "$titre\n$titre2\n$titre3\n$titre4"

            val url = "https://twitter.com/intent/tweet?text=$message"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        binding.imageSMS.setOnClickListener{
            // Récupérer le texte des TextViews
            val titre = binding.titre.text.toString()
            val titre2 = binding.titre2.text.toString()
            val titre3 = binding.titre3.text.toString()
            val titre4 = binding.titre4.text.toString()

            // Créer le message à partager
            val message = "$titre\n$titre2\n$titre3\n$titre4"

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:"))
            intent.putExtra("sms_body", message)
            startActivity(intent)
        }

        /*Polique de confidentialité , contenu pré-rempli impossible
        binding.imageFacebook.setOnClickListener {
            val url = "https://www.facebook.com/sharer/sharer.php?u=YOUR_URL_TO_SHARE"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }*/

        binding.imageLinkedin.setOnClickListener {
            // Récupérer le texte des TextViews
            val titre = binding.titre.text.toString()
            val titre2 = binding.titre2.text.toString()
            val titre3 = binding.titre3.text.toString()
            val titre4 = binding.titre4.text.toString()

            // Créer le message à partager
            val message = "$titre\n$titre2\n$titre3\n$titre4"
            val url = "https://www.linkedin.com/sharing/share-offsite/?url=$message"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }


        binding.sendButton.setOnClickListener {
            offreController.getOffre(id).addOnSuccessListener { document ->
                if (document != null) {
                    val offre = document.toObject(Offre::class.java)
                    if (offre != null) {
                        // Création d'un Intent pour passer à RechercheOffresActivity
                        val intent = Intent(this, RechercheOffresActivity::class.java)

                        // Ajout des extras à l'intent seulement si les chips correspondantes sont cochées
                        if (binding.chipMetier.isChecked) {
                            intent.putExtra("metier", offre.titre)
                        }
                        if (binding.chipEmployeur.isChecked) {
                            intent.putExtra("employeur", offre.entreprise)
                        }
                        if (binding.chipPeriode.isChecked) {
                            intent.putExtra("periodeDebut", offre.dateDebut)
                            intent.putExtra("periodeFin", offre.dateFin)
                        }
                        if (binding.chipLieux.isChecked) {
                            intent.putExtra("lieu", offre.adresse)
                        }

                        startActivity(intent)
                    }
                }
            }
        }



    }

    private fun ya_t_il_lien(){
        val idOffre = intent.getStringExtra("offre_id") ?: "1"
        val image1: ImageView = findViewById(R.id.image1)

        offreController.getOffre(idOffre).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val offre = task.result?.toObject(Offre::class.java)
                if (offre != null && !offre.lienOffre.isNullOrEmpty()) {
                    image1.visibility = View.VISIBLE
                    image1.setOnClickListener {
                        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(offre.lienOffre))
                        startActivity(browserIntent)
                    }
                }
            } else {
                Log.e("DetailsOffreActivity", "Erreur lors de la récupération de l'offre", task.exception)
            }
        }
    }

    private fun show_fav(){

        val parterRS: ConstraintLayout = findViewById(R.id.parterRS)
        if (bool_v==0){
            parterRS.visibility = View.VISIBLE
            bool_v++
        }else{
            parterRS.visibility = View.GONE
            bool_v--
        }

    }

    private fun add_fav(){
        val userMail = sharedPreferences.getString("userMail", "")
        val idOffre = intent.getStringExtra("offre_id") ?: "1"

        favoriController.isFavori(userMail!!, idOffre).addOnSuccessListener { documents ->
            if (documents.isEmpty) {
                // L'offre n'est pas encore un favori
                val favori = Favori(userMail, idOffre)
                favoriController.addFavori(favori)
                binding.image3.setImageResource(R.drawable.baseline_favorite_24_red)
                binding.image3.tag = idOffre
            } else {
                // L'offre est déjà un favori, donc on la supprime
                val favoriId = documents.documents[0].id
                favoriController.removeFavori(favoriId)
                binding.image3.setImageResource(R.drawable.baseline_favorite_border_24)
                binding.image3.tag = ""
            }
        }.addOnFailureListener { exception ->
            Log.e("DetailsOffreActivity", "Erreur lors de la vérification des favoris", exception)
        }
    }





    private fun masquer_vue_anonyme(){
        val logo: ImageView = findViewById(R.id.logo)
        logo.visibility = View.GONE
        val imageView: ImageView = findViewById(R.id.imageView)
        imageView.visibility = View.GONE

        val image3 : ImageView = findViewById(R.id.image3)
        image3.visibility = View.GONE
        // Get an instance of the fragment
        val footerFragment = supportFragmentManager.findFragmentById(R.id.footerFragment)

        // Hide the fragment
        if (footerFragment != null) {
            supportFragmentManager.beginTransaction().hide(footerFragment).commit()
        }
    }

    private fun addRechAnnSimi() {
        val chipGroupContainer: ConstraintLayout = findViewById(R.id.chipGroupContainer)
        val sendButton: Button = findViewById(R.id.sendButton)

        chipGroupContainer.visibility = View.VISIBLE
        sendButton.visibility = View.VISIBLE
    }

    private fun faire_apparaitre_co_inscr(){
        val veuillez_identif: TextView = findViewById(R.id.veuillez_identif)
        veuillez_identif.visibility = View.VISIBLE


        val rechercher_button1: Button = findViewById(R.id.rechercher_button1)
        rechercher_button1.visibility = View.VISIBLE

        val rechercher_button2: Button = findViewById(R.id.rechercher_button2)
        rechercher_button2.visibility = View.VISIBLE

        val rechercher_button: Button = findViewById(R.id.rechercher_button)
        rechercher_button.visibility = View.GONE


        val constraintLayout1: ConstraintLayout = findViewById(R.id.parent_constr)
        val constraintSet1 = ConstraintSet()
        constraintSet1.clone(constraintLayout1)

        constraintSet1.connect(R.id.veuillez_identif, ConstraintSet.TOP, R.id.titre4, ConstraintSet.BOTTOM)
        constraintSet1.applyTo(constraintLayout1)

    }

    private fun readData(id: String) {
        offreController.getOffre(id).addOnSuccessListener { document ->
            if (document != null) {
                val titre = document.getString("titre")
                val entreprise = document.getString("entreprise")
                val adresse = document.getString("adresse")
                val description = document.getString("description")

                binding.titre.text = titre
                binding.titre2.text = entreprise
                binding.titre3.text = adresse
                binding.titre4.text = description

            } else {
                Toast.makeText(this,"Echec, la bdd ne reconnait pas l'id ",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener { e ->
            Toast.makeText(this,"Erreur avec la bdd",Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun translateText(targetLanguage: String, text: String, context: CoroutineContext = Dispatchers.Default): String = withContext(context) {
        val options = TranslateOptions.newBuilder().setApiKey("AIzaSyBADToY3UnbvIfZfSLxUJABaRSafYlQCnE").build()
        val translate = options.service
        val translation = translate.translate(text, Translate.TranslateOption.targetLanguage(targetLanguage))
        translation.translatedText
    }

}
