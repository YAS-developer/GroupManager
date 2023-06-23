# COMMANDES #
JAVAC = javac

WINDOWS = 1
# note $$ to get a single shell $

ifeq ($(WINDOWS),1)
	JAVAC_OPTIONS = -encoding UTF-8 -d build -classpath build -sourcepath "src:tmp" -implicit:none
else
	JAVAC_OPTIONS = -encoding UTF-8 -d build -cp build:$$CLASSPATH -implicit:none
endif

JAVA = java
JAR = jar
EXEC_JAR = ${JAVA} -jar

# CHEMINS RELATIFS
SRC = src/fr/iutfbleau/projetIHM2022FI2
BUILD = build/fr/iutfbleau/projetIHM2022FI2
DOC = doc/fr/iutfbleau/projetIHM2022FI2

# CHOIX NOMS
JAR_MNP = test-mnp.jar

# BUTS FACTICES #
.PHONY : run clean doc

# BUT PAR DEFAUT #
run : ${JAR_MNP}
	${EXEC_JAR} ${JAR_MNP} 

# AUTRE BUTS
doc :
	javadoc -d doc -noqualifier all src/fr/iutfbleau/projetIHM2022FI2/API/* src/fr/iutfbleau/projetIHM2022FI2/MNP/*.java src/fr/iutfbleau/projetIHM2022FI2/FRAME/ADMINISTRATEUR/*.java src/fr/iutfbleau/projetIHM2022FI2/FRAME/ENSEIGNANT/*.java src/fr/iutfbleau/projetIHM2022FI2/FRAME/ETUDIANT/*.java                                                              
# src/fr/iutfbleau/projetIHM2022FI2/MNP/*.java src/fr/iutfbleau/projetIHM2022FI2/MP/*.java src/fr/iutfbleau/projetIHM2022FI2/MP/*.java
clean :
	rm -rf ${BUILD}/* *.jar

# REGLES DE DEPENDANCE #

## API ##
${BUILD}/API/MonPrint.class : ${SRC}/API/MonPrint.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/MonPrint.java

${BUILD}/API/TypeGroupe.class : ${SRC}/API/TypeGroupe.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/TypeGroupe.java

${BUILD}/API/Groupe.class : ${SRC}/API/Groupe.java \
	  		     ${BUILD}/API/TypeGroupe.class\
			     ${BUILD}/API/MonPrint.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/Groupe.java

${BUILD}/API/Etudiant.class : ${SRC}/API/Etudiant.java \
                            ${BUILD}/API/MonPrint.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/Etudiant.java

${BUILD}/API/Changement.class : ${SRC}/API/Changement.java \
	  		            ${BUILD}/API/Etudiant.class \
	  		     	    ${BUILD}/API/Groupe.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/Changement.java

${BUILD}/API/AbstractGroupeFactory.class : ${SRC}/API/AbstractGroupeFactory.java \
				${BUILD}/API/Groupe.class \
	  		         ${BUILD}/API/Etudiant.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/AbstractGroupeFactory.java

${BUILD}/API/AbstractChangementFactory.class : ${SRC}/API/AbstractChangementFactory.java \
	  		            ${BUILD}/API/AbstractGroupeFactory.class \
				    ${BUILD}/API/Changement.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/AbstractChangementFactory.java

## MNP ##

${BUILD}/MNP/EtudiantNP.class : ${SRC}/MNP/EtudiantNP.java \
                              ${BUILD}/API/Etudiant.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MNP/EtudiantNP.java


${BUILD}/MNP/GroupeNP.class : ${SRC}/MNP/GroupeNP.java \
                              ${BUILD}/API/Groupe.class \
			      ${BUILD}/API/TypeGroupe.class \
                              ${BUILD}/API/Etudiant.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MNP/GroupeNP.java


${BUILD}/MNP/ChangementNP.class : ${BUILD}/API/Changement.class \
				${SRC}/MNP/ChangementNP.java \
                              ${BUILD}/API/Groupe.class \
                              ${BUILD}/API/Etudiant.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MNP/ChangementNP.java

${BUILD}/MNP/AbstractGroupeFactoryNP.class : ${SRC}/MNP/AbstractGroupeFactoryNP.java \
				${BUILD}/API/AbstractGroupeFactory.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MNP/AbstractGroupeFactoryNP.java

${BUILD}/MNP/AbstractChangementFactoryNP.class : ${SRC}/MNP/AbstractChangementFactoryNP.java \
				${BUILD}/API/AbstractChangementFactory.class \
				${BUILD}/API/Changement.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MNP/AbstractChangementFactoryNP.java


# BDD #

${BUILD}/BDD/Connexion.class : ${SRC}/BDD/Connexion.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/BDD/Connexion.java


## MP ##



${BUILD}/MP/EtudiantP.class : ${SRC}/MP/EtudiantP.java \
								${BUILD}/API/Etudiant.class \
								${BUILD}/BDD/Connexion.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MP/EtudiantP.java								

${BUILD}/MP/GroupeP.class : ${SRC}/MP/GroupeP.java \
								${BUILD}/API/Etudiant.class \
								${BUILD}/BDD/Connexion.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MP/GroupeP.java


${BUILD}/MP/ChangementP.class : ${SRC}/MP/ChangementP.java \
								${BUILD}/BDD/Connexion.class \
								${BUILD}/API/Changement.class \
								${BUILD}/API/Groupe.class \
								${BUILD}/API/Etudiant.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MP/ChangementP.java

${BUILD}/MP/AbstractGroupeFactoryP.class : ${SRC}/MP/AbstractGroupeFactoryP.java \
											${BUILD}/BDD/Connexion.class \
											${BUILD}/API/AbstractGroupeFactory.class \
											${BUILD}/MP/GroupeP.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MP/AbstractGroupeFactoryP.java	

${BUILD}/MP/AbstractChangementFactoryP.class : ${SRC}/MP/AbstractChangementFactoryP.java \
												${BUILD}/BDD/Connexion.class \
												${BUILD}/API/AbstractChangementFactory.class \
												 ${BUILD}/MP/AbstractGroupeFactoryP.class \
												${BUILD}/MP/ChangementP.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MP/AbstractChangementFactoryP.java										


## FRAME ##

${BUILD}/FRAME/ENSEIGNANT/%.class : ${SRC}/FRAME/ENSEIGNANT/FrameEns.java \
									${SRC}/FRAME/ENSEIGNANT/EventEns.java \
									${SRC}/FRAME/ENSEIGNANT/VueEtuparGroupe.java \
									${SRC}/FRAME/ENSEIGNANT/VueGroupe.java \
									${SRC}/FRAME/ENSEIGNANT/VueRechercheEtu.java \
									${SRC}/FRAME/EventAllFrame.java \
									${BUILD}/MNP/AbstractGroupeFactoryNP.class \
									${BUILD}/MNP/AbstractChangementFactoryNP.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/FRAME/ENSEIGNANT/*.java 


${BUILD}/FRAME/ETUDIANT/%.class : ${SRC}/FRAME/ETUDIANT/FrameEtu.java \
		 						${SRC}/FRAME/ETUDIANT/EventEtu.java \
								${SRC}/FRAME/ETUDIANT/VueDemande.java \
								${SRC}/FRAME/EventAllFrame.java \
									${BUILD}/MNP/AbstractGroupeFactoryNP.class \
									${BUILD}/MNP/AbstractChangementFactoryNP.class 
	${JAVAC} ${JAVAC_OPTIONS}  ${SRC}/FRAME/ETUDIANT/*.java 


${BUILD}/FRAME/ADMINISTRATEUR/%.class : ${SRC}/FRAME/ADMINISTRATEUR/FrameAdmin.java \
		 						${SRC}/FRAME/ADMINISTRATEUR/EventAdmin.java \
								${SRC}/FRAME/ADMINISTRATEUR/VuePrincipale.java \
								${SRC}/FRAME/ADMINISTRATEUR/VueGestionGroupe.java \
								${SRC}/FRAME/ADMINISTRATEUR/VueGestionEtudiant.java \
								${SRC}/FRAME/ADMINISTRATEUR/VueGestionDemande.java \
								${SRC}/FRAME/EventAllFrame.java \
									${BUILD}/MNP/AbstractGroupeFactoryNP.class \
									${BUILD}/MNP/AbstractChangementFactoryNP.class 
	${JAVAC} ${JAVAC_OPTIONS}  ${SRC}/FRAME/ADMINISTRATEUR/*.java 					



# ${BUILD}/FRAME/%.class : ${SRC}/FRAME/EventAllFrame.java \
# 						${BUILD}/FRAME/ADMINISTRATEUR/FrameAdmin.class \
# 						${BUILD}/FRAME/ETUDIANT/FrameEtu.class \
# 						${BUILD}/FRAME/ENSEIGNANT/FrameEns.class 
# 	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/FRAME/*.java 

# TEST ##

 ${BUILD}/Test/TestTexteMNP.class : ${SRC}/Test/TestTexteMNP.java \
			 ${BUILD}/MNP/EtudiantNP.class \
			 ${BUILD}/MNP/GroupeNP.class \
			 ${BUILD}/MNP/ChangementNP.class \
                         ${BUILD}/MNP/AbstractGroupeFactoryNP.class \
						 ${BUILD}/MNP/AbstractChangementFactoryNP.class \
						 		${BUILD}/BDD/Connexion.class \
								${BUILD}/MP/EtudiantP.class \
								${BUILD}/MP/GroupeP.class \
								${BUILD}/MP/ChangementP.class \
								${BUILD}/MP/AbstractGroupeFactoryP.class \
								${BUILD}/MP/AbstractChangementFactoryP.class \
									${BUILD}/FRAME/ADMINISTRATEUR/FrameAdmin.class \
									${BUILD}/FRAME/ADMINISTRATEUR/EventAdmin.class \
									${BUILD}/FRAME/ADMINISTRATEUR/VuePrincipale.class \
									${BUILD}/FRAME/ADMINISTRATEUR/VueGestionGroupe.class \
									${BUILD}/FRAME/ADMINISTRATEUR/VueGestionEtudiant.class \
									${BUILD}/FRAME/ADMINISTRATEUR/VueGestionDemande.class \
									${BUILD}/FRAME/ENSEIGNANT/FrameEns.class \
									${BUILD}/FRAME/ENSEIGNANT/EventEns.class \
									${BUILD}/FRAME/ENSEIGNANT/VueGroupe.class \
									${BUILD}/FRAME/ENSEIGNANT/VueEtuparGroupe.class \
									${BUILD}/FRAME/ENSEIGNANT/VueRechercheEtu.class \
									${BUILD}/FRAME/ETUDIANT/Frame.class \
									${BUILD}/FRAME/ETUDIANT/EventEtu.class \
									${BUILD}/FRAME/ETUDIANT/VueDemande.class

	${JAVAC} -Xlint:deprecation ${JAVAC_OPTIONS} ${SRC}/Test/TestTexteMNP.java 

# ## JARS ##

${JAR_MNP} : ${BUILD}/Test/TestTexteMNP.class
	${JAR} cvfe ${JAR_MNP} fr.iutfbleau.projetIHM2022FI2.Test.TestTexteMNP org/ -C build fr

# ${JAR} cvfe ${JAR_MNP} fr.iutfbleau.projetIHM2022FI2.Test.TestTexteMNP -C build fr
	

