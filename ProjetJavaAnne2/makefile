### Variables ###
CHEMIN=fr/iutfbleau/projetIHM2023FI2/GroupeAchJLMIK
JC = javac -d build -sourcepath src src/${CHEMIN}/*.java
JCFLAGS = -encoding UTF-8 -implicit:none
JVM = java
JAR = jar
JARFLAGS = -cvef

### ETAPES/REGLES DE COMPILATION ###

#Programe1
build/${CHEMIN}/Main.class : src/${CHEMIN}/Main.java build/${CHEMIN}/ProtocolController.class build/${CHEMIN}/ProtocolModel.class build/${CHEMIN}/ProtocolView.class 
	${JC} ${JCFLAGS} src/${CHEMIN}/Main.java

build/${CHEMIN}/ProtocolInfoView.class : src/${CHEMIN}/ProtocolInfoView.java  build/${CHEMIN}/ProtocolController.class build/${CHEMIN}/ProtocolModel.class build/${CHEMIN}/ProtocolView.class 
	${JC} ${JCFLAGS} src/${CHEMIN}/ProtocolInfoView.java

build/${CHEMIN}/ProtocolController.class : src/${CHEMIN}/ProtocolController.java build/${CHEMIN}/ProtocolModel.class build/${CHEMIN}/ProtocolView.class build/${CHEMIN}/ValidationListener.class
	${JC} ${JCFLAGS} src/${CHEMIN}/ProtocolController.java

build/${CHEMIN}/ProtocolModel.class : src/${CHEMIN}/ProtocolModel.java build/${CHEMIN}/ProtocolView.class
	${JC} ${JCFLAGS} src/${CHEMIN}/ProtocolModel.java

build/${CHEMIN}/ProtocolView.class : src/${CHEMIN}/ProtocolView.java 
	${JC} ${JCFLAGS} src/${CHEMIN}/ProtocolView.java

build/${CHEMIN}/ValidationListener.class : src/${CHEMIN}/ValidationListener.java build/${CHEMIN}/ProtocolModel.class build/${CHEMIN}/ProtocolView.class
	${JC} ${JCFLAGS} src/${CHEMIN}/ValidationListener.java



#Programme2
build/${CHEMIN}/Main2.class : src/${CHEMIN}/Main2.java build/${CHEMIN}/CamenbertController.class build/${CHEMIN}/CamenbertModel.class build/${CHEMIN}/CamenbertView.class 
	${JC} ${JCFLAGS} src/${CHEMIN}/Main2.java

build/${CHEMIN}/CamenbertController.class : src/${CHEMIN}/CamenbertController.java  build/${CHEMIN}/CamenbertView.class 
	${JC} ${JCFLAGS} src/${CHEMIN}/CamenbertController.java

build/${CHEMIN}/CamenbertModel.class : src/${CHEMIN}/CamenbertModel.java build/${CHEMIN}/CamenbertController.class build/${CHEMIN}/CamenbertView.class
	${JC} ${JCFLAGS} src/${CHEMIN}/CamenbertModel.java

build/${CHEMIN}/CamenbertView.class : src/${CHEMIN}/CamenbertView.java build/${CHEMIN}/ValidationListener2.class
	${JC} ${JCFLAGS} src/${CHEMIN}/CamenbertView.java 

build/${CHEMIN}/CamenbertInfoView1.class : src/${CHEMIN}/CamenbertInfoView1.java  build/${CHEMIN}/CamenbertController.class build/${CHEMIN}/CamenbertModel.class build/${CHEMIN}/CamenbertView.class 
	${JC} ${JCFLAGS} src/${CHEMIN}/CamenbertInfoView1.java

build/${CHEMIN}/LeDeuxiemeCamembertView.class : src/${CHEMIN}/LeDeuxiemeCamembertView.java build/${CHEMIN}/LeDeuxiemeCamembertModel.class
	${JC} ${JCFLAGS} src/${CHEMIN}/LeDeuxiemeCamembertView.java

build/${CHEMIN}/LeDeuxiemeCamembertModel.class : src/${CHEMIN}/LeDeuxiemeCamembertModel.java 
	${JC} ${JCFLAGS} src/${CHEMIN}/LeDeuxiemeCamembertModel.java

build/${CHEMIN}/LeDeuxiemeCamembertController.class : src/${CHEMIN}/LeDeuxiemeCamembertController.java build/${CHEMIN}/LeDeuxiemeCamembertModel.class build/${CHEMIN}/LeDeuxiemeCamembertView.class
	${JC} ${JCFLAGS} src/${CHEMIN}/LeDeuxiemeCamembertController.java

build/${CHEMIN}/ValidationListener2.class : src/${CHEMIN}/ValidationListener2.java build/${CHEMIN}/CamenbertModel.class build/${CHEMIN}/CamenbertView.class
	${JC} ${JCFLAGS} src/${CHEMIN}/ValidationListener2.java

### Créer le JAR exécutable Programme1.jar en utilisant make ###
Programme1.jar: \
    build/${CHEMIN}/Main.class \
    build/${CHEMIN}/ProtocolController.class \
    build/${CHEMIN}/ProtocolModel.class \
    build/${CHEMIN}/ProtocolView.class \
    build/${CHEMIN}/ValidationListener.class \
    build/${CHEMIN}/ProtocolInfoView.class

	# Créer le JAR avec les classes et du pilote JDBC MariaDB
	${JAR} ${JARFLAGS} fr.iutfbleau.projetIHM2023FI2.GroupeAchJLMIK.Main Programme1.jar -C build ${CHEMIN} -C res/tmp .

### Créer le JAR exécutable Programme1.jar en utilisant make ###
Programme2.jar: \
    build/${CHEMIN}/Main2.class \
    build/${CHEMIN}/CamenbertController.class \
    build/${CHEMIN}/CamenbertModel.class \
    build/${CHEMIN}/CamenbertView.class \
    build/${CHEMIN}/ValidationListener2.class \
    build/${CHEMIN}/CamenbertInfoView1.class \
    build/${CHEMIN}/LeDeuxiemeCamembertView.class\
    build/${CHEMIN}/LeDeuxiemeCamembertModel.class\
    build/${CHEMIN}/LeDeuxiemeCamembertController.class
	# Créer le JAR avec les classes et du pilote JDBC MariaDB
	${JAR} ${JARFLAGS} fr.iutfbleau.projetIHM2023FI2.GroupeAchJLMIK.Main2 Programme2.jar -C build ${CHEMIN} -C res/tmp .

all: Programme1.jar Programme2.jar

### Documentation ###

doc :
	-mkdir doc
	javadoc -d doc ./src/${CHEMIN}/*.java

### RULES ###
run : 
	${JVM} -jar Programme1.jar
	${JVM} -jar Programme2.jar

clean :
	rm -rf build/${CHEMIN}/*.class
	rm -f Programme1.jar
	rm -f Programme2.jar
	rm -rf doc
	
###BUTS FACTICE###
.PHONY : run clean doc all
