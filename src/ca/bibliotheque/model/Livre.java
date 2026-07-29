package ca.bibliotheque.model;

import ca.bibliotheque.exception.DonneesInvalidesException;

public class Livre extends Document {
    private String isbn;

    public Livre(String id, String titre, String auteurOuEditeur, int anneePublication, boolean disponible, int nbEmprunts, String isbn)
            throws DonneesInvalidesException {
        super(id, titre, auteurOuEditeur, anneePublication, disponible, nbEmprunts);

        if (isbn == null || isbn.trim().isEmpty()) {
            throw new DonneesInvalidesException("L'ISBN ne peut pas être vide pour le livre ID : " + id);
        }
        this.isbn = isbn;
    }

    public String getIsbn() { return isbn; }

    @Override
    public String getType() {
        return "Livre";
    }

    @Override
    public String toString() {
        return super.toString() + " | ISBN: " + isbn;
    }
}