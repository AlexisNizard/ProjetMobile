package com.example.interimexpress.model

import android.net.Uri
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*

object InitialData {

    const val VERSION = 66

    private val sdf = SimpleDateFormat("dd/MM/yyyy")
    private val date = sdf.parse("01/02/2022")
    private val timestamp = Timestamp(date)


    val employeurs = listOf(
        Employeur(
            adresseMail = "contact1@entrepriseA.com",
            nomEntreprise = "Entreprise A",
            nomService = "Service A1",
            nomSousService = "Sous-Service A1.1",
            numeroSiret = "12345678901234",
            nomContact1 = "Smith",
            prenomContact1 = "John",
            nomContact2 = "Doe",
            prenomContact2 = "Jane",
            adresseMail2 = "contact2@entrepriseA.com",
            motDePasse = "password123",
            role = "Employeur",
            numTelephone1 = "+1234567890",
            numTelephone2 = "+0987654321",
            adresse = "123 Rue de l'Exemple",
            codePostal = "75000",
            ville = "Paris",
            lienSiteWeb = "www.entrepriseA.com",
            lienLinkedin = "www.linkedin.com/in/entrepriseA",
            lienFacebook = "www.facebook.com/entrepriseA",
            valide = 1,
            repondu=1
        ),Employeur(
            adresseMail = "contact1@entrepriseB.com",
            nomEntreprise = "Entreprise B",
            nomService = null,
            nomSousService = "Sous-Service B1.1",
            numeroSiret = "23456789012345",
            nomContact1 = "Johnson",
            prenomContact1 = "Robert",
            nomContact2 = "Davies",
            prenomContact2 = "Emily",
            adresseMail2 = null,
            motDePasse = "password456",
            role = "Employeur",
            numTelephone1 = "+2345678901",
            numTelephone2 = null,
            adresse = "456 Avenue de l'Exemple",
            codePostal = "69000",
            ville = "Lyon",
            lienSiteWeb = null,
            lienLinkedin = "www.linkedin.com/in/entrepriseB",
            lienFacebook = null,
            valide = 1,
            repondu=1
        ),Employeur(
            adresseMail = "contact1@entrepriseC.com",
            nomEntreprise = "Entreprise C",
            nomService = null,
            nomSousService = null,
            numeroSiret = "34567890123456",
            nomContact1 = "Anderson",
            prenomContact1 = "Michael",
            nomContact2 = null,
            prenomContact2 = null,
            adresseMail2 = null,
            motDePasse = "password789",
            role = "Employeur",
            numTelephone1 = "+3456789012",
            numTelephone2 = null,
            adresse = "789 Boulevard de l'Exemple",
            codePostal = "31000",
            ville = "Toulouse",
            lienSiteWeb = "www.entrepriseC.com",
            lienLinkedin = null,
            lienFacebook = null,
            valide = 1,
            repondu=1
        )

        ,Employeur(
            adresseMail = "contact1@entrepriseD.com",
            nomEntreprise = "Entreprise D",
            nomService = "Service D1",
            nomSousService = null,
            numeroSiret = "45678901234567",
            nomContact1 = null,
            prenomContact1 = null,
            nomContact2 = null,
            prenomContact2 = null,
            adresseMail2 = null,
            motDePasse = "password012",
            role = "Employeur",
            numTelephone1 = null,
            numTelephone2 = null,
            adresse = "987 Rue de l'Exemple",
            codePostal = "44000",
            ville = "Nantes",
            lienSiteWeb = "www.entrepriseD.com",
            lienLinkedin = null,
            lienFacebook = null,
            valide = 1,
            repondu=1
        )

        ,Employeur(
            adresseMail = "contact1@entrepriseE.com",
            nomEntreprise = "Entreprise E",
            nomService = "Service E1",
            nomSousService = null,
            numeroSiret = "56789012345678",
            nomContact1 = "Brown",
            prenomContact1 = "David",
            nomContact2 = null,
            prenomContact2 = null,
            adresseMail2 = null,
            motDePasse = "password345",
            role = "Employeur",
            numTelephone1 = "+5678901234",
            numTelephone2 = null,
            adresse = "654 Chemin de l'Exemple",
            codePostal = "69001",
            ville = "Lyon",
            lienSiteWeb = null,
            lienLinkedin = null,
            lienFacebook = "www.facebook.com/entrepriseE",
            valide = 1,
            repondu=1
        )

        ,Employeur(
            adresseMail = "contact1@entrepriseF.com",
            nomEntreprise = "Entreprise F",
            nomService = "Service F1",
            nomSousService = "Sous-Service F1.1",
            numeroSiret = "67890123456789",
            nomContact1 = null,
            prenomContact1 = null,
            nomContact2 = "Wilson",
            prenomContact2 = "Sarah",
            adresseMail2 = null,
            motDePasse = "password678",
            role = "Employeur",
            numTelephone1 = null,
            numTelephone2 = "+6789012345",
            adresse = "987 Avenue de l'Exemple",
            codePostal = "35000",
            ville = "Rennes",
            lienSiteWeb = "www.entrepriseF.com",
            lienLinkedin = null,
            lienFacebook = null,
            valide = 1,
            repondu=1
        )

        ,Employeur(
            adresseMail = "contact1@entrepriseG.com",
            nomEntreprise = "Entreprise G",
            nomService = null,
            nomSousService = null,
            numeroSiret = "78901234567890",
            nomContact1 = "Taylor",
            prenomContact1 = "Daniel",
            nomContact2 = null,
            prenomContact2 = null,
            adresseMail2 = null,
            motDePasse = "password901",
            role = "Employeur",
            numTelephone1 = "+7890123456",
            numTelephone2 = null,
            adresse = "321 Rue de l'Exemple",
            codePostal = "67000",
            ville = "Strasbourg",
            lienSiteWeb = null,
            lienLinkedin = "www.linkedin.com/in/entrepriseG",
            lienFacebook = null,
            valide = 1,
            repondu=1
        )

        ,Employeur(
            adresseMail = "contact1@entrepriseH.com",
            nomEntreprise = "Entreprise H",
            nomService = "Service H1",
            nomSousService = "Sous-Service H1.1",
            numeroSiret = "89012345678901",
            nomContact1 = "Walker",
            prenomContact1 = "Jennifer",
            nomContact2 = "Lewis",
            prenomContact2 = "Michael",
            adresseMail2 = null,
            motDePasse = "password234",
            role = "Employeur",
            numTelephone1 = "+8901234567",
            numTelephone2 = "+8901234567",
            adresse = "456 Boulevard de l'Exemple",
            codePostal = "59000",
            ville = "Lille",
            lienSiteWeb = "www.entrepriseH.com",
            lienLinkedin = "www.linkedin.com/in/entrepriseH",
            lienFacebook = "www.facebook.com/entrepriseH",
            valide = 1,
            repondu=1
        )

        ,Employeur(
            adresseMail = "contact1@entrepriseI.com",
            nomEntreprise = "Entreprise I",
            nomService = "Service I1",
            nomSousService = null,
            numeroSiret = "90123456789012",
            nomContact1 = "Clark",
            prenomContact1 = "William",
            nomContact2 = null,
            prenomContact2 = null,
            adresseMail2 = "contact2@entrepriseI.com",
            motDePasse = "password567",
            role = "Employeur",
            numTelephone1 = "+9012345678",
            numTelephone2 = null,
            adresse = "789 Chemin de l'Exemple",
            codePostal = "34000",
            ville = "Montpellier",
            lienSiteWeb = null,
            lienLinkedin = null,
            lienFacebook = null,
            valide = 1,
            repondu=1
        )

        ,Employeur(
            adresseMail = "contact1@entrepriseJ.com",
            nomEntreprise = "Entreprise J",
            nomService = null,
            nomSousService = null,
            numeroSiret = "01234567890123",
            nomContact1 = "Allen",
            prenomContact1 = "Jessica",
            nomContact2 = null,
            prenomContact2 = null,
            adresseMail2 = null,
            motDePasse = "password890",
            role = "Employeur",
            numTelephone1 = "+0123456789",
            numTelephone2 = null,
            adresse = "987 Avenue de l'Exemple",
            codePostal = "13000",
            ville = "Marseille",
            lienSiteWeb = null,
            lienLinkedin = "www.linkedin.com/in/entrepriseJ",
            lienFacebook = null,
            valide = 1,
            repondu=1
        )

        ,Employeur(
            adresseMail = "contact1@entrepriseK.com",
            nomEntreprise = "Entreprise K",
            nomService = "Service K1",
            nomSousService = "Sous-Service K1.1",
            numeroSiret = "12345678901234",
            nomContact1 = "Green",
            prenomContact1 = "Oliver",
            nomContact2 = "Moore",
            prenomContact2 = "Emma",
            adresseMail2 = "contact2@entrepriseK.com",
            motDePasse = "password123",
            role = "Employeur",
            numTelephone1 = "+1234567890",
            numTelephone2 = "+0987654321",
            adresse = "123 Rue de l'Exemple",
            codePostal = "75000",
            ville = "Paris",
            lienSiteWeb = "www.entrepriseK.com",
            lienLinkedin = "www.linkedin.com/in/entrepriseK",
            lienFacebook = "www.facebook.com/entrepriseK",
            valide = 1,
            repondu=1
        )

        ,Employeur(
            adresseMail = "contact1@entrepriseL.com",
            nomEntreprise = "Entreprise L",
            nomService = null,
            nomSousService = null,
            numeroSiret = "23456789012345",
            nomContact1 = "Wilson",
            prenomContact1 = "Sarah",
            nomContact2 = "Smith",
            prenomContact2 = "John",
            adresseMail2 = null,
            motDePasse = "password123",
            role = "Employeur",
            numTelephone1 = "+2345678901",
            numTelephone2 = "+0987654321",
            adresse = "456 Avenue de l'Exemple",
            codePostal = "69000",
            ville = "Lyon",
            lienSiteWeb = null,
            lienLinkedin = "www.linkedin.com/in/entrepriseL",
            lienFacebook = null,
            valide = 1,
            repondu=1
        )
        ,Employeur(
            adresseMail = "contact1@entrepriseH.com",
            nomEntreprise = "Entreprise M",
            nomService = "Service M1",
            nomSousService = null,
            numeroSiret = "34567890123456",
            nomContact1 = "Davis",
            prenomContact1 = "Emma",
            nomContact2 = null,
            prenomContact2 = null,
            adresseMail2 = null,
            motDePasse = "password456",
            role = "Employeur",
            numTelephone1 = null,
            numTelephone2 = null,
            adresse = "789 Boulevard de l'Exemple",
            codePostal = "31000",
            ville = "Toulouse",
            lienSiteWeb = null,
            lienLinkedin = "www.linkedin.com/in/entrepriseM",
            lienFacebook = null,
            valide = 1,
            repondu=1
        )
    )

    val candidats = listOf(
        Candidat(
            adresseMail = "john.doe@example.com",
            nom = "John",
            prenom = "Doe",
            motDePasse = "password123",
            role = "Candidat",
            nationalite = "France",
            numTelephone = "+1234567890",
            dateNaissance = "01-01-1990"
        ),Candidat(
            adresseMail = "emma.wilson@example.com",
            nom = "Wilson",
            prenom = "Emma",
            motDePasse = "password456",
            role = "Candidat",
            nationalite = "France",
            numTelephone = "+2345678901",
            dateNaissance = "02-02-1995"
        ),Candidat(
            adresseMail = "michael.anderson@example.com",
            nom = "Anderson",
            prenom = "Michael",
            motDePasse = "password789",
            role = "Candidat",
            nationalite = "Finland",
            numTelephone = "+3456789012",
            dateNaissance = "03-03-1992"
        )
        ,Candidat(
            adresseMail = "olivia.thomas@example.com",
            nom = "Thomas",
            prenom = "Olivia",
            motDePasse = "password012",
            role = "Candidat",
            nationalite = "Guadeloupe",
            numTelephone = "+4567890123",
            dateNaissance = "04-04-1988"
        )
        ,Candidat(
            adresseMail = "liam.johnson@example.com",
            nom = "Johnson",
            prenom = "Liam",
            motDePasse = "password345",
            role = "Candidat",
            nationalite = "Ireland",
            numTelephone = "+5678901234",
            dateNaissance = "05-05-1993"
        )
        ,Candidat(
            adresseMail = "sophia.martin@example.com",
            nom = "Martin",
            prenom = "Sophia",
            motDePasse = "password678",
            role = "Candidat",
            nationalite = "Luxembourg",
            numTelephone = "+6789012345",
            dateNaissance = "06-06-1990"
        )
        ,Candidat(
            adresseMail = "daniel.brown@example.com",
            nom = "Brown",
            prenom = "Daniel",
            motDePasse = "password901",
            role = "Candidat",
            nationalite = "Mozambique",
            numTelephone = "+7890123456",
            dateNaissance = "07-07-1996"
        )
        ,Candidat(
            adresseMail = "mia.lewis@example.com",
            nom = "Lewis",
            prenom = "Mia",
            motDePasse = "password234",
            role = "Candidat",
            nationalite = "Panama",
            numTelephone = "+8901234567",
            dateNaissance = "08-08-1991"
        )
        ,Candidat(
            adresseMail = "noah.clark@example.com",
            nom = "Clark",
            prenom = "Noah",
            motDePasse = "password567",
            role = "Candidat",
            nationalite = "Somalia",
            numTelephone = "+9012345678",
            dateNaissance = "09-09-1987"
        )
        ,Candidat(
            adresseMail = "amelia.harris@example.com",
            nom = "Harris",
            prenom = "Amelia",
            motDePasse = "password890",
            role = "Candidat",
            nationalite = "Tunisia",
            numTelephone = "+0123456789",
            dateNaissance = "10-10-1994"
        )
        ,Candidat(
            adresseMail = "logan.green@example.com",
            nom = "Green",
            prenom = "Logan",
            motDePasse = "password123",
            role = "Candidat",
            nationalite = "Tunisia",
            numTelephone = "+1234567890",
            dateNaissance = "11-11-1999"
        )
    )

    val offres = listOf(
        Offre(
            id = "2",
            titre = "Stage Data Analyst - Montpellier (F/H)",
            entreprise = "Microsoft",
            adresse = "Montpellier",
            cp ="34000",
            mail = "contact1@entrepriseA.com",
            typeContrat = "STAGE",
            remuneration = 2500.0,
            dateDebut = Timestamp(sdf.parse("01/09/2023")),
            dateFin = Timestamp(sdf.parse("01/12/2023")),
            description = "Microsoft est une entreprise technologique leader dans le domaine des logiciels, des services et des solutions cloud. Notre mission est d'aider chaque personne et chaque organisation sur la planète à réaliser davantage.\n" +
                    "\n" +
                    "Si tu es passionné(e) par l'analyse des données et que tu souhaites contribuer à des projets d'envergure, alors rejoins dès aujourd'hui notre équipe en tant que stagiaire Data Analyst.\n" +
                    "\n" +
                    "Ton rôle et tes compétences\n" +
                    "\n" +
                    "En tant que stagiaire Data Analyst, tes missions principales seront les suivantes :\n" +
                    "\n" +
                    "Collecter et analyser des données provenant de différentes sources\n" +
                    "Développer et mettre en œuvre des modèles d'analyse de données\n" +
                    "Identifier des tendances et des insights à partir des données analysées\n" +
                    "Créer des visualisations de données claires et compréhensibles\n" +
                    "Travailler en collaboration avec les équipes techniques et les responsables de projet\n" +
                    "Participer à l'amélioration continue des processus d'analyse de données\n" +
                    "\n" +
                    "Tes compétences\n" +
                    "\n" +
                    "Actuellement en cours de formation BAC+4/BAC+5 dans le domaine de l'informatique, des mathématiques appliquées ou des statistiques, tu es à l'aise avec les outils d'analyse de données tels que Python, R ou SQL.\n" +
                    "\n" +
                    "Tu possèdes une capacité d'analyse et de synthèse, ainsi qu'une bonne compréhension des enjeux liés à l'exploitation des données.\n" +
                    "\n" +
                    "Les avantages à nous rejoindre\n" +
                    "\n" +
                    "Un environnement de travail stimulant et innovant\n" +
                    "L'opportunité de travailler sur des projets d'envergure internationale\n" +
                    "Des formations et des opportunités de développement professionnel\n" +
                    "Une entreprise engagée en faveur de la diversité et de l'inclusion\n" +
                    "Une équipe dynamique et bienveillante\n" +
                    "\n" +
                    "Pour en savoir plus sur nos opportunités de carrière, rendez-vous sur notre site web : https://www.microsoft.com/fr-fr/carrieres/\n" +
                    "\n" +
                    "Microsoft valorise la diversité et s'engage à créer un environnement inclusif pour tous les employés.\n" +
                    "\n" +
                    "Si tu es prêt(e) à relever de nouveaux défis et à contribuer à notre mission, n'hésite plus et postule dès maintenant !",
            dateCreation =Timestamp(Date())
        ),Offre(
            id = "3",
            titre = "Stage Marketing Digital - Lyon (F/H)",
            entreprise = "L'Oréal",
            adresse = "Lyon",
            cp ="69000",
            mail =  "contact1@entrepriseB.com",
            typeContrat = "STAGE",
            remuneration = 2000.0,
            dateDebut = Timestamp(sdf.parse("01/08/2023")),
            dateFin = Timestamp(sdf.parse("01/12/2023")),
            description = "L'Oréal est le leader mondial de la beauté, présent dans plus de 150 pays. Nous offrons une large gamme de produits cosmétiques et de soins capillaires de haute qualité, dans le respect de l'environnement.\n" +
                    "\n" +
                    "Si tu es passionné(e) par le marketing digital et que tu souhaites rejoindre une équipe dynamique et créative, alors ce stage est fait pour toi ! Nous recherchons un(e) stagiaire en marketing digital pour notre site basé à Lyon.\n" +
                    "\n" +
                    "Ton rôle et tes compétences\n" +
                    "\n" +
                    "En tant que stagiaire en marketing digital, tes missions principales seront les suivantes :\n" +
                    "\n" +
                    "Participer à la définition de la stratégie digitale de notre marque\n" +
                    "Mettre en place et suivre des campagnes publicitaires en ligne\n" +
                    "Analyser les performances des actions marketing et proposer des recommandations d'amélioration\n" +
                    "Gérer les réseaux sociaux de la marque et créer du contenu attractif\n" +
                    "Effectuer une veille concurrentielle et technologique pour rester à la pointe des tendances\n" +
                    "Travailler en collaboration avec les équipes créatives, les agences et les partenaires externes\n" +
                    "\n" +
                    "Tes compétences\n" +
                    "\n" +
                    "Actuellement en cours de formation BAC+4/BAC+5 dans le domaine du marketing, de la communication ou du commerce, tu possèdes une véritable passion pour le digital et les réseaux sociaux.\n" +
                    "\n" +
                    "Tu es créatif(ve), autonome et tu possèdes une excellente capacité d'analyse. Tu maîtrises les outils de marketing digital tels que Google Analytics, les plateformes de gestion des réseaux sociaux et les outils de création graphique.\n" +
                    "\n" +
                    "Les avantages à nous rejoindre\n" +
                    "\n" +
                    "Une expérience enrichissante au sein d'une entreprise internationale\n" +
                    "La possibilité de travailler sur des marques renommées\n" +
                    "Un environnement de travail stimulant et créatif\n" +
                    "Des formations et des opportunités d'évolution\n" +
                    "Une équipe dynamique et bienveillante\n" +
                    "\n" +
                    "Pour en savoir plus sur notre entreprise et nos valeurs, rendez-vous sur notre site web : https://www.loreal.fr/\n" +
                    "\n" +
                    "L'Oréal s'engage en faveur de la diversité et de l'inclusion.\n" +
                    "\n" +
                    "Si tu es prêt(e) à relever de nouveaux défis dans le domaine du marketing digital, n'hésite plus et postule dès maintenant !",
            dateCreation =Timestamp(sdf.parse("20/05/2023"))
        )
        ,Offre(
            id = "4",
            titre = "Alternance Ressources Humaines - Montpellier (F/H)",
            entreprise = "LVMH",
            adresse = "Montpellier",
            cp ="34000",
            mail = "contact1@entrepriseC.com",
            typeContrat = "ALTERNANCE",
            remuneration = 1500.0,
            dateDebut = Timestamp(sdf.parse("01/09/2023")),
            dateFin = Timestamp(sdf.parse("01/09/2024")),
            description = "LVMH est un groupe leader dans le domaine du luxe, regroupant plusieurs marques prestigieuses dans les secteurs de la mode, de la maroquinerie, des parfums et des cosmétiques. Nous sommes engagés dans l'excellence, l'innovation et la créativité.\n" +
                    "\n" +
                    "Si tu es passionné(e) par les ressources humaines et que tu souhaites acquérir une expérience professionnelle enrichissante au sein d'un environnement dynamique, rejoins-nous en tant qu'alternant(e) en ressources humaines à Paris.\n" +
                    "\n" +
                    "Ton rôle et tes compétences\n" +
                    "\n" +
                    "En tant qu'alternant(e) en ressources humaines, tu auras l'opportunité de participer à diverses missions, telles que :\n" +
                    "\n" +
                    "Assister à la gestion des processus de recrutement et de sélection\n" +
                    "Contribuer à la gestion administrative du personnel (contrats, absences, congés, etc.)\n" +
                    "Participer à la mise en place de projets de développement des compétences\n" +
                    "Contribuer à l'élaboration et à la mise à jour des procédures RH\n" +
                    "Participer à l'organisation d'événements internes et à la communication interne\n" +
                    "Collaborer avec les différents acteurs des ressources humaines\n" +
                    "\n" +
                    "Tes compétences\n" +
                    "\n" +
                    "Actuellement en cours de formation en ressources humaines, en psychologie ou dans un domaine similaire, tu es à la recherche d'une alternance pour développer tes compétences dans le domaine RH.\n" +
                    "\n" +
                    "Tu possèdes de bonnes capacités d'organisation, une excellente communication écrite et orale, ainsi qu'une bonne maîtrise des outils informatiques (MS Office).\n" +
                    "\n" +
                    "Les avantages à nous rejoindre\n" +
                    "\n" +
                    "Une expérience valorisante au sein d'un groupe de renommée mondiale\n" +
                    "La possibilité de travailler sur des projets variés et stimulants\n" +
                    "Un encadrement et un suivi personnalisé tout au long de ton alternance\n" +
                    "Une opportunité de développer ton réseau professionnel\n" +
                    "Une ambiance de travail conviviale et dynamique\n" +
                    "\n" +
                    "Pour en savoir plus sur notre groupe et nos marques, rendez-vous sur notre site web : https://www.lvmh.fr/\n" +
                    "\n" +
                    "LVMH est engagé en faveur de la diversité et de l'inclusion.\n" +
                    "\n" +
                    "Si tu es prêt(e) à vivre une expérience unique dans le domaine des ressources humaines, postule dès maintenant !",
            dateCreation = Timestamp(sdf.parse("10/05/2023"))
        )
        ,Offre(
            id = "5",
            titre = "Chef de Projet Marketing - Marseille (F/H)",
            entreprise = "Airbus",
            adresse = "Marseille",
            cp ="13000",
            mail = "contact1@entrepriseD.com",
            typeContrat = "CDI",
            remuneration = 5000.0,
            dateDebut = Timestamp(sdf.parse("01/08/2023")),
            dateFin = null,
            description = "Airbus est un leader mondial de l'aéronautique, de l'espace et des services associés. Nous concevons, fabriquons et livrons des avions innovants pour les compagnies aériennes du monde entier. Notre mission est de repousser les limites de la technologie et de créer un avenir plus durable pour l'industrie.\n" +
                    "\n" +
                    "Si tu es passionné(e) par le marketing, que tu as une expérience solide en gestion de projets et que tu souhaites rejoindre une entreprise d'envergure internationale, alors cette offre de Chef de Projet Marketing est faite pour toi ! Rejoins-nous à Marseille.\n" +
                    "\n" +
                    "Ton rôle et tes compétences\n" +
                    "\n" +
                    "En tant que Chef de Projet Marketing, tes principales responsabilités seront :\n" +
                    "\n" +
                    "Gérer et coordonner des projets marketing d'envergure\n" +
                    "Définir et mettre en œuvre des stratégies de marketing et de communication\n" +
                    "Collaborer avec les équipes internes et externes pour assurer la réalisation des projets dans les délais\n" +
                    "Analyser les résultats des campagnes marketing et proposer des améliorations\n" +
                    "Veiller à la cohérence de l'image de marque et de la communication de l'entreprise\n" +
                    "Gérer le budget alloué aux projets et assurer le suivi financier\n" +
                    "\n" +
                    "Tes compétences\n" +
                    "\n" +
                    "Titulaire d'un diplôme supérieur en marketing, communication ou dans un domaine similaire, tu possèdes une expérience significative en gestion de projets marketing.\n" +
                    "\n" +
                    "Tu as de solides compétences en communication, une grande capacité d'organisation et une vision stratégique. Tu maîtrises les outils de marketing digital et tu es à l'aise dans un environnement international.\n" +
                    "\n" +
                    "Les avantages à nous rejoindre\n" +
                    "\n" +
                    "La possibilité de travailler sur des projets d'envergure mondiale\n" +
                    "Un environnement de travail stimulant et innovant\n" +
                    "Des perspectives d'évolution et de développement professionnel\n" +
                    "Un package salarial attractif et des avantages sociaux\n" +
                    "Une entreprise engagée en faveur de la durabilité et de la responsabilité sociale\n" +
                    "\n" +
                    "Pour en savoir plus sur notre entreprise et nos opportunités de carrière, rendez-vous sur notre site web : https://www.airbus.com/\n" +
                    "\n" +
                    "Airbus valorise la diversité et l'inclusion.\n" +
                    "\n" +
                    "Si tu es prêt(e) à relever ce défi passionnant, postule dès maintenant pour rejoindre notre équipe !",
            dateCreation = Timestamp(sdf.parse("13/05/2023"))
        )
        ,Offre(
            id = "6",
            titre = "Alternance Ingénieur Mécanique - Toulouse (F/H)",
            entreprise = "Airbus",
            adresse = "Toulouse",
            cp ="31000",
            mail =  "contact1@entrepriseE.com",
            typeContrat = "ALTERNANCE",
            remuneration = 2000.0,
            dateDebut = Timestamp(sdf.parse("01/09/2023")),
            dateFin = Timestamp(sdf.parse("01/09/2024")),
            description = "Airbus est un leader mondial de l'aéronautique, de l'espace et des services associés. Nous concevons, fabriquons et livrons des avions innovants pour les compagnies aériennes du monde entier. Notre mission est de repousser les limites de la technologie et de créer un avenir plus durable pour l'industrie.\n" +
                    "\n" +
                    "Si tu es passionné(e) par l'ingénierie mécanique, que tu souhaites acquérir une expérience professionnelle enrichissante au sein d'une entreprise de renommée mondiale, alors rejoins-nous en tant qu'alternant(e) ingénieur mécanique à Toulouse.\n" +
                    "\n" +
                    "Ton rôle et tes compétences\n" +
                    "\n" +
                    "En tant qu'alternant(e) ingénieur mécanique, tu auras l'opportunité de participer à diverses missions, telles que :\n" +
                    "\n" +
                    "Contribuer à la conception et à la modélisation de pièces mécaniques\n" +
                    "Participer à des analyses de résistance et de performance\n" +
                    "Collaborer avec les équipes de développement pour optimiser les processus de production\n" +
                    "Effectuer des essais et des validations sur les composants mécaniques\n" +
                    "Contribuer à l'amélioration continue des produits et des procédés\n" +
                    "Travailler en équipe sur des projets d'innovation et de recherche\n" +
                    "\n" +
                    "Tes compétences\n" +
                    "\n" +
                    "Actuellement en cours de formation en génie mécanique ou dans un domaine similaire, tu es à la recherche d'une alternance pour développer tes compétences techniques.\n" +
                    "\n" +
                    "Tu possèdes de bonnes connaissances en conception assistée par ordinateur (CAO) et en calculs mécaniques. Tu es rigoureux(se), curieux(se) et tu aimes travailler en équipe.\n" +
                    "\n" +
                    "Les avantages à nous rejoindre\n" +
                    "\n" +
                    "Une expérience valorisante au sein d'un leader de l'aéronautique\n" +
                    "La possibilité de travailler sur des projets innovants et d'envergure internationale\n" +
                    "Un encadrement et un suivi personnalisé tout au long de ton alternance\n" +
                    "Une opportunité de développer ton réseau professionnel\n" +
                    "Une ambiance de travail stimulante et collaborative\n" +
                    "\n" +
                    "Pour en savoir plus sur notre entreprise et nos opportunités de carrière, rendez-vous sur notre site web : https://www.airbus.com/\n" +
                    "\n" +
                    "Airbus valorise la diversité et l'inclusion.\n" +
                    "\n" +
                    "Si tu es prêt(e) à vivre une expérience enrichissante dans le domaine de l'ingénierie mécanique, n'hésite plus et postule dès maintenant !",
            dateCreation = Timestamp(sdf.parse("22/05/2023"))
        )
        ,Offre(
            id = "7",
            titre = "Alternance Développeur Full Stack - Montpellier (F/H)",
            entreprise = "Google",
            adresse = "Montpellier",
            cp ="34000",
            mail = "contact1@entrepriseF.com",
            typeContrat = "ALTERNANCE",
            remuneration = 2500.0,
            dateDebut = Timestamp(sdf.parse("01/09/2023")),
            dateFin = Timestamp(sdf.parse("01/09/2024")),
            description = "Google est une entreprise technologique leader dans le domaine de l'Internet et des services en ligne. Nous sommes dédiés à l'innovation et à la création de produits et de services qui facilitent la vie des utilisateurs à travers le monde.\n" +
                    "\n" +
                    "Si tu es passionné(e) par le développement web et que tu souhaites rejoindre une équipe talentueuse et dynamique, alors cette offre d'alternance en tant que développeur Full Stack est faite pour toi ! Rejoins-nous à Paris.\n" +
                    "\n" +
                    "Ton rôle et tes compétences\n" +
                    "\n" +
                    "En tant qu'alternant(e) développeur Full Stack, tu auras l'opportunité de participer à des projets de développement web, en travaillant sur les tâches suivantes :\n" +
                    "\n" +
                    "Participer à la conception et au développement de nouvelles fonctionnalités\n" +
                    "Développer des applications web performantes et intuitives\n" +
                    "Collaborer avec les équipes de design et de produit pour créer des expériences utilisateur exceptionnelles\n" +
                    "Assurer la qualité du code à travers des tests et des revues de code\n" +
                    "Utiliser les technologies web modernes telles que JavaScript, HTML, CSS, etc.\n" +
                    "Rester à jour sur les nouvelles tendances et les meilleures pratiques en matière de développement web\n" +
                    "\n" +
                    "Tes compétences\n" +
                    "\n" +
                    "Actuellement en cours de formation en informatique, en développement web ou dans un domaine similaire, tu possèdes une solide connaissance des langages de programmation web et des frameworks associés.\n" +
                    "\n" +
                    "Tu es curieux(se), autonome et tu as une passion pour le développement de solutions innovantes. Tu as également de bonnes compétences en résolution de problèmes et en travail d'équipe.\n" +
                    "\n" +
                    "Les avantages à nous rejoindre\n" +
                    "\n" +
                    "Une expérience professionnelle au sein d'une entreprise de renommée mondiale\n" +
                    "La possibilité de travailler sur des projets d'envergure internationale\n" +
                    "Un environnement de travail stimulant et collaboratif\n" +
                    "Des opportunités de développement professionnel et de croissance\n" +
                    "Une culture d'entreprise axée sur l'innovation et l'excellence\n" +
                    "\n" +
                    "Pour en savoir plus sur notre entreprise et nos opportunités de carrière, rendez-vous sur notre site web : https://www.google.com/\n" +
                    "\n" +
                    "Google valorise la diversité et l'inclusion.\n" +
                    "\n" +
                    "Si tu souhaites relever ce défi passionnant en tant que développeur Full Stack, postule dès maintenant pour rejoindre notre équipe !",
            dateCreation = Timestamp(sdf.parse("20/04/2023"))
        )
        ,Offre(
            id = "8",
            titre = "CDD Chargé(e) de Projet Marketing - Lyon (F/H)",
            entreprise = "Amazon",
            adresse = "Lyon",
            cp ="69000",
            mail =  "contact1@entrepriseG.com",
            typeContrat = "CDD",
            remuneration = 3000.0,
            dateDebut = Timestamp(sdf.parse("01/07/2023")),
            dateFin = Timestamp(sdf.parse("01/12/2023")),
            description = "Amazon est une entreprise technologique et de commerce électronique de renommée mondiale. Nous sommes spécialisés dans la vente en ligne de produits variés et nous nous efforçons d'offrir la meilleure expérience d'achat à nos clients.\n" +
                    "\n" +
                    "Si tu es passionné(e) par le marketing et que tu as une expérience en gestion de projet, alors cette offre de Chargé(e) de Projet Marketing en CDD est faite pour toi ! Rejoins notre équipe à Lyon.\n" +
                    "\n" +
                    "Ton rôle et tes compétences\n" +
                    "\n" +
                    "En tant que Chargé(e) de Projet Marketing, tes principales responsabilités seront :\n" +
                    "\n" +
                    "Gérer et coordonner des projets marketing, de la conception à la mise en œuvre\n" +
                    "Définir et suivre les indicateurs de performance des campagnes\n" +
                    "Collaborer avec les équipes internes et externes pour assurer la réussite des projets\n" +
                    "Participer à l'élaboration des plans marketing et à l'optimisation des stratégies\n" +
                    "Analyser les tendances du marché et identifier les opportunités\n" +
                    "Assurer la conformité des activités marketing avec les objectifs et les normes de l'entreprise\n" +
                    "\n" +
                    "Tes compétences\n" +
                    "\n" +
                    "Titulaire d'un diplôme supérieur en marketing, communication ou dans un domaine similaire, tu possèdes une expérience pertinente en gestion de projet marketing.\n" +
                    "\n" +
                    "Tu es créatif(ve), rigoureux(se) et orienté(e) résultats. Tu as de bonnes compétences en communication et en analyse de données. Une connaissance du domaine du commerce électronique serait un plus.\n" +
                    "\n" +
                    "Les avantages à nous rejoindre\n" +
                    "\n" +
                    "Une expérience professionnelle au sein d'une entreprise mondiale et innovante\n" +
                    "La possibilité de travailler sur des projets d'envergure\n" +
                    "Un environnement de travail dynamique et stimulant\n" +
                    "Des opportunités de développement et de croissance professionnelle\n" +
                    "Une culture d'entreprise axée sur l'excellence et l'innovation\n" +
                    "\n" +
                    "Pour en savoir plus sur notre entreprise et nos opportunités de carrière, rendez-vous sur notre site web : https://www.amazon.fr/\n" +
                    "\n" +
                    "Amazon valorise la diversité et l'inclusion.\n" +
                    "\n" +
                    "Si tu es prêt(e) à relever ce défi passionnant en tant que Chargé(e) de Projet Marketing, postule dès maintenant !",
            dateCreation =Timestamp(sdf.parse("22/03/2023"))
        )
        ,Offre(
            id = "9",
            titre = "CDI Développeur Front-End - Paris (F/H)",
            entreprise = "Facebook",
            adresse = "Paris",
            cp ="75000",
            mail =  "contact1@entrepriseH.com",
            typeContrat = "CDI",
            remuneration = 4500.0,
            dateDebut = Timestamp(sdf.parse("01/09/2023")),
            dateFin = null,
            description = "Facebook est une entreprise technologique mondiale qui connecte des milliards de personnes à travers le monde. Nous sommes à la pointe de l'innovation dans le domaine des réseaux sociaux et des technologies web.\n" +
                    "\n" +
                    "Si tu es passionné(e) par le développement Front-End et que tu souhaites rejoindre une équipe talentueuse et dynamique, cette offre de CDI en tant que développeur Front-End est faite pour toi ! Rejoins-nous à Paris.\n" +
                    "\n" +
                    "Ton rôle et tes compétences :\n" +
                    "\n" +
                    "En tant que développeur Front-End, tu seras responsable de la conception et de la création d'interfaces utilisateur attrayantes et réactives. Tu travailleras en étroite collaboration avec les équipes de conception et de développement pour mettre en œuvre des fonctionnalités innovantes.\n" +
                    "\n" +
                    "Compétences requises :\n" +
                    "\n" +
                    " - Solide expérience dans le développement Front-End avec HTML, CSS et JavaScript\n" +
                    " - Maîtrise des frameworks JavaScript tels que React ou Angular\n" +
                    " - Bonnes compétences en résolution de problèmes et en collaboration\n" +
                    " - Passion pour l'expérience utilisateur et la création d'interfaces esthétiques et conviviales\n" +
                    " - Capacité à travailler dans un environnement dynamique et en évolution\n" +
                    "\n" +
                    "Les avantages à nous rejoindre :\n" +
                    "\n" +
                    " - Un environnement de travail stimulant et collaboratif\n" +
                    " - Des opportunités de développement professionnel et de croissance\n" +
                    " - Une culture d'entreprise axée sur l'innovation et l'excellence\n" +
                    " - Des avantages sociaux attractifs\n" +
                    " - La possibilité de travailler sur des produits utilisés par des millions de personnes dans le monde\n" +
                    "\n" +
                    "Postule dès maintenant pour rejoindre notre équipe et contribuer à façonner l'avenir des réseaux sociaux !",
            dateCreation = Timestamp(sdf.parse("01/05/2023"))
        )
        ,Offre(
            id = "10",
            titre = "CDD Analyste Financier - Montpellier (F/H)",
            entreprise = "BNP Paribas",
            adresse = "Montpellier",
            cp ="34000",
            mail =  "contact1@entrepriseA.com",
            typeContrat = "CDD",
            remuneration = 3500.0,
            dateDebut = Timestamp(sdf.parse("01/10/2023")),
            dateFin = Timestamp(sdf.parse("01/04/2024")),
            description = "BNP Paribas est l'une des plus grandes banques de la zone euro. Nous offrons une large gamme de services financiers à nos clients, en mettant l'accent sur l'innovation et la durabilité.\n" +
                    "\n" +
                    "Si tu es passionné(e) par l'analyse financière et que tu souhaites rejoindre une équipe dynamique et internationale, cette offre de CDD en tant qu'analyste financier est faite pour toi ! Rejoins-nous à Lyon.\n" +
                    "\n" +
                    "Ton rôle et tes compétences :\n" +
                    "\n" +
                    "En tant qu'analyste financier, tu seras responsable de l'analyse des données financières, de l'évaluation des risques et de la préparation de rapports et de recommandations pour la direction. Tu travailleras en étroite collaboration avec les équipes financières et commerciales.\n" +
                    "\n" +
                    "Compétences requises :\n" +
                    "\n" +
                    " - Formation supérieure en finance, économie ou domaine similaire\n" +
                    " - Solides connaissances en analyse financière et en évaluation des risques\n" +
                    " - Capacité à travailler avec des données complexes et à en tirer des conclusions\n" +
                    " - Excellentes compétences en communication et en présentation\n" +
                    " - Maîtrise des outils informatiques et des logiciels d'analyse financière\n" +
                    "\n" +
                    "Les avantages à nous rejoindre :\n" +
                    "\n" +
                    " - Une expérience professionnelle au sein d'une grande institution financière\n" +
                    " - La possibilité de travailler sur des projets variés et stimulants\n" +
                    " - Un environnement de travail international et multiculturel\n" +
                    " - Des opportunités de développement professionnel et de formation\n" +
                    " - Une rémunération compétitive\n" +
                    "\n" +
                    "Postule dès maintenant pour rejoindre notre équipe et contribuer à notre succès !",
            dateCreation = Timestamp(Date())
        )
        ,Offre(
            id = "11",
            titre = "Alternance Assistant(e) Marketing - Bordeaux (F/H)",
            entreprise = "L'Oréal",
            adresse = "Bordeaux",
            cp ="33000",
            mail = "contact1@entrepriseA.com",
            typeContrat = "ALTERNANCE",
            remuneration = 2000.0,
            dateDebut = Timestamp(sdf.parse("01/09/2023")),
            dateFin = Timestamp(sdf.parse("01/09/2024")),
            description = "L'Oréal est le leader mondial de la beauté, présent dans plus de 150 pays. Nous offrons une large gamme de produits cosmétiques et de soins capillaires de haute qualité, dans le respect de l'environnement.\n" +
                    "\n" +
                    "Si tu es passionné(e) par le marketing et que tu souhaites acquérir une expérience professionnelle enrichissante au sein d'une entreprise reconnue, cette offre d'alternance en tant qu'assistant(e) marketing est faite pour toi ! Rejoins notre équipe à Bordeaux.\n" +
                    "\n" +
                    "Ton rôle et tes compétences :\n" +
                    "\n" +
                    "En tant qu'assistant(e) marketing, tu seras impliqué(e) dans diverses tâches liées aux activités de marketing, telles que la recherche de marché, l'analyse des concurrents, la création de supports marketing et la coordination des projets.\n" +
                    "\n" +
                    "Compétences requises :\n" +
                    "\n" +
                    " - Formation supérieure en marketing, communication ou domaine similaire\n" +
                    " - Capacité à mener des recherches de marché et à analyser les données\n" +
                    " - Excellentes compétences en communication écrite et verbale\n" +
                    " - Maîtrise des outils informatiques et des logiciels de marketing\n" +
                    "\n" +
                    "Les avantages à nous rejoindre :\n" +
                    "\n" +
                    " - Une expérience professionnelle au sein d'une grande entreprise de cosmétiques\n" +
                    " - La possibilité de travailler sur des marques de renommée internationale\n" +
                    " - Un encadrement et un suivi personnalisé tout au long de ton alternance\n" +
                    " - Une opportunité de développer ton réseau professionnel\n" +
                    " - Une ambiance de travail dynamique et créative\n" +
                    "\n" +
                    "Postule dès maintenant pour rejoindre notre équipe et contribuer à l'industrie de la beauté !",
            dateCreation = Timestamp(Date())
        )
        ,Offre(
            id = "12",
            titre = "Stage Développeur Mobile - Lyon (F/H)",
            entreprise = "Apple",
            adresse = "Lyon",
            cp ="69000",
            mail =  "contact1@entrepriseA.com",
            typeContrat = "STAGE",
            remuneration = 1200.0,
            dateDebut = Timestamp(sdf.parse("01/09/2023")),
            dateFin = Timestamp(sdf.parse("01/12/2023")),
            description = "Apple est une entreprise mondiale spécialisée dans la conception et la fabrication de produits électroniques de pointe. Nous sommes connus pour nos innovations dans le domaine des appareils mobiles et des technologies de communication.\n" +
                    "\n" +
                    "Si tu es passionné(e) par le développement mobile et que tu souhaites acquérir une expérience professionnelle enrichissante, cette offre de stage en tant que développeur mobile est faite pour toi ! Rejoins notre équipe à Lyon.\n" +
                    "\n" +
                    "Ton rôle et tes compétences :\n" +
                    "\n" +
                    "En tant que développeur mobile stagiaire, tu seras impliqué(e) dans le développement et l'amélioration de nos applications mobiles. Tu travailleras en étroite collaboration avec les équipes de développement pour créer des expériences utilisateur de qualité.\n" +
                    "\n" +
                    "Compétences requises :\n" +
                    "\n" +
                    " - Connaissance des langages de programmation mobile tels que Swift ou Kotlin\n" +
                    " - Compréhension des principes de conception d'interface utilisateur mobile\n" +
                    " - Capacité à travailler en équipe et à communiquer efficacement\n" +
                    " - Passion pour les technologies mobiles et la création d'applications innovantes\n" +
                    "\n" +
                    "Les avantages à nous rejoindre :\n" +
                    "\n" +
                    " - Une expérience professionnelle au sein d'une entreprise de renommée mondiale\n" +
                    " - La possibilité de travailler sur des projets innovants et d'envergure internationale\n" +
                    " - Un encadrement et un suivi personnalisé tout au long de ton stage\n" +
                    " - Une opportunité de développer tes compétences et ton réseau professionnel\n" +
                    " - Une ambiance de travail stimulante et créative\n" +
                    "\n" +
                    "Postule dès maintenant pour rejoindre notre équipe et contribuer à façonner l'avenir des technologies mobiles !",
            dateCreation =Timestamp(Date())
        )
        ,Offre(
            id = "13",
            titre = "Stage Analyste Marketing - Montpellier (F/H)",
            entreprise = "Procter & Gamble",
            adresse = "Montpellier",
            cp ="34000",
            mail =  "contact1@entrepriseB.com",
            typeContrat = "STAGE",
            remuneration = 1000.0,
            dateDebut = Timestamp(sdf.parse("01/10/2023")),
            dateFin = Timestamp(sdf.parse("01/02/2024")),
            description = "Procter & Gamble est une entreprise multinationale spécialisée dans les produits de consommation. Nous proposons une large gamme de marques populaires dans les domaines de la beauté, des soins personnels et de l'entretien ménager.\n" +
                    "\n" +
                    "Si tu es passionné(e) par le marketing et que tu souhaites acquérir une expérience professionnelle enrichissante, cette offre de stage en tant qu'analyste marketing est faite pour toi ! Rejoins notre équipe à Paris.\n" +
                    "\n" +
                    "Ton rôle et tes compétences :\n" +
                    "\n" +
                    "En tant qu'analyste marketing stagiaire, tu seras impliqué(e) dans l'analyse des données marketing, la réalisation d'études de marché et la préparation de recommandations pour les équipes commerciales et marketing.\n" +
                    "\n" +
                    "Compétences requises :\n" +
                    "\n" +
                    " - Formation supérieure en marketing, économie ou domaine similaire\n" +
                    " - Capacité à analyser des données complexes et à en tirer des conclusions\n" +
                    " - Excellentes compétences en communication et en présentation\n" +
                    " - Maîtrise des outils informatiques et des logiciels d'analyse de données\n" +
                    "\n" +
                    "Les avantages à nous rejoindre :\n" +
                    "\n" +
                    " - Une expérience professionnelle au sein d'une entreprise internationale\n" +
                    " - La possibilité de travailler sur des marques renommées\n" +
                    " - Un encadrement et un suivi personnalisé tout au long de ton stage\n" +
                    " - Une opportunité de développer tes compétences et ton réseau professionnel\n" +
                    " - Une ambiance de travail stimulante et collaborative\n" +
                    "\n" +
                    "Postule dès maintenant pour rejoindre notre équipe et contribuer à notre succès dans l'industrie des produits de consommation !",
            dateCreation = Timestamp(Date())
        )
        ,Offre(
            id = "14",
            titre = "Stage Assistant(e) Ressources Humaines - Bordeaux (F/H)",
            entreprise = "Danone",
            adresse = "Bordeaux",
            cp ="33000",
            mail =  "contact1@entrepriseC.com",
            typeContrat = "STAGE",
            remuneration = 900.0,
            dateDebut = Timestamp(sdf.parse("01/09/2023")),
            dateFin = Timestamp(sdf.parse("01/01/2024")),
            description = "Danone est une entreprise internationale spécialisée dans l'industrie agroalimentaire. Nous proposons une large gamme de produits laitiers, d'eau en bouteille et de produits nutritionnels.\n" +
                    "\n" +
                    "Si tu es passionné(e) par les ressources humaines et que tu souhaites acquérir une expérience professionnelle enrichissante, cette offre de stage en tant qu'assistant(e) ressources humaines est faite pour toi ! Rejoins notre équipe à Bordeaux.\n" +
                    "\n" +
                    "Ton rôle et tes compétences :\n" +
                    "\n" +
                    "En tant qu'assistant(e) ressources humaines stagiaire, tu seras impliqué(e) dans diverses tâches liées à la gestion des talents, au recrutement, à la formation et au développement des employés.\n" +
                    "\n" +
                    "Compétences requises :\n" +
                    "\n" +
                    " - Formation supérieure en gestion des ressources humaines, psychologie du travail ou domaine similaire\n" +
                    " - Capacité à travailler avec des informations confidentielles et à respecter la confidentialité\n" +
                    " - Excellentes compétences en communication et en travail d'équipe\n" +
                    " - Maîtrise des outils informatiques et des logiciels de gestion des ressources humaines\n" +
                    "\n" +
                    "Les avantages à nous rejoindre :\n" +
                    "\n" +
                    " - Une expérience professionnelle au sein d'une entreprise internationale et engagée\n" +
                    " - La possibilité de travailler sur des projets RH variés\n" +
                    " - Un encadrement et un suivi personnalisé tout au long de ton stage\n" +
                    " - Une opportunité de développer tes compétences et ton réseau professionnel\n" +
                    " - Une ambiance de travail dynamique et collaborative\n" +
                    "\n" +
                    "Postule dès maintenant pour rejoindre notre équipe et contribuer à notre mission de fournir des produits alimentaires de qualité !",
            dateCreation =Timestamp(Date())
        )
        ,Offre(
            id = "15",
            titre = "CDI Ingénieur Logiciel - Toulouse (F/H)",
            entreprise = "Microsoft",
            adresse = "Toulouse",
            cp ="31000",
            mail = "contact1@entrepriseD.com",
            typeContrat = "CDI",
            remuneration = 5000.0,
            dateDebut = Timestamp(sdf.parse("01/09/2023")),
            dateFin = null,
            description = "Microsoft, leader mondial de la technologie, recherche un(e) ingénieur(e) logiciel passionné(e) pour rejoindre notre équipe à Toulouse." +
                    " Expérience en développement C++ et connaissances en systèmes d'exploitation requises.",
            dateCreation = Timestamp(Date())
        )
        ,Offre(
            id = "16",
            titre = "CDI Chef de Projet Marketing - Paris (F/H)",
            entreprise = "Coca-Cola",
            adresse = "Paris",
            cp ="75000",
            mail =  "contact1@entrepriseE.com",
            typeContrat = "CDI",
            remuneration = 4500.0,
            dateDebut = Timestamp(sdf.parse("01/10/2023")),
            dateFin = null,
            description = "Coca-Cola recherche un(e) chef de projet marketing motivé(e) pour rejoindre notre équipe à Paris. " +
                    "Expérience en marketing et excellentes compétences en communication requises.",
            dateCreation =Timestamp(Date())
        )
        ,Offre(
            id = "17",
            titre = "CDI Analyste Financier - Lyon (F/H)",
            entreprise = "KPMG",
            adresse = "Lyon",
            cp ="69000",
            mail =  "contact1@entrepriseF.com",
            typeContrat = "CDI",
            remuneration = 4000.0,
            dateDebut = Timestamp(sdf.parse("01/09/2023")),
            dateFin = null,
            description = "KPMG, cabinet d'audit et de conseil, recherche un(e) analyste financier(e) pour rejoindre notre équipe à Lyon. " +
                    "Formation en finance et solides compétences analytiques requises.",
            dateCreation = Timestamp(sdf.parse("15/04/2023"))
        )
        ,Offre(
            id = "18",
            titre = "CDD Technicien de Maintenance - Lyon (F/H)",
            entreprise = "Total",
            adresse = "Lyon",
            cp ="69000",
            mail =  "contact1@entrepriseG.com",
            typeContrat = "CDD",
            remuneration = 3000.0,
            dateDebut = Timestamp(sdf.parse("01/09/2023")),
            dateFin = Timestamp(sdf.parse("01/03/2024")),
            description = "Total est un acteur majeur de l'industrie pétrolière et gazière. Nous sommes à la recherche d'un(e) technicien(ne) de maintenance pour rejoindre notre équipe à Lyon. Expérience en maintenance industrielle et connaissances des équipements pétroliers requis.\n" +
                    "\n" +
                    "Ton rôle et tes compétences :\n" +
                    "\n" +
                    "En tant que technicien(ne) de maintenance, tu seras responsable de l'entretien préventif et curatif des installations. Capacité à diagnostiquer et à résoudre les problèmes techniques. Connaissance des normes de sécurité et respect des procédures exigées.\n" +
                    "\n" +
                    "Compétences requises :\n" +
                    "\n" +
                    " - Formation technique en maintenance industrielle ou domaine similaire\n" +
                    " - Expérience dans la maintenance d'équipements pétroliers\n" +
                    " - Capacité à travailler en équipe et sous pression\n" +
                    " - Connaissance des normes de sécurité et des procédures de maintenance\n" +
                    "\n" +
                    "Les avantages à nous rejoindre :\n" +
                    "\n" +
                    " - Une expérience professionnelle au sein d'une entreprise leader dans le secteur de l'énergie\n" +
                    " - La possibilité de travailler sur des installations technologiquement avancées\n" +
                    " - Un encadrement et un suivi personnalisé tout au long de ton contrat\n" +
                    " - Des opportunités de développement professionnel\n" +
                    " - Une rémunération compétitive\n" +
                    "\n" +
                    "Postule dès maintenant pour rejoindre notre équipe et contribuer à notre mission énergétique !",
            dateCreation =Timestamp(Date())
        )
        ,Offre(
            id = "19",
            titre = "CDD Assistant(e) Administratif - Paris (F/H)",
            entreprise = "Airbnb",
            adresse = "Paris",
            cp ="75000",
            mail =  "contact1@entrepriseH.com",
            typeContrat = "CDD",
            remuneration = 2500.0,
            dateDebut = Timestamp(sdf.parse("01/10/2023")),
            dateFin = Timestamp(sdf.parse("01/02/2024")),
            description = "Airbnb, la plateforme de location de logements, recherche un(e) assistant(e) administratif(ve) pour rejoindre notre équipe à Paris. Gestion des tâches administratives, organisation des réunions et soutien à l'équipe. Expérience administrative et excellentes compétences en communication requises.\n" +
                    "\n" +
                    "Ton rôle et tes compétences :\n" +
                    "\n" +
                    "En tant qu'assistant(e) administratif(ve), tu seras chargé(e) de fournir un soutien administratif à l'équipe. Capacité à gérer les tâches administratives quotidiennes, à planifier et à coordonner les réunions. Excellentes compétences en communication et en organisation.\n" +
                    "\n" +
                    "Compétences requises :\n" +
                    "\n" +
                    " - Expérience en tant qu'assistant(e) administratif(ve) ou dans un rôle similaire\n" +
                    " - Maîtrise des outils informatiques et des logiciels de bureautique\n" +
                    " - Excellentes compétences en communication et en organisation\n" +
                    " - Capacité à travailler de manière autonome et à gérer les priorités\n" +
                    "\n" +
                    "Les avantages à nous rejoindre :\n" +
                    "\n" +
                    " - Une expérience professionnelle au sein d'une entreprise innovante dans le secteur de l'hébergement\n" +
                    " - La possibilité de travailler au sein d'une équipe dynamique et multiculturelle\n" +
                    " - Un encadrement et un suivi personnalisé tout au long de ton contrat\n" +
                    " - Des opportunités de développement professionnel\n" +
                    " - Une ambiance de travail stimulante\n" +
                    "\n" +
                    "Postule dès maintenant pour rejoindre notre équipe et contribuer à notre mission de rendre les voyages accessibles à tous !",
            dateCreation = Timestamp(Date())
        )
        ,Offre(
            id = "20",
            titre = "CDD Commercial - Bordeaux (F/H)",
            entreprise = "Amazon",
            adresse = "Bordeaux",
            cp ="33000",
            mail =  "contact1@entrepriseI.com",
            typeContrat = "CDD",
            remuneration = 2800.0,
            dateDebut = Timestamp(sdf.parse("01/09/2023")),
            dateFin = Timestamp(sdf.parse("01/01/2024")),
            description = "Amazon, leader du commerce électronique, recherche un(e) commercial(e) pour rejoindre notre équipe à Bordeaux. Prospection de nouveaux clients, développement des ventes et suivi de la relation client. Expérience commerciale et excellentes compétences en négociation requises.\n" +
                    "\n" +
                    "Ton rôle et tes compétences :\n" +
                    "\n" +
                    "En tant que commercial(e), tu seras responsable de la prospection de nouveaux clients, de la gestion des relations clients existantes et de la réalisation des objectifs de vente. Capacité à négocier et à conclure des contrats. Expérience dans la vente et maîtrise des techniques de vente.\n" +
                    "\n" +
                    "Compétences requises :\n" +
                    "\n" +
                    " - Expérience dans la vente et la prospection de nouveaux clients\n" +
                    " - Excellentes compétences en communication et en négociation\n" +
                    " - Capacité à travailler de manière autonome et à atteindre les objectifs de vente\n" +
                    " - Connaissance des techniques de vente et des stratégies commerciales\n" +
                    "\n" +
                    "Les avantages à nous rejoindre :\n" +
                    "\n" +
                    " - Une expérience professionnelle au sein d'une entreprise leader dans le domaine du commerce électronique\n" +
                    " - La possibilité de travailler sur des projets stimulants et variés\n" +
                    " - Un encadrement et un suivi personnalisé tout au long de ton contrat\n" +
                    " - Des opportunités de développement professionnel\n" +
                    " - Une rémunération compétitive\n" +
                    "\n" +
                    "Postule dès maintenant pour rejoindre notre équipe et contribuer à notre mission d'offrir une expérience d'achat exceptionnelle à nos clients !",
            dateCreation =Timestamp(Date())
        )
    )

    val admins = listOf(
        Admin(
            adresseMail = "admin@gmail.com",
            motDePasse = "admin",
            role = "Admin"

        )
    )

    val gestionnaires = listOf(
        Gestionnaire(
            adresseMail = "gestionnaire1@gmail.com",
            motDePasse = "mdp",
            role = "Gestionnaire"
        ), Gestionnaire(
            adresseMail = "gestionnaire2@gmail.com",
            motDePasse = "mdp",
            role = "Gestionnaire"
        )
    )


    /*val postulers = listOf(
        Postuler(
            id = "1",
            userId = "john.doe@example.com",
            employerId = "contact1@entrepriseA.com",
            offerId = "BtX6NKS2T0mv690wtxyD", //a update
            nom = "John",
            prenom = "Doe",
            dateNaissance = "01-01-1990",
            nationalite = "Américain",
            cv = "https://firebasestorage.googleapis.com/v0/b/interimexpress-814be.appspot.com/o/CV1.pdf?alt=media&token=0006defd-befb-4a44-a77d-6d454149d9ff",
            lm = "https://firebasestorage.googleapis.com/v0/b/interimexpress-814be.appspot.com/o/LM1.pdf?alt=media&token=4c622ef7-9c1e-4e82-a4b7-07c4262a7d77",
            traite=0,
            accept=0
        )
    )*/

    val agences = listOf(
        Agence(
            adresseMail = "contact1@entrepriseB.com",
            nomAgence = "Entreprise B",
            nomService = "Service B1",
            nomSousService = "Sous-Service B1.1",
            numeroSiret = "12345678901234",
            nomContact1 = "White",
            prenomContact1 = "Derrick",
            nomContact2 = "Tatum",
            prenomContact2 = "Jayson",
            adresseMail2 = "contact2@entrepriseB.com",
            motDePasse = "password123",
            role = "Agence",
            numTelephone1 = "+1234567890",
            numTelephone2 = "+0987654321",
            adresse = "123 Rue de l'Exemple",
            codePostal = "75000",
            ville = "Paris",
            lienSiteWeb = "www.entrepriseB.com",
            lienLinkedin = "www.linkedin.com/in/entrepriseB",
            lienFacebook = "www.facebook.com/entrepriseB"
        )
    )




}