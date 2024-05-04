# Morpion Solitaire - Documentation

## Introduction

**Morpion Solitaire** est un jeu passionnant destiné à un seul joueur, qui se pratique sur une feuille à carreaux traditionnelle. Ce projet vise à la création d'une application Android qui simule ce jeu, en offrant une expérience immersive et interactive. L'objectif est de développer cette application en Java, en s'appuyant sur les connaissances acquises en cours et en utilisant exclusivement des classes originales ou celles discutées en classe.

## Règles du Jeu

Au départ, le jeu commence avec une configuration initiale de 36 intersections arrangées en forme de croix grecque sur la feuille. Le joueur peut ensuite dessiner une ligne (horizontale, verticale, ou diagonale) reliant cinq intersections, dont quatre déjà marquées et une nouvelle. La nouvelle intersection devient marquée, et le jeu continue ainsi. Une ligne ne peut partager qu'une seule intersection avec une ligne existante. La partie prend fin quand aucun coup supplémentaire n'est possible, et le score final correspond au nombre de coups joués.

## Objectifs du Projet

- Développer l'application en respectant les consignes de programmation fournies, exclusivement en Java.
- Implémenter une interface utilisateur intuitive comportant un menu de démarrage et une vue de jeu.
- Gérer les interactions de l'utilisateur de manière fluide et réactive.
- Intégrer une fonctionnalité permettant au joueur de recentrer la vue sur la croix initiale à tout moment.
- Permettre au jeu de détecter automatiquement la fin d'une partie et d'afficher le score final de manière permanente.
- Assurer la conservation de l'état du jeu lors des interruptions ou mises en pause.
- Offrir des options de configuration pour modifier les règles du jeu.

## Fonctionnalités

### Menu Principal

- **Démarrer une partie** : Lance une nouvelle session de jeu.
- **Options** : Permet d'accéder aux réglages où l'on peut activer des règles optionnelles.

### Jeu

- **Navigation** : Le joueur peut faire défiler la feuille dans toutes les directions et recentrer la vue sur la position initiale.
- **Jouabilité** : Les coups se jouent en traçant une ligne d'un geste. Les mouvements non autorisés sont ignorés.
- **Fin de partie** : Le jeu annonce la fin de la partie et affiche le score. Le joueur peut ensuite naviguer librement sur la feuille.
- **Pause et reprise** : Le jeu sauvegarde l'état de la partie en cas de pause ou d'interruption.

### Options

- **Taille de la croix initiale** : Permet de commencer avec une croix plus petite de 24 intersections.
- **Prolongation des lignes** : Interdit de tracer une ligne prolongeant une autre ligne
- **Mode de Triche**: permet de visualiser une croix que le joueur peut faire apparaitre en traçant la ligne correspondante