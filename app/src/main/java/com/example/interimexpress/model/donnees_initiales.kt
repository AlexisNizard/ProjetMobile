package com.example.interimexpress.model

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*

object InitialData {

    const val VERSION = 6

    private val sdf = SimpleDateFormat("dd/MM/yyyy")
    private val date = sdf.parse("01/02/2022")
    private val timestamp = Timestamp(date)

    val offres = listOf(
        Offre(
            "1",
            "Alternance Développeur web - Montpellier (F/H)",
            "Capgemini",
            "34000 Montpellier",
            "Capgemini accompagne les entreprises pour faire de leurs infrastructures un accélérateur de transformation digitale. Du conseil à la conception, de la réalisation à la production, nous offrons à nos clients une gamme complète de services combinant sécurité, évolutivité, optimisation et innovation.\n" +
                    "\n" +
                    "Tu souhaites contribuer à une vision dynamique des services d’infrastructure ? L’idée d’intégrer des équipes motivées, soudées et tournées vers les technologies de demain te fait envie ? Alors rejoint dès aujourd’hui l’entité Cloud Infrastructure Services.\n" +
                    "\n" +
                    "\n" +
                    "Ton rôle et tes compétences\n" +
                    "\n" +
                    "En tant qu’ingénieur DevOps junior, tu auras pour missions :\n" +
                    "\n" +
                    "Rechercher et trouver des solutions avec les produits du marché pour les présenter aux clients\n" +
                    "Automatiser nos tâches à l’aide du scripting\n" +
                    "CI/CD\n" +
                    "Identifier les standards et référentiels de sécurité pour concevoir et rédiger des architectures\n" +
                    "Installer et configurer l’infrastructure de nos clients\n" +
                    "Rédiger les livrables des activités précédentes sur les outils bureautiques\n" +
                    "Rédiger des procédures techniques\n" +
                    "\n" +
                    "Tes compétences\n" +
                    "\n" +
                    "Titulaire d’un BAC+3 à BAC+5, tu es autonome, impliqué(e) et tu as l’esprit d’équipe ?\n" +
                    "\n" +
                    "Alors tu es LE/LA candidat(e) idéal(e) pour ce poste !\n" +
                    "\n" +
                    "\n" +
                    "Les avantages à nous rejoindre\n" +
                    "\n" +
                    "Une ambiance de travail dynamique et conviviale\n" +
                    "Un CSE offrant de nombreux avantages (vacances, chèques cadeaux, titres restaurant…)\n" +
                    "Une plateforme de formations en e-learning complète et diversifiée\n" +
                    "Un Career manager qui vous accompagne tout au long de votre évolution\n" +
                    "Et pour votre intégration, nous laissons nos collaborateurs vous en parler.\n" +
                    "\n" +
                    "Offre à pourvoir sur Aix/Marseille, Nice, Montpellier et Bagnols-sur-Cèze.\n" +
                    "\n" +
                    "https://www.capgemini.com/fr-fr/carrieres-ancien/le-recrutement-et-l-integration-chez-capgemini/\n" +
                    "\n" +
                    "Capgemini, entreprise handi accueillante, conformément à la norme AFNOR NF X50-783, est également signataire de la charte de la diversité en entreprise.\n" +
                    "\n" +
                    "\n" +
                    "Alors convaincu ?\n" +
                    "\n" +
                    "Postule vite et rejoins-nous !",
            timestamp
        ),
        Offre(
            "2",
            "Sapien eu ligula laoreet, vel molestie dolor egestas (F/H)",
            "Euismod porttitor",
            "34000 Montpellier",
            "Aliquam scelerisque interdum ante interdum consectetur. Curabitur nec ante sed massa luctus consectetur sit amet scelerisque magna. Curabitur porta tempor massa, id gravida odio viverra non. Pellentesque convallis sollicitudin neque, eget iaculis erat. Aenean eleifend ante non nisi dictum vulputate. Nam mauris ante, faucibus at massa tincidunt, fermentum imperdiet risus. Integer laoreet nunc ut luctus condimentum. Integer lobortis felis vitae egestas dapibus",
            timestamp
        ),
        Offre(
            "3",
            "Etiam non lobortis massa. Ut ac euismod lectus (F/H)",
            "Euismod porttitor",
            "34000 Montpellier",
            "Aliquam scelerisque interdum ante interdum consectetur. Curabitur nec ante sed massa luctus consectetur sit amet scelerisque magna. Curabitur porta tempor massa, id gravida odio viverra non. Pellentesque convallis sollicitudin neque, eget iaculis erat. Aenean eleifend ante non nisi dictum vulputate. Nam mauris ante, faucibus at massa tincidunt, fermentum imperdiet risus. Integer laoreet nunc ut luctus condimentum. Integer lobortis felis vitae egestas dapibus",
            timestamp
        ),
        Offre(
            "4",
            "Fermentum. Mauris sed lectus at purus pulvinar accumsan (F/H)",
            "Euismod porttitor",
            "34000 Montpellier",
            "Aliquam scelerisque interdum ante interdum consectetur. Curabitur nec ante sed massa luctus consectetur sit amet scelerisque magna. Curabitur porta tempor massa, id gravida odio viverra non. Pellentesque convallis sollicitudin neque, eget iaculis erat. Aenean eleifend ante non nisi dictum vulputate. Nam mauris ante, faucibus at massa tincidunt, fermentum imperdiet risus. Integer laoreet nunc ut luctus condimentum. Integer lobortis felis vitae egestas dapibus",
            timestamp
        ),
        Offre(
            "5",
            "Elementum placerat. Interdum et malesuada fames ac (F/H)",
            "Euismod porttitor",
            "34000 Montpellier",
            "Aliquam scelerisque interdum ante interdum consectetur. Curabitur nec ante sed massa luctus consectetur sit amet scelerisque magna. Curabitur porta tempor massa, id gravida odio viverra non. Pellentesque convallis sollicitudin neque, eget iaculis erat. Aenean eleifend ante non nisi dictum vulputate. Nam mauris ante, faucibus at massa tincidunt, fermentum imperdiet risus. Integer laoreet nunc ut luctus condimentum. Integer lobortis felis vitae egestas dapibus",
            timestamp
        ),
        Offre(
            "6",
            " Phasellus ultrices tellus non tellus sollicitudin, (F/H)",
            "Faucibus orci",
            "34000 Montpellier",
            "Fusce eget feugiat felis. Aliquam vitae faucibus dolor, nec iaculis felis. Vivamus ex felis, dignissim eget magna et, sollicitudin blandit tellus. Nunc nisl lectus, placerat vel ullamcorper nec, laoreet vel metus. Morbi sed nibh eros. Vivamus nec orci et turpis ornare hendrerit vel nec odio. Sed nisl risus, gravida nec magna vitae, rutrum dictum risus. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus",
            timestamp
        )
    )

    val utilisateurs = listOf(
        Utilisateur(
            id = "1",
            nom = "John",
            prenom = "Doe",
            adresseMail = "john.doe@example.com",
            motDePasse = "password123",
            role = "Candidat",
            nationalite = "Américain",
            numTelephone = "+1234567890",
            dateNaissance = "01-01-1990"
        )
    )
}