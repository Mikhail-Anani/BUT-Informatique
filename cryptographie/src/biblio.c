#include <stdio.h>
#include <stdlib.h>
#include "biblio.h"

big_n ZERO, UN, DEUX;



void mod_inv(big_n a, big_n m, big_n res) {
    big_n copieM, t, q, x, copieA;
    copy_big(a, copieA);
    copy_big(m, copieM);
    num_here("0", x);
    num_here("1", res);

    while(cp_abs(copieA, UN) == -1){ 
        div_big(copieM, copieA, q);
        copy_big(copieM, t);

        mod_big(copieM, copieA, copieM);
        copy_big(t, copieA);

        copy_big(x, t);
        mult_big(q, x, x);
        sub_big(x, res, x);

        copy_big(t, res);
    }

    while (res[PREC] == 1){ 
        add_big(m, res, res);
    }
}


void exp_mod(big_n a, big_n d, big_n n, big_n res) {
    big_n bit_poidFaible, pow, copieD;
    num_here("1", res);
    copy_big(a, pow);
    copy_big(d, copieD);

    while (cp_abs(copieD, ZERO) != 0) {
        mod_big(DEUX, copieD, bit_poidFaible);
        if (cp_abs(bit_poidFaible, UN) == 0) { 
            mult_big(res, pow, res);
            mod_big(n, res, res);
        }

        mult_big(pow, pow, pow);
        mod_big(n, pow, pow);

        div_int(2, copieD, copieD);
    }
}

void init(){
    num_here("0", ZERO);
    num_here("1", UN);
    num_here("2", DEUX);
}