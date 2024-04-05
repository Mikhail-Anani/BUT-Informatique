#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include "biblio.h"

int main(int argc, char *argv[]) {
    init();

    if (argc != 5 || (strcmp(argv[1], "-e") != 0 && strcmp(argv[1], "-d") != 0)) {
        fprintf(stderr, "Utilisation : %s -e|-d <fichier_clef> <fichier_in> <fichier_out>\n", argv[0]);
        return EXIT_FAILURE;
    }

    big_n n, e, d, messageEntree, messageSortie;
    FILE *fichierClef = fopen(argv[2], "r");
    FILE *fichierEntree = fopen(argv[3], "r");
    FILE *fichierSortie = fopen(argv[4], "w");

    if (fichierClef == NULL || fichierEntree == NULL || fichierSortie == NULL) {
        perror("Erreur lors de l'ouverture du fichier");
        return EXIT_FAILURE;
    }

    read_hex(fichierClef, n);
    read_hex(fichierClef, e);
    read_hex(fichierClef, d);
    read_hex(fichierEntree, messageEntree);

    if (strcmp(argv[1], "-e") == 0) {
        exp_mod(messageEntree, e, n, messageSortie);
        write_hex(fichierSortie, messageSortie, '\n');
    }
    else if (strcmp(argv[1], "-d") == 0) {
        exp_mod(messageEntree, d, n, messageSortie);
        write_hex(fichierSortie, messageSortie, '\n');
    }

    fclose(fichierClef);
    fclose(fichierEntree);
    fclose(fichierSortie);

    big_n a, m, i1, i2;
    num_here("568754",a);
    num_here("45527",m);
    num_here("40203 ",i1);
    mod_inv(a,m,i2);
    assert(cp_abs(i1, i2) == 0);

    return EXIT_SUCCESS;
}