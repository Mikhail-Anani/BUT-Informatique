# Guide d'utilisation après compilation avec Makefile

Ce document détaille l'utilisation des outils générés après la compilation pour la gestion des clés RSA et le chiffrement/déchiffrement de fichiers ou d'entiers.

## Génération des Clés RSA

### Commande pour générer les clés

Pour commencer, vous devez générer une paire de clés (publique et privée) en utilisant la commande suivante :

```bash
 ./key_gen key.pub key.priv
```


## Utilisation de Crypt_rsa pour Chiffrement/Déchiffrement d'Entiers

### Chiffrement d'un Entier

Le fichier `crypt_rsa.c` est conçu pour le chiffrement et le déchiffrement d'entiers représentés en hexadécimal et stockés dans un fichier texte.

- Pour **chiffrer** un entier, utilisez la commande suivante :
  
```bash
  ./crypt_rsa -e key.pub fileEntier.in fileEntier.encrypt
```

- Pour **déchiffrer** un entier, utilisez la commande suivante :

```bash
 ./crypt_rsa -d key.priv fileEntier.encrypt fileEntier.decrypt
```


## Utilisation de Crypt_rsa_file pour Chiffrement/Déchiffrement d'un fichier

### Chiffrement d'un fichier

Le fichier `crypt_rsa_file.c` est conçu pour le chiffrement et le déchiffrement d'un fichier et stocké dans un fichier texte.

- Pour **chiffrer** un fichier, utilisez la commande suivante :

```bash
 ./crypt_rsa_file -e key.pub file.in file.encrypt
```

- Pour **déchiffrer** un fichier, utilisez la commande suivante :

```bash
 ./crypt_rsa_file -d key.priv file.encrypt file.decrypt
```


Cette documentation fournit des instructions claires et précises pour l'utilisation des commandes de chiffrement et de déchiffrement, ainsi que pour la génération de clés RSA, rendant le processus plus accessible pour les utilisateurs.


