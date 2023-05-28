package com.example.interimexpress.test

import kotlin.random.Random

class EmployeurRegisterActivityTest {
    val prenoms = listOf(
        "Alexis", "Robin", "Marie",
        "Lucas", "Julie", "Thomas",
        "Émilie", "Antoine", "Laura",
        "Nicolas", "Sophie", "Mathieu",
        "Camille", "Pierre", "Manon",
        "Maxime", "Élise", "Romain",
        "Caroline", "Vincent", "Sarah",
        // 20 nouvelles données
        "Jean", "Marine", "Guillaume",
        "Charlotte", "Hugo", "Élodie",
        "Alexandre", "Céline", "Gabriel",
        "Julia", "François", "Léa",
        "Louis", "Éva", "Raphaël",
        "Valérie", "Samuel", "Emma",
        "Damien", "Clara", "Benoît"
    )

    val noms = listOf(
        "Dupont", "Durand", "Martin",
        "Lefebvre", "Moreau", "Girard",
        "Dubois", "Rousseau", "Fournier",
        "Morel", "Lopez", "Lemoine",
        "Leroy", "Lefevre", "Mercier",
        "Dupuis", "Bonnet", "Francois",
        "Martinez", "Legrand", "Garnier",
        // 20 nouvelles données
        "Petit", "Roux", "Leroux",
        "Henry", "Barbier", "Hubert",
        "Gauthier", "Roger", "Perrin",
        "Joly", "Garcia", "Muller",
        "Gonzalez", "Fontaine", "Chevalier",
        "Blanc", "Benoit", "Marchand",
        "Laurent", "Guérin", "Philippe"
    )

    val services = listOf(
        "Service A", "Service B", "Service C",
        "Service D", "Service E", "Service F",
        "Service G", "Service H", "Service I",
        "Service J", "Service K", "Service L",
        "Service M", "Service N", "Service O",
        "Service P", "Service Q", "Service R",
        "Service S", "Service T", "Service U",
        // 20 nouvelles données
        "Service V", "Service W", "Service X",
        "Service Y", "Service Z", "Service AA",
        "Service BB", "Service CC", "Service DD",
        "Service EE", "Service FF", "Service GG",
        "Service HH", "Service II", "Service JJ",
        "Service KK", "Service LL", "Service MM",
        "Service NN", "Service OO", "Service PP"
    )

    val sousServices = listOf(
        "Sous-service 1", "Sous-service 2", "Sous-service 3",
        "Sous-service 4", "Sous-service 5", "Sous-service 6",
        "Sous-service 7", "Sous-service 8", "Sous-service 9",
        "Sous-service 10", "Sous-service 11", "Sous-service 12",
        "Sous-service 13", "Sous-service 14", "Sous-service 15",
        "Sous-service 16", "Sous-service 17", "Sous-service 18",
        "Sous-service 19", "Sous-service 20", "Sous-service 21",
        // 20 nouvelles données
        "Sous-service 22", "Sous-service 23", "Sous-service 24",
        "Sous-service 25", "Sous-service 26", "Sous-service 27",
        "Sous-service 28", "Sous-service 29", "Sous-service 30",
        "Sous-service 31", "Sous-service 32", "Sous-service 33",
        "Sous-service 34", "Sous-service 35", "Sous-service 36",
        "Sous-service 37", "Sous-service 38", "Sous-service 39",
        "Sous-service 40", "Sous-service 41", "Sous-service 42"
    )

    val villes = listOf(
        "Paris", "Lyon", "Marseille",
        "Bordeaux", "Toulouse", "Nice",
        "Nantes", "Strasbourg", "Rennes",
        "Lille", "Montpellier", "Reims",
        "Saint-Étienne", "Le Havre", "Clermont-Ferrand",
        "Aix-en-Provence", "Brest", "Limoges",
        "Toulon", "Angers", "Dijon",
        // 20 nouvelles données
        "Rouen", "Avignon", "Nîmes",
        "Grenoble", "Saint-Denis", "La Rochelle",
        "Nancy", "Tourcoing", "Metz",
        "Caen", "Orléans", "Mulhouse",
        "Saint-Paul", "Roubaix", "Saint-Quentin",
        "Pau", "Calais", "Cannes",
        "Poitiers", "Versailles", "Perpignan"
    )
    val specCharacters = listOf("@", "#", "$", "%", "&", "*", "!")

    fun randomFromList(list: List<String>) = list[Random.nextInt(list.size)]
    fun randomNumber(n: Int) = List(n) { Random.nextInt(0, 10) }.joinToString("")
    fun randomSpecChar() = randomFromList(specCharacters)
    fun randomPassword(): String {
        val chars = ('A'..'Z') + ('a'..'z') + ('0'..'9') + listOf('@', '#', '$', '%', '&', '*', '!')
        return List(10) { chars.random() }.joinToString("")
    }
    fun randomEmail() = "${randomFromList(prenoms)}${randomFromList(noms)}@exemple.com"
}
