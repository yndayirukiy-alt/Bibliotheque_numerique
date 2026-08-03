
# Projet : Bibliothèque Numérique et Gestion des Emprunts (Java)

-----
- **Membres du groupe :**
    - Yvan Ndayirukiye (Modèles de données & Exceptions)
    - Jean Baptiste Guetchine (Services & Classe Main)
- **Cours :** Programmation Avancée

---

## Description du projet
Cette application Java permet de gérer le catalogue d'une bibliothèque numérique comprenant divers types de documents (Livres, Ebooks, Magazines). Elle assure la lecture et la validation automatique des données à partir d'un fichier CSV, gère les opérations d'emprunt et de retour, et génère un rapport statistique complet.

-----------------------

## Fonctionnalités développées
- **Chargement et validation CSV :** Importation des 15 documents depuis `donnees_bibliotheque.csv` avec gestion des lignes corrompues/invalides.
- **Gestion des emprunts et retours :** Contrôle de disponibilité et levée d'exceptions personnalisées.
- **Analyse statistique :** Calcul de la répartition par catégorie, du taux d'utilisation, du Top 3 des emprunts et des documents non empruntés.
- **Génération de rapport :** Exportation automatique des métriques calculées dans le fichier `rapport_statistiques.txt`.
-----

## Application des principes SOLID
- **SRP (Single Responsibility Principle) :** Chaque classe possède une responsabilité unique (ex: `GestionnaireFichierCSV` pour le I/O, `GestionnaireEmprunts` pour la logique métier, `GenerateurStatistiques` pour les calculs).
- **OCP (Open/Closed Principle) :** La classe abstraite `Document` permet d'ajouter de nouveaux types de documents (ex: `Audiobook`) sans modifier le code de gestion existant.

---