package fr.iutfbleau.projetSAE2023.GroupeAlexisDjabrailMikhail;

import java.util.*;

import fr.iutfbleau.projetSAE2023.GroupeAlexisDjabrailMikhail.DicoCellule;

/**
 * La classe Argument permet de diviser des chaines de caracteres.
 * Elle a ete creer pour separer les differents arguments d'une formule mathematique
 * elle ne contient que des methodes statiques
 *
 * @version 1.0
 * @author Alexis, Djabrail, Mikhail
 */

public class Argument{ 

    /**
     * La methode splitFormule permet de diviser une formule mathematique en classant tous ses composants dans une liste
     * Pour savoir comment se fait le decoupage referez vous a la methode getFirstArgument
     *
     * @param formule la formule mathematique
     * @return la liste des composants de la formule (operateurs, nombres, noms de cellules, ...)
     */
    public static List<String> splitFormule(String formule){
        List<String> args = new ArrayList<>();
        String resteDeFormule = formule;
        IntObjet caractereATronquer = new IntObjet(0);
        while (resteDeFormule.length() > 0){
            String arg = Argument.getFirstArgument(resteDeFormule, caractereATronquer);
            if (arg != null){
                args.add(arg); // caractereATronquer est modifier dans getFirstArgument
            }
            resteDeFormule = resteDeFormule.substring(caractereATronquer.getValeur());
        }
        return args;
    }

    /**
     * La methode getFirstArgument permet de trouver le premier composant d'une formule mathematique
     * Voici comment sont decouper les arguments:
     * 1) Chaque caracteres blancs marque la fin d'un argument. il n'y auras jamais de caracteres blancs dans un argument.
     * 2) Un operateur marque aussi la fin d'un argument.
     * 3) Dans un argument avec operateur, l'operateur est le seul caractere sauf le '-'.
     * 4) Un argument commencant par '-' peut etre suivis d'un nombre ou d'une chaine
     * 5) Un nombre peut commencer par une virgule.
     *    Il s'arreteras a la rencontre d'une deuxieme virgule ou d'un caractere qui n'est pas un chiffre
     * 5) Une chaine de caractere commence par un caractere ni chiffre ni operateur ni blanc ni virgule.
     *    Une chaine ne contient pas de virgule et s'arrete a la premiere rencontrer.
     *    Si un chiffre est dans une chaine, la chaine s'arreteras a la fin de la suite de chiffre.
     * 
     * exemples d'arguments : -A8 -8.5 -.6 - + * / . .89 3.1415 666 AZE897 #Au_ null
     *
     * @param formule la formule mathematique
     * @param nbCaracterUtilises est un entier de type IntObjet (la valeur qu'il contiens n'est pas importante)
     * A la fin de l'execution de la methode la valeur qu'il contiendras
     * seras le nombre de caractere qui ont ete lu dans la formule afin de trouver le premier composant de la formule.
     * ex : avec la formule "  +A88   6.6", le premier composant seras "+"
     * et le nombre de caractere lu seras 3 (2 caracteres blancs et le '+')
     * @return le premier composant de la formule, null si la formule ne contient que des caracteres blancs
     */
    public static String getFirstArgument(String formule, IntObjet nbCaracterUtilises){
        StringBuilder argument = new StringBuilder();
        int i=0, taille = formule.length();
        boolean flag = false;
        boolean havePoint = false; //permet de savoir si au moins une virgule a ete mise

        if (taille > 0){
            char c = formule.charAt(i);
            ArgumentEnum typeActuel = null;
            ArgumentEnum typePrecedant = Argument.getType(c);
            havePoint = Argument.ajouter(argument, c, typePrecedant);

            for (i=1; i<taille && flag==false; i++){
                c = formule.charAt(i);
                typeActuel = Argument.getType(c);

                if (typePrecedant == ArgumentEnum.BLANC){
                    havePoint = Argument.ajouter(argument, c, typeActuel);
                }
                else if (typePrecedant == ArgumentEnum.STRING){
                    havePoint = true;
                    if (typeActuel == ArgumentEnum.STRING || typeActuel == ArgumentEnum.CHIFFRE){
                        argument.append(c);
                    }
                    else{
                        flag = true;
                    }
                }
                else if (typePrecedant == ArgumentEnum.CHIFFRE){
                    if (typeActuel == ArgumentEnum.CHIFFRE){
                        argument.append(c);
                    }
                    else if (typeActuel == ArgumentEnum.VIRGULE && havePoint == false){
                        havePoint = true;
                        argument.append('.');
                    }
                    else{
                        flag = true;
                    }
                }
                else if (typePrecedant == ArgumentEnum.VIRGULE && typeActuel == ArgumentEnum.CHIFFRE){
                    argument.append(c);
                }
                else if (typePrecedant == ArgumentEnum.MOINS && (typeActuel == ArgumentEnum.CHIFFRE || typeActuel == ArgumentEnum.VIRGULE || typeActuel == ArgumentEnum.STRING)){
                    havePoint = Argument.ajouter(argument, c, typeActuel);
                }
                else{
                    flag = true;
                }
                typePrecedant = typeActuel;
            }
        }
        if (i==taille && flag==false){
            i++;
        }

        nbCaracterUtilises.setValeur(i-1);
        System.out.println("\""+argument.toString()+"\" : "+nbCaracterUtilises.getValeur());
        if (argument.length() == 0){
            return null;
        }
        return argument.toString();
    }

    /**
     * Cette methode ajoute un caractere a un StringBuilder.
     * Cela ne fonctionne que si ce n'est pas un caractere blanc.
     *
     * @param chaine le StringBuilder a completer
     * @param c le caracetere a ajouter
     * @param typeDeC le type du caractere (Blanc, Operateur, Virgule, Chiffre, ...)
     * @return true si une virgule a ete ajouter false sinon
     */
    private static boolean ajouter(StringBuilder chaine, char c, ArgumentEnum typeDeC){
        if (typeDeC != ArgumentEnum.BLANC){
            if (typeDeC == ArgumentEnum.VIRGULE){
                chaine.append('.');
                return true;
            }
            chaine.append(c);
            return false;
        }
        return false;
    }

    /**
     * La methode getType permet de retourner le type d'un caractere (chiffre, moins, operateur, caractere blanc, ou autre)
     *
     * @param c le caractere a analyser
     * @return le type du caractere sous forme de ArgumentEnum
     */

     private static ArgumentEnum getType(char c){
        Operateur operateur = Calcul.getOperator(c);
        if (operateur != null){
            if (operateur == Operateur.SOUSTRACTION){
                return ArgumentEnum.MOINS;
            }
            return ArgumentEnum.OPERATEUR;
        }
        else if (c == '.' || c == ','){
            return ArgumentEnum.VIRGULE;
        }
        else if (Character.isDigit(c)){
            return ArgumentEnum.CHIFFRE;
        }
        else if (Character.isWhitespace(c)){
            return ArgumentEnum.BLANC;
        }
        else{
            return ArgumentEnum.STRING;
        }
    }
}