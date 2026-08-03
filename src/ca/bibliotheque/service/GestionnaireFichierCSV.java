package ca.bibliotheque.service;

import ca.bibliotheque.exception.DonneesInvalidesException;
import ca.bibliotheque.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestionnaireFichierCSV {

        public static List<Document> chargerDocuments(String cheminFichier) {
        List<Document> documents = new ArrayList<>();
        int ligneCompteur = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;

            // Ignorer la première ligne (en-tête du CSV)
            String enTete = br.readLine();

            while ((ligne = br.readLine()) != null) {
                ligneCompteur++;
                // Ne pas traiter les lignes vides
                if (ligne.trim().isEmpty()) {
                    continue;
                }

                try {
                    Document doc = parseLigne(ligne);
                    documents.add(doc);
                } catch (DonneesInvalidesException e) {
                    System.err.println("⚠️ [Erreur Donnée Ligne " + (ligneCompteur + 1) + "] " + e.getMessage());
                } catch (NumberFormatException e) {
                    System.err.println("⚠️ [Erreur Format Ligne " + (ligneCompteur + 1) + "] Valeur numérique invalide dans : " + ligne);
                } catch (Exception e) {
                    System.err.println("⚠️ [Erreur Format Ligne " + (ligneCompteur + 1) + "] Ligne corrompue : " + ligne);
                }
            }

            System.out.println("✅ Charger effectué : " + documents.size() + " documents valides importés sur " + ligneCompteur + " lignes lues.\n");

        } catch (IOException e) {
            System.err.println("❌ Erreur lors de la lecture du fichier CSV : " + e.getMessage());
        }

        return documents;
    }

    /**
     * Transforme une ligne du fichier CSV en un objet Document (Livre, Ebook ou Magazine).
     */
    private static Document parseLigne(String ligne) throws DonneesInvalidesException {
        // Séparer les colonnes par virgule
        String[] champs = ligne.split(",");

        if (champs.length < 8) {
            throw new DonneesInvalidesException("Nombre de colonnes insuffisant (" + champs.length + "/8).");
        }

        String id = champs[0].trim();
        String type = champs[1].trim();
        String titre = champs[2].trim();
        String auteurOuEditeur = champs[3].trim();
        int anneePublication = Integer.parseInt(champs[4].trim());
        boolean disponible = Boolean.parseBoolean(champs[5].trim());
        int nbEmprunts = Integer.parseInt(champs[6].trim());
        String attributSpecifique = champs[7].trim();

        // Validation des valeurs négatives sur nbEmprunts
        if (nbEmprunts < 0) {
            throw new DonneesInvalidesException("Le nombre d'emprunts ne peut pas être négatif pour l'ID : " + id);
        }

        // Instanciation selon le type
        switch (type.toLowerCase()) {
            case "livre":
                return new Livre(id, titre, auteurOuEditeur, anneePublication, disponible, nbEmprunts, attributSpecifique);
            case "ebook":
                return new Ebook(id, titre, auteurOuEditeur, anneePublication, disponible, nbEmprunts, attributSpecifique);
            case "magazine":
                return new Magazine(id, titre, auteurOuEditeur, anneePublication, disponible, nbEmprunts, attributSpecifique);
            default:
                throw new DonneesInvalidesException("Type de document inconnu '" + type + "' pour l'ID : " + id);
        }
    }
}
