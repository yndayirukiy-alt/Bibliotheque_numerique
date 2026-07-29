package ca.bibliotheque;

import ca.bibliotheque.exception.DocumentIndisponibleException;
import ca.bibliotheque.model.Document;
import ca.bibliotheque.service.GestionnaireEmprunts;
import ca.bibliotheque.service.GestionnaireFichierCSV;
import ca.bibliotheque.service.GenerateurStatistiques;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SYSTÈME DE GESTION DE LA BIBLIOTHÈQUE NUMÉRIQUE ===\n");

        // 1. Chargement des données CSV
        List<Document> documents = GestionnaireFichierCSV.chargerDocuments("donnees_bibliotheque.csv");

        // 2. Initialisation des services
        GestionnaireEmprunts gestionnaireEmprunts = new GestionnaireEmprunts(documents);
        GenerateurStatistiques generateurStats = new GenerateurStatistiques(documents);

        // 3. Test de la gestion des emprunts et retours
        System.out.println("--- DEMO GESTION DES EMPRUNTS ---");
        try {
            // Tentative d'emprunt d'un document disponible (ex: DOC001)
            gestionnaireEmprunts.effectuerEmprunt("DOC001");
            
            // Tentative d'emprunt d'un document déjà emprunté (ex: DOC003)
            gestionnaireEmprunts.effectuerEmprunt("DOC003");
        } catch (DocumentIndisponibleException e) {
            System.err.println("❌ Exception capturée : " + e.getMessage());
        }

        // Test de retour (ex: DOC003)
        gestionnaireEmprunts.effectuerRetour("DOC003");

        // 4. Génération et export des statistiques
        System.out.println("\n--- GESTION DES STATISTIQUES ---");
        generateurStats.genererRapportTXT("rapport_statistiques.txt");
    }
}
