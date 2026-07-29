package ca.bibliotheque.service;

import ca.bibliotheque.model.Document;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Service dédié au calcul des statistiques de la bibliothèque et à la génération du rapport TXT.
 * Respecte le principe de responsabilité unique (SRP).
 */
public class GenerateurStatistiques {

    private final List<Document> documents;

    public GenerateurStatistiques(List<Document> documents) {
        this.documents = documents;
    }

    /**
     * Calcule le nombre de documents par type (Livre, Ebook, Magazine).
     */
    public Map<String, Integer> compterParCategorie() {
        Map<String, Integer> repartition = new HashMap<>();
        for (Document doc : documents) {
            String type = doc.getType();
            repartition.put(type, repartition.getOrDefault(type, 0) + 1);
        }
        return repartition;
    }

    /**
     * Calcule le taux d'utilisation (% de documents actuellement empruntés).
     */
    public double calculerTauxUtilisation() {
        if (documents.isEmpty()) return 0.0;

        long empruntes = 0;
        for (Document doc : documents) {
            if (!doc.estDisponible()) {
                empruntes++;
            }
        }
        return ((double) empruntes / documents.size()) * 100;
    }

    /**
     * Retourne la liste des documents les plus empruntés (triés par nbEmprunts décroissant).
     */
    public List<Document> obtenirPlusEmpruntes(int topN) {
        List<Document> copies = new ArrayList<>(documents);
        copies.sort((d1, d2) -> Integer.compare(d2.getNbEmprunts(), d1.getNbEmprunts()));
        return copies.subList(0, Math.min(topN, copies.size()));
    }

    /**
     * Retourne la liste des documents qui n'ont jamais été empruntés (nbEmprunts == 0).
     */
    public List<Document> obtenirJamaisEmpruntes() {
        List<Document> jamaisEmpruntes = new ArrayList<>();
        for (Document doc : documents) {
            if (doc.getNbEmprunts() == 0) {
                jamaisEmpruntes.add(doc);
            }
        }
        return jamaisEmpruntes;
    }

    /**
     * Génère un fichier TXT contenant le rapport détaillé des statistiques.
     */
    public void genererRapportTXT(String nomFichier) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichier))) {
            writer.write("====================================================\n");
            writer.write("      RAPPORT STATISTIQUE - BIBLIOTHÈQUE NUMÉRIQUE  \n");
            writer.write("====================================================\n\n");

            writer.write("1. TOTAL DE DOCUMENTS VALIDES : " + documents.size() + "\n\n");

            writer.write("2. RÉPARTITION PAR CATÉGORIE :\n");
            Map<String, Integer> repartition = compterParCategorie();
            for (Map.Entry<String, Integer> entry : repartition.entrySet()) {
                writer.write("   - " + entry.getKey() + " : " + entry.getValue() + "\n");
            }
            writer.write("\n");

            writer.write(String.format("3. TAUX D'UTILISATION ACTUEL : %.2f%%\n\n", calculerTauxUtilisation()));

            writer.write("4. TOP 3 DES DOCUMENTS LES PLUS EMPRUNTÉS :\n");
            List<Document> top3 = obtenirPlusEmpruntes(3);
            for (Document doc : top3) {
                writer.write("   - [" + doc.getType() + "] " + doc.getTitre() + " (" + doc.getNbEmprunts() + " emprunts)\n");
            }
            writer.write("\n");

            writer.write("5. DOCUMENTS JAMAIS EMPRUNTÉS :\n");
            List<Document> jamais = obtenirJamaisEmpruntes();
            if (jamais.isEmpty()) {
                writer.write("   Aucun document jamais emprunté.\n");
            } else {
                for (Document doc : jamais) {
                    writer.write("   - [" + doc.getType() + "] " + doc.getTitre() + " (ID: " + doc.getId() + ")\n");
                }
            }

            writer.write("\n====================================================\n");
            System.out.println("✅ Rapport généré avec succès dans le fichier : " + nomFichier);

        } catch (IOException e) {
            System.err.println("❌ Erreur lors de la génération du rapport TXT : " + e.getMessage());
        }
    }
}