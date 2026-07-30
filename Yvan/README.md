# Yvan Ndayirukiye

## Module pris en charge : Modèles de données & Exceptions personnalisées

### 1. Contributions réalisées
Dans le cadre du Projet 1 (Bibliothèque numérique), j'ai pris en charge la conception de la structure Orientée Objet (POO) ainsi que la gestion robuste des erreurs :

- Hiérarchie de classes (`ca.bibliotheque.model`) :
  - `Document.java` : Classe abstraite de base regroupant les attributs communs (`id`, `titre`, `auteur`, `anneePublication`, `disponible`, `nbEmprunts`) et définissant le contrat des documents.
  - `Empruntable.java` : Interface définissant les méthodes métier `emprunter()` et `retourner()`.
  - `Livre.java` : Sous-classe représentant les livres papier (avec l'attribut spécifique `nbPages`).
  - `Ebook.java` : Sous-classe représentant les livres numériques (avec l'attribut spécifique `tailleMo` et le format).
  - `Magazine.java` : Sous-classe représentant les périodiques (avec l'attribut spécifique `numeroEdition`).

- Gestion des exceptions (`ca.bibliotheque.exception`) :
  - `DonneesInvalidesException.java` : Exception personnalisée levée lors du parsing de données corrompues (ex: année négative, format incorrect).
  - `DocumentIndisponibleException.java` : Exception personnalisée levée lorsqu'un utilisateur tente d'emprunter un document déjà emprunté ou inexistant.

---

### 2. Application des principes SOLID
- OCP (Open/Closed Principle) : La classe abstraite `Document` est ouverte à l'extension mais fermée à la modification. Si nous souhaitons ajouter un nouveau type de document (ex: `Audiobook`), il suffit de créer une nouvelle classe sans toucher au code existant.
- SRP (Single Responsibility Principle) : Le package `model` ne s'occupe que de la structure des données et des règles métier propres aux objets, laissant la lecture du fichier et l'affichage aux autres modules.
