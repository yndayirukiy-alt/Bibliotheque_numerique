package ca.bibliotheque.model;

import ca.bibliotheque.exception.DonneesInvalidesException;

public class Magazine extends Document {
    private String numeroEdition;

    public Magazine(String id, String titre, String auteurOuEditeur, int anneePublication, boolean disponible, int nbEmprunts, String numeroEdition)
            throws DonneesInvalidesException {
        super(id, titre, auteurOuEditeur, anneePublication, disponible, nbEmprunts);

        if (numeroEdition == null || numeroEdition.trim().isEmpty()) {
            throw new DonneesInvalidesException("Le numéro d'édition ne peut pas être vide pour le magazine ID : " + id);
        }
        this.numeroEdition = numeroEdition;
    }

    public String getNumeroEdition() { return numeroEdition; }

    @Override
    public String getType() {
        return "Magazine";
    }

    @Override
    public String toString() {
        return super.toString() + " | N° Édition: " + numeroEdition;
    }
}