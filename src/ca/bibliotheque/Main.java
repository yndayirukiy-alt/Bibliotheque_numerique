package ca.bibliotheque;

import ca.bibliotheque.model.Document;
import ca.bibliotheque.service.GestionnaireFichierCSV;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== CHARGEMENT DE LA BIBLIOTHÈQUE DEPUIS LE CSV ===\n");

        // Chargement du fichier CSV
        List<Document> bibliotheque = GestionnaireFichierCSV.chargerDocuments("donnees_bibliotheque.csv");

        System.out.println("--- LISTE DES DOCUMENTS CHARGÉS ---");
        for (Document doc : bibliotheque) {
            System.out.println(doc);
        }
    }
}