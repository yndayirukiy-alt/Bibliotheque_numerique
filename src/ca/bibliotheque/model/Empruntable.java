package ca.bibliotheque.model;

public interface Empruntable {

        void emprunter() throws DocumentIndisponibleException;
        void retourner();
        boolean estDisponible();
}
