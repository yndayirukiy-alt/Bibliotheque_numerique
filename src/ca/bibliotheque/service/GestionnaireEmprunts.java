package ca.bibliotheque.service;

import ca.bibliotheque.exception.DocumentIndisponibleException;
import ca.bibliotheque.model.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Service dédié à la gestion des opérations d'emprunt et de retour.
 * Respecte le principe de responsabilité unique (SRP).
 */
public class GestionnaireEmprunts {

    private final List<Document> documents;

    public GestionnaireEmprunts(List<Document> documents) {
        this.documents = documents;
    }

    /**
     * Recherche un document par son identifiant unique.
     */
    public Document trouverParId(String id) {
        for (Document doc : documents) {
            if (doc.getId().equalsIgnoreCase(id)) {
                return doc;
            }
        }
        return null;
    }

    /**
     * Recherche des documents contenant un mot-clé dans le titre.
     */
    public List<Document> rechercherParTitre(String motCle) {
        List<Document> resultats = new ArrayList<>();
        for (Document doc : documents) {
            if (doc.getTitre().toLowerCase().contains(motCle.toLowerCase())) {
                resultats.add(doc);
            }
        }
        return resultats;
    }

    /**
     * Effectue l'emprunt d'un document.
     *
     * @param id Identifiant du document à emprunter
     * @throws DocumentIndisponibleException si le document est déjà emprunté ou introuvable
     */
    public void effectuerEmprunt(String id) throws DocumentIndisponibleException {
        Document doc = trouverParId(id);

        if (doc == null) {
            throw new DocumentIndisponibleException("Impossible d'emprunter : le document ID '" + id + "' n'existe pas.");
        }

        // Appelle la méthode emprunter() de l'objet Document
        doc.emprunter();
        System.out.println("✅ Emprunt réussi : '" + doc.getTitre() + "' (ID: " + doc.getId() + ")");
    }

    /**
     * Effectue le retour d'un document.
     *
     * @param id Identifiant du document à retourner
     */
    public void effectuerRetour(String id) {
        Document doc = trouverParId(id);

        if (doc == null) {
            System.err.println("❌ Impossible de retourner : le document ID '" + id + "' n'existe pas.");
            return;
        }

        if (doc.estDisponible()) {
            System.out.println("⚠️ Le document '" + doc.getTitre() + "' était déjà en bibliothèque.");
            return;
        }

        doc.retourner();
        System.out.println("✅ Retour réussi : '" + doc.getTitre() + "' (ID: " + doc.getId() + ")");
    }
}