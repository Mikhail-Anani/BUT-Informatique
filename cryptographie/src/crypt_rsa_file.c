#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <string.h>
#include "biblio.h"


void writeBigN(big_n data, FILE* fichierSortie, int Max){
    int lim = Max-data[0]+1;
    int i;
    unsigned int entier;
    if (Max == -1){
        Max = PREC-1;
        lim = 0;
    }
    for (i = Max; i >= lim; i--) {
        entier = data[i];
        fwrite(&entier, sizeof(unsigned int), 1, fichierSortie);
    }
}

int readBigN(FILE* f, big_n res, int Max){
    int add=1;
    unsigned int entier;
    int nbLu;
    zero_big(res);

    if (Max == -1){
        Max = PREC-1;
        add = 0;
    }

    int i=Max;

    do{
        nbLu = fread(&entier, sizeof(unsigned int), 1, f);
        if (nbLu > 0){
            res[i] = entier;
            i --;
        }
        else{
            if (add){
                res[0] = Max-i;
            }
            return 0;
        }
    } while (i>=add);

    if (add){
        res[0] = Max-i;
    }

    return 1;
}

int getNbIntInBigN(big_n n) {
    int i;
    for (i = PREC - 1; i >= 0; i--) {
        if (n[i] != 0) {
            return i+1;
        }
    }
    return 0;
}


void decryptageFichier(FILE* fichierEntree, FILE* fichierSortie, big_n n, big_n d) {
    big_n data, Decrypter_data;
    int Restebign;
    int tailleMaxData = getNbIntInBigN(n)-2;

    if (tailleMaxData < 0){
        perror("Votre clef n est trop petite");
        exit(EXIT_FAILURE);
    }

    do {
        Restebign = readBigN(fichierEntree, data, -1);
        exp_mod(data, d, n, Decrypter_data);
        writeBigN(Decrypter_data, fichierSortie, tailleMaxData);
    } while (Restebign);
}

void cryptageFichier(FILE* fichierEntree, FILE* fichierSortie, big_n n, big_n e) {

    big_n data, Encrypter_data;
    int Restebign;
    int tailleMaxData = getNbIntInBigN(n)-2;

    if (tailleMaxData < 0){
        perror("Clef n est trop petite");
        exit(EXIT_FAILURE);
    }
    do{
        Restebign = readBigN(fichierEntree, data, tailleMaxData);
        exp_mod(data, e, n, Encrypter_data);
        writeBigN(Encrypter_data, fichierSortie, -1);
    }while(Restebign);
}

int main(int argc, char *argv[]) {
    init();

    if (argc != 5 || (strcmp(argv[1], "-e") != 0 && strcmp(argv[1], "-d") != 0)) {
        fprintf(stderr, "Utilisation : %s -e|-d <fichier_clef> <fichier_in> <fichier_out>\n", argv[0]);
        return EXIT_FAILURE;
    }

    FILE* fichierEntree = fopen(argv[3], "r");
    FILE* fichierSortie = fopen(argv[4], "w");
    if (fichierEntree == NULL || fichierSortie == NULL) {
        perror("Erreur lors de l'ouverture du fichier");
        exit(EXIT_FAILURE);
    }

    big_n n, e, d;
    FILE *fichierClef = fopen(argv[2], "r");

    if (fichierClef == NULL) {
        perror("Erreur lors de l'ouverture du fichier");
        return EXIT_FAILURE;
    }

    read_hex(fichierClef, n);
    read_hex(fichierClef, e);
    read_hex(fichierClef, d);

    if (cp_abs(n, ZERO) == 0 || cp_abs(e, ZERO) == 0){
        perror("Merci de fournir un fichier clef valide");
        return EXIT_FAILURE;
    }

    if (strcmp(argv[1], "-e") == 0) {
        cryptageFichier(fichierEntree, fichierSortie, n, e);
    }
    else if (strcmp(argv[1], "-d") == 0) {
        if (cp_abs(d, ZERO) == 0){
            perror("Merci de fournir un fichier clef privee valide");
            return EXIT_FAILURE;
        }
        decryptageFichier(fichierEntree, fichierSortie, n, d);
    }

    fclose(fichierClef);
    fclose(fichierEntree);
    fclose(fichierSortie);

    return EXIT_SUCCESS;
}