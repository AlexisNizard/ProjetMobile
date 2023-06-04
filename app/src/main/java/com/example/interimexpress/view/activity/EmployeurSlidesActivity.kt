package com.example.interimexpress.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.res.ResourcesCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.interimexpress.R
import com.example.interimexpress.adapter.SlidePagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.example.interimexpress.view.activity.CandidatDashboardActivity


class EmployeurSlidesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employeur_slides)

        val services = listOf(
            Service("Gérer des offres", "Vous pourrez d'abord avoir la possibilité de déposer une offre d’emploi.\n\nVous pourrez ensuite la consulter, la modifier ou bien la supprimer des offres déposées.\n\nVous pourrez également créer vos propres catégories d’offres et déplacer chacune de vos offres dans ces catégories."),
            Service("Gérer les candidatures non traitées", "Vous pourrez consulter les candidatures déposées par des chercheurs d'emploi à vos différents offres.\n\nPuis accepter, refuser, répondre à chacune de ces candidatures"),
            Service("Gérer les candidatures acceptées", "Vous pourrez consulter chacune des candidatures que vous aurez accepté et contacter chaque candidat (par mail, téléphone ou sms)"),
            Service("Bloquer ou Signaler un candidat", "Vous pourrez égalemnt bloquer ou signaler un candidat jugé indésirable selon vous")
        )

        val button = findViewById<Button>(R.id.btnContinuer)
        button.setOnClickListener {
            // Lancez ici l'activité suivante
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val tabLayout = findViewById<TabLayout>(R.id.tabDots)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val adapter = SlidePagerAdapter(services,this)
        viewPager.adapter = adapter

        val tabMediator = TabLayoutMediator(tabLayout, viewPager) { _, _ ->
        }.attach()

        val indicateurSelectionne = ResourcesCompat.getDrawable(resources, R.drawable.indicateur_selectionne, null)
        val indicateurNonSelectionne = ResourcesCompat.getDrawable(resources, R.drawable.indicateur_nonselectionne, null)

        for (i in 0 until tabLayout.tabCount) {
            val tab = tabLayout.getTabAt(i)
            tab?.icon = if (i == 0) indicateurSelectionne else indicateurNonSelectionne
        }

        val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                for (i in 0 until tabLayout.tabCount) {
                    val tab = tabLayout.getTabAt(i)
                    tab?.icon = if (i == position) indicateurSelectionne else indicateurNonSelectionne
                }
            }
        }

        // Ajoutez le callback au ViewPager
        viewPager.registerOnPageChangeCallback(pageChangeCallback)
    }
}

data class Service(val title: String, val description: String)