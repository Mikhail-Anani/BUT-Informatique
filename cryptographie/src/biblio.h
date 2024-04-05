#ifndef UTILE_H
#define UTILE_H

#include "big.h"

extern big_n ZERO, UN, DEUX;

void mod_inv(big_n a, big_n m, big_n res);
void exp_mod(big_n a, big_n d, big_n n, big_n res);
void init();

#endif