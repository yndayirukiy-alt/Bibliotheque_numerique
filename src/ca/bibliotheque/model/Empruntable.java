package ca.bibliotheque.model;

import ca.bibliotheque.exception.DocumentIndisponibleException;

public interface Empruntable {

        void emprunter() throws DocumentIndisponibleException;
        void retourner();
        boolean estDisponible();
}
