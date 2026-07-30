# Guetchine Jean Baptiste

## Module pris en charge : Services métier et Fichier principal Main

### 1. Contributions réalisées
Dans le cadre du Projet 1 (Bibliothèque numérique), j'ai pris en charge le traitement des données, la logique métier et le point d'entrée de l'application :

- Services de l'application (`ca.bibliotheque.service`) :
    - `GestionnaireFichierCSV.java` : Lecture du fichier `donnees_bibliotheque.csv`, parsing des lignes et capture des exceptions pour ignorer les lignes corrompues sans faire planter le programme.
    - `GestionnaireEmprunts.java` : Logique métier des opérations d'emprunt, de retour et de recherche de documents par ID ou par mot-clé.
    - `GenerateurStatistiques.java` : Calcul des métriques de la bibliothèque (taux d'utilisation, top 3 des emprunts, documents jamais empruntés) et export du rapport au format `.txt`.

- Programme principal (`Main.java`) :
    - Fichier principal orchestrant l'exécution complète du programme : chargement des 15+ documents CSV, exécution des démonstrations d'emprunts/retours et génération du fichier `rapport_statistiques.txt`.

---

### 2. Application des principes SOLID
- SRP (Single Responsibility Principle) : Séparation stricte des responsabilités entre la lecture du CSV (`GestionnaireFichierCSV`), la gestion des transactions (`GestionnaireEmprunts`) et la génération du rapport (`GenerateurStatistiques`).
