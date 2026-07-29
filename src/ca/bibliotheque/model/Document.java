package ca.bibliotheque.model;
import ca.bibliotheque.exception.DocumentIndisponibleException;
import ca.bibliotheque.exception.DonneesInvalidesException;

public abstract class Document implements Empruntable {
    private String id;
    private String titre;
    private String auteurOuEditeur;
    private int anneePublication;
    private boolean disponible;
    private int nbEmprunts;

    public Document(String id, String titre, String auteurOuEditeur, int anneePublication, boolean disponible, int nbEmprunts)
            throws DonneesInvalidesException {

        // Validation des données
        if (id == null || id.trim().isEmpty()) {
            throw new DonneesInvalidesException("L'identifiant du document ne peut pas être vide.");
        }
        if (anneePublication <= 0) {
            throw new DonneesInvalidesException("L'année de publication doit être supérieure à 0 pour le document ID : " + id);
        }

        this.id = id;
        this.titre = titre;
        this.auteurOuEditeur = auteurOuEditeur;
        this.anneePublication = anneePublication;
        this.disponible = disponible;
        this.nbEmprunts = nbEmprunts;
    }

    // Implémentation de l'interface Empruntable
    @Override
    public void emprunter() throws DocumentIndisponibleException {
        if (!disponible) {
            throw new DocumentIndisponibleException("Le document '" + titre + "' (ID: " + id + ") est déjà emprunté.");
        }
        this.disponible = false;
        this.nbEmprunts++;
    }

    @Override
    public void retourner() {
        this.disponible = true;
    }

    @Override
    public boolean estDisponible() {
        return disponible;
    }

    // Getters et Setters
    public String getId() { return id; }
    public String getTitre() { return titre; }
    public String getAuteurOuEditeur() { return auteurOuEditeur; }
    public int getAnneePublication() { return anneePublication; }
    public int getNbEmprunts() { return nbEmprunts; }

    // Méthode abstraite pour obtenir le type spécifique du document
    public abstract String getType();

    @Override
    public String toString() {
        return "[" + getType() + "] " + id + " - " + titre + " (" + auteurOuEditeur + ", " + anneePublication + ")" +
                " | Dispo: " + (disponible ? "Oui" : "Non") + " | Emprunts: " + nbEmprunts;
    }
}
