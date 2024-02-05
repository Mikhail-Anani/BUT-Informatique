### Variables ###
CHEMIN=fr/iutfbleau/projetSAE2023/GroupeAlexisDjabrailMikhail
JC = javac -d build -sourcepath src #src/${CHEMIN}/*.java
JCFLAGS = -encoding UTF-8 -implicit:none
JVM = java
JAR = jar
JARFLAGS = -cvef

### ETAPES/REGLES DE COMPILATION ###


build:
	mkdir -p build/${CHEMIN}

all: Programme.jar

build/${CHEMIN}/Noeud.class : src/${CHEMIN}/Noeud.java 
	${JC} ${JCFLAGS} src/${CHEMIN}/Noeud.java	

build/${CHEMIN}/Arbre.class : src/${CHEMIN}/Arbre.java  build/${CHEMIN}/Noeud.class
	${JC} ${JCFLAGS} src/${CHEMIN}/Arbre.java

build/${CHEMIN}/Calcul.class : src/${CHEMIN}/Calcul.java 
	${JC} ${JCFLAGS} src/${CHEMIN}/Calcul.java	

build/${CHEMIN}/Cellule.class : src/${CHEMIN}/Cellule.java build/${CHEMIN}/Arbre.class
	${JC} ${JCFLAGS} src/${CHEMIN}/Cellule.java	

build/${CHEMIN}/DicoCellule.class : src/${CHEMIN}/DicoCellule.java build/${CHEMIN}/Cellule.class
	${JC} ${JCFLAGS} src/${CHEMIN}/DicoCellule.java	

build/${CHEMIN}/Fenetre.class : src/${CHEMIN}/Fenetre.java 
	${JC} ${JCFLAGS} src/${CHEMIN}/Fenetre.java	

build/${CHEMIN}/NoeudOperateur.class : src/${CHEMIN}/NoeudOperateur.java build/${CHEMIN}/Noeud.class
	${JC} ${JCFLAGS} src/${CHEMIN}/NoeudOperateur.java	

build/${CHEMIN}/NoeudValeur.class : src/${CHEMIN}/NoeudValeur.java build/${CHEMIN}/Noeud.class
	${JC} ${JCFLAGS} src/${CHEMIN}/NoeudValeur.java	

build/${CHEMIN}/NoeudValeurCellule.class : src/${CHEMIN}/NoeudValeurCellule.java build/${CHEMIN}/NoeudValeur.class build/${CHEMIN}/Cellule.class
	${JC} ${JCFLAGS} src/${CHEMIN}/NoeudValeurCellule.java	

build/${CHEMIN}/NoeudValeurDouble.class : src/${CHEMIN}/NoeudValeurDouble.java build/${CHEMIN}/NoeudValeur.class 
	${JC} ${JCFLAGS} src/${CHEMIN}/NoeudValeurDouble.java	

build/${CHEMIN}/Tableau.class : src/${CHEMIN}/Tableau.java build/${CHEMIN}/EvenementsCellule.class build/${CHEMIN}/Barre.class
	${JC} ${JCFLAGS} src/${CHEMIN}/Tableau.java

build/${CHEMIN}/EvenementsBarre.class : src/${CHEMIN}/EvenementsBarre.java
	${JC} ${JCFLAGS} src/${CHEMIN}/EvenementsBarre.java

build/${CHEMIN}/EvenementsCellule.class : src/${CHEMIN}/EvenementsCellule.java
	${JC} ${JCFLAGS} src/${CHEMIN}/EvenementsCellule.java

build/${CHEMIN}/Main.class : src/${CHEMIN}/Main.java  build/${CHEMIN}/DicoCellule.class
	${JC} ${JCFLAGS} src/${CHEMIN}/Main.java

build/${CHEMIN}/Barre.class : src/${CHEMIN}/Barre.java
	${JC} ${JCFLAGS} src/${CHEMIN}/Barre.java

build/${CHEMIN}/IntObjet.class : src/${CHEMIN}/IntObjet.java
	${JC} ${JCFLAGS} src/${CHEMIN}/IntObjet.java

build/${CHEMIN}/ArgumentEnum.class : src/${CHEMIN}/ArgumentEnum.java
	${JC} ${JCFLAGS} src/${CHEMIN}/ArgumentEnum.java

build/${CHEMIN}/Argument.class : src/${CHEMIN}/Argument.java  build/${CHEMIN}/ArgumentEnum.class  build/${CHEMIN}/IntObjet.class
	${JC} ${JCFLAGS} src/${CHEMIN}/Argument.java

build/${CHEMIN}/Boucle.class : src/${CHEMIN}/Boucle.java  build/${CHEMIN}/Cellule.class
	${JC} ${JCFLAGS} src/${CHEMIN}/Boucle.java

build/${CHEMIN}/Operateur.class : src/${CHEMIN}/Operateur.java
	${JC} ${JCFLAGS} src/${CHEMIN}/Operateur.java

### Créer le JAR exécutable Programme.jar en utilisant make ###
Programme.jar: \
	build/${CHEMIN}/Noeud.class \
	build/${CHEMIN}/Arbre.class \
	build/${CHEMIN}/Calcul.class \
	build/${CHEMIN}/Cellule.class \
	build/${CHEMIN}/DicoCellule.class \
	build/${CHEMIN}/Fenetre.class \
	build/${CHEMIN}/NoeudOperateur.class \
	build/${CHEMIN}/NoeudValeur.class \
	build/${CHEMIN}/NoeudValeurCellule.class \
	build/${CHEMIN}/NoeudValeurDouble.class \
	build/${CHEMIN}/Tableau.class \
	build/${CHEMIN}/EvenementsBarre.class \
	build/${CHEMIN}/EvenementsCellule.class \
	build/${CHEMIN}/Barre.class \
	build/${CHEMIN}/IntObjet.class \
	build/${CHEMIN}/ArgumentEnum.class \
	build/${CHEMIN}/Argument.class \
	build/${CHEMIN}/Boucle.class \
	build/${CHEMIN}/Operateur.class \
	build/${CHEMIN}/Main.class \

	# Créer le JAR avec les classes 
	${JAR} ${JARFLAGS} fr.iutfbleau.projetSAE2023.GroupeAlexisDjabrailMikhail.Main Programme.jar -C build ${CHEMIN}  

### Documentation ###

doc :
	-mkdir doc
	javadoc -d doc ./src/${CHEMIN}/*.java

### RULES ###

run : 
	${JVM} -jar Programme.jar

clean :
	rm -rf build
	rm -f Programme.jar
	rm -rf doc
	
###BUTS FACTICE###

.PHONY : run clean doc all