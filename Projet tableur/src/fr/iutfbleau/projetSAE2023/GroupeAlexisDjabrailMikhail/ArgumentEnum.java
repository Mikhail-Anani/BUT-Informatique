package fr.iutfbleau.projetSAE2023.GroupeAlexisDjabrailMikhail;

/**
 * La classe ArgumentEnum permet de connaitre le type d'un caractere
 *
 * @version 1.0
 * @author Alexis, Djabrail, Mikhail
 */
public enum ArgumentEnum{
    /**
     * Le caractere est un chiffre ['0'-'9']
     */
    CHIFFRE,
    /**
     * Le caractere est une virgule ['.',',']
     */
    VIRGULE,
    /**
     * Le caractere est un moins ['-']
     */
    MOINS,
    /**
     * Le caractere est un operateur sauf un moins ['+','*','/']
     */
    OPERATEUR,
    /**
     * Le caractere est un caractere blanc ['\0','\n','\h',' ', ...]
     */
    BLANC,
    /**
     * Le caractere est un string (autre que ce qui a ete precedemment cite)
     */
    STRING
}