package ca.bibliotheque.exception;

/**
 * Exception personnalisée levée lorsqu'une donnée lue ou saisie est invalide
 * (ex: année de publication négative, champ obligatoire vide, type inconnu).
 */
public class DonneesInvalidesException extends Exception {

    public DonneesInvalidesException(String message) {
        super(message);
    }
}