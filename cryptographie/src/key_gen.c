#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <assert.h>
#include "biblio.h"


void Random(big_n min, big_n max, big_n res) {
    int i = PREC-1;
    int flag=1;
    big_n diff;
    sub_big(min, max, diff);

    while (diff[i] == 0 && i>=0){
        res[i] = 0;
        i--;
    }
    while (i>=0 && flag == 1){
        res[i] = rand() % (diff[i] + 1);
        if (res[i] != diff[i]){
            flag = 0;
        }
        i--;
    }
    while (i>=0){
        res[i] = rand();
        i--;
    }

    add_big(min, res, res);
}

void RandomImpaire(big_n res, int bits) {
    big_n nb;
    int i;

    zero_big(nb);
    for (i=1; i<bits; i++){
        int new = rand() % 2;
        mult_int(2, nb, nb);
        add_int(new, nb, nb);
    }
    mult_int(2, nb, nb);
    add_int(1, nb, nb);
    copy_big(nb, res);
}


void calculerSD(big_n n, big_n s, big_n d){
    big_n t;
    zero_big(s);
    copy_big(n, d);
    sub_int(1, d, d);

    mod_big(DEUX,d,t);
    while (cp_abs(t, ZERO) == 0) {
        div_int(2, d, d);
        add_int(1, s, s);
        mod_big(DEUX,d,t);
    }

    big_n n1, d2, copieS;
    copy_big(s, copieS);
    sub_int(1,n,n1);
    num_here("1", d2);
    while(cp_abs(ZERO,copieS) == 1){
        mult_int(2,d2,d2);
        sub_int(1,copieS,copieS);
    }
    mult_big(d,d2,d2);
    assert(cp_abs(d2,n1)==0);
}


int Miller(big_n n, int tour){
    printf("|");
    fflush(stdout);
    int i;
    big_n a, n1, s, d, x, index;
    sub_int(1, n, n1);
    for (i=0; i<tour; i++){
        Random(DEUX, n1, a);
        assert(cp_abs(a, n)==1);
        assert(cp_abs(UN, a)==1);
        calculerSD(n, s, d);
        exp_mod(a, d, n, x);
        if (cp_abs(x, UN) == 0 || cp_abs(x, n1) == 0) {
            return 0;
        }
        num_here("1", index);
        for (copy_big(UN, index); cp_abs(index, s) == 1; add_int(1,index,index)) {
            mult_big(x, x, x);
            mod_big(n, x, x);

            if (cp_abs(x, n1) == 0) {
                return 0;
            }
        }
    }
    return 1;
}


void genererPremier(big_n res, int bits) {
    big_n nb;
    do{
        RandomImpaire(nb, bits);
    }
    while (Miller(nb, 20));
    copy_big(nb, res);
}


void ClefRSA(big_n n, big_n e, big_n d, int taille) {
    big_n p, q, p1, phiN;

    genererPremier(p, taille/2);
    genererPremier(q, taille/2);

    mult_big(p, q, n);

    sub_int(1, p, p1);
    sub_int(1, q, phiN);
    mult_big(p1, phiN, phiN);

    num_here("65537", e);

    mod_inv(e, phiN, d);

    big_n test;
    mult_big(e, d, test);
    mod_big(phiN, test, test);
    assert(cp_abs(UN, test)==0);
}

void sauvegarder(const char *FPublique, const char *FPrivee, big_n n, big_n e, big_n d) {

    FILE* fichierPub = fopen(FPublique, "w");
    FILE * fichierPriv = fopen(FPrivee, "w");

    if (fichierPub == NULL || fichierPriv == NULL) {
        perror("Erreur lors de l'ouverture des fichiers");
        exit(EXIT_FAILURE);
    }

    write_hex(fichierPub, n, '\n');
    write_hex(fichierPub, e, '\n');

    write_hex(fichierPriv, n, '\n');
    write_hex(fichierPriv, e, '\n');
    write_hex(fichierPriv, d, '\n');

    fclose(fichierPub);
    fclose(fichierPriv);
}

int main(int argc, char *argv[]) {
    init();

    srand(time(NULL));
    if (argc != 3) {
        fprintf(stderr, "Utilisation : %s <fichier_clef_publique> <fichier_clef_privee>\n", argv[0]);
        return EXIT_FAILURE;
    }

    big_n n, e, d;

    ClefRSA(n, e, d, 1024);
    printf("\n");
    sauvegarder(argv[1], argv[2], n, e, d);

    return EXIT_SUCCESS;
}