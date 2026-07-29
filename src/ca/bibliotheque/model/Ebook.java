package ca.bibliotheque.model;

import ca.bibliotheque.exception.DonneesInvalidesException;

public class Ebook extends Document {
    private String tailleFichier;

    public Ebook(String id, String titre, String auteurOuEditeur, int anneePublication, boolean disponible, int nbEmprunts, String tailleFichier)
            throws DonneesInvalidesException {
        super(id, titre, auteurOuEditeur, anneePublication, disponible, nbEmprunts);

        if (tailleFichier == null || tailleFichier.trim().isEmpty()) {
            throw new DonneesInvalidesException("La taille du fichier ne peut pas être vide pour l'Ebook ID : " + id);
        }
        this.tailleFichier = tailleFichier;
    }

    public String getTailleFichier() { return tailleFichier; }

    @Override
    public String getType() {
        return "Ebook";
    }

    @Override
    public String toString() {
        return super.toString() + " | Taille: " + tailleFichier;
    }
}
