### Programme génerer la Cle###
but: key_gen crypt_rsa crypt_rsa_file

### VARIABLES ###
CC = gcc
CFLAGS = -Wall -g
FLAG_LIB = -I./lib
LDFLAGS = -lm
BUILD = build
SRC = src
LIB = lib

### Creation des .o dans le build ###
$(BUILD)/big.o: $(LIB)/big.c $(LIB)/big.h
	$(CC) $(CFLAGS) $(FLAG_LIB) -c $(LIB)/big.c -o $(BUILD)/big.o

$(BUILD)/biblio.o: $(SRC)/biblio.c $(SRC)/biblio.h
	$(CC) $(CFLAGS) $(FLAG_LIB) -c $(SRC)/biblio.c -o $(BUILD)/biblio.o

$(BUILD)/key_gen.o: $(SRC)/key_gen.c $(SRC)/biblio.c $(SRC)/biblio.h
	$(CC) $(CFLAGS) $(FLAG_LIB) -c $(SRC)/key_gen.c -o $(BUILD)/key_gen.o

$(BUILD)/crypt_rsa.o: $(SRC)/crypt_rsa.c $(SRC)/biblio.c $(SRC)/biblio.h
	$(CC) $(CFLAGS) $(FLAG_LIB) -c $(SRC)/crypt_rsa.c -o $(BUILD)/crypt_rsa.o

$(BUILD)/crypt_rsa_file.o: $(SRC)/crypt_rsa_file.c $(SRC)/biblio.c $(SRC)/biblio.h
	$(CC) $(CFLAGS) $(FLAG_LIB) -c $(SRC)/crypt_rsa_file.c -o $(BUILD)/crypt_rsa_file.o


### Nom des but pour les executions du makefile ###
key_gen: $(BUILD)/key_gen.o $(BUILD)/big.o $(BUILD)/biblio.o
	$(CC) $(CFLAGS) $(BUILD)/key_gen.o $(BUILD)/big.o $(BUILD)/biblio.o $(LDFLAGS) -o key_gen

crypt_rsa: $(BUILD)/crypt_rsa.o $(BUILD)/big.o $(BUILD)/biblio.o
	$(CC) $(CFLAGS) $(BUILD)/crypt_rsa.o $(BUILD)/big.o $(BUILD)/biblio.o $(LDFLAGS) -o crypt_rsa

crypt_rsa_file: $(BUILD)/crypt_rsa_file.o $(BUILD)/big.o $(BUILD)/biblio.o
	$(CC) $(CFLAGS) $(BUILD)/crypt_rsa_file.o $(BUILD)/big.o $(BUILD)/biblio.o $(LDFLAGS) -o crypt_rsa_file

### Clean ###
clean:
	rm -f key_gen crypt_rsa crypt_rsa_file $(BUILD)/*.o


.PHONY: but clean
