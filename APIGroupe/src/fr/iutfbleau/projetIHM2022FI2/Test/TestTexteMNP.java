package fr.iutfbleau.projetIHM2022FI2.Test;

import fr.iutfbleau.projetIHM2022FI2.API.*;
// import fr.iutfbleau.projetIHM2022FI2.FRAME.EventAllFrame;
import fr.iutfbleau.projetIHM2022FI2.FRAME.ADMINISTRATEUR.FrameAdmin;
import fr.iutfbleau.projetIHM2022FI2.FRAME.ENSEIGNANT.*;
import fr.iutfbleau.projetIHM2022FI2.FRAME.ETUDIANT.FrameEtu;
import fr.iutfbleau.projetIHM2022FI2.MNP.*;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TestTexteMNP{


    public static void main(String[] args) {
        
        Etudiant e1=new EtudiantNP("AMOUSSA","Rayan");
        Etudiant e2=new EtudiantNP("BADE","Guy");
        Etudiant e3=new EtudiantNP("BAILLY","Joris");
        Etudiant e4=new EtudiantNP("BAZILLIO","Titouan");
        Etudiant e5=new EtudiantNP("BERNARD","Théo");
        Etudiant e6=new EtudiantNP("BESSON","Romain");
        Etudiant e7=new EtudiantNP("BOUARROUDJ","Yanis");
        Etudiant e8=new EtudiantNP("BOUDJEMLINE","Bilal");
        Etudiant e43=new EtudiantNP("BOUTET","Rémi");
        Etudiant e9=new EtudiantNP("BREZEKY","Ethan");
        Etudiant e10=new EtudiantNP("BRINET","Félix");


        
        

        // System.out.print(".");
        
        Etudiant e11=new EtudiantNP("CATANESE","Simon");
        Etudiant e12=new EtudiantNP("CHAABA","Hamza");
        Etudiant e13=new EtudiantNP("CHAIGNEAU","Mathis");
        Etudiant e14=new EtudiantNP("CHARBONNEL","Julien");
        Etudiant e15=new EtudiantNP("CORBIER","Florian");
        Etudiant e16=new EtudiantNP("DAGORNE","Thomas");
        Etudiant e17=new EtudiantNP("DANIEL","Gaël");
        Etudiant e18=new EtudiantNP("DE SOUSA ALVES","Etann");
        Etudiant e19=new EtudiantNP("DESCAMPS","Victor");
        Etudiant e20=new EtudiantNP("DOS SANTOS","Adrien");

        // System.out.print(".");
        
        Etudiant e21=new EtudiantNP("FOUCHÉ","Joffrey");
        Etudiant e22=new EtudiantNP("GOBERT ","Claire");
        Etudiant e23=new EtudiantNP("GUETTAF","Adem");
        Etudiant e24=new EtudiantNP("HAISSOUS","Kayyissa");
        Etudiant e25=new EtudiantNP("HAMROUNI","Yassine");
        Etudiant e26=new EtudiantNP("HANI","Ismaïl");
        Etudiant e27=new EtudiantNP("HORVILLE","Ewen");
        Etudiant e28=new EtudiantNP("JUSTINE","Lucas");
        Etudiant e29=new EtudiantNP("JUSTINE","Yannis");
        Etudiant e30=new EtudiantNP("KOFFI","Ryan");

        // System.out.print(".");
        
        Etudiant e31=new EtudiantNP("LEFEUVRE","Ethan");
        Etudiant e32=new EtudiantNP("LETABI","Valmont");
        Etudiant e33=new EtudiantNP("MARDACI","Kamil");
        Etudiant e34=new EtudiantNP("MARS","Faten");
        Etudiant e35=new EtudiantNP("MARTINS","Clément");
        Etudiant e36=new EtudiantNP("MEDDAHI","Adam");
        Etudiant e37=new EtudiantNP("MONIN","Tom");
        Etudiant e38=new EtudiantNP("MOULIN","Kilian");
        Etudiant e39=new EtudiantNP("NEVEJANS","Baptiste");
        Etudiant e40=new EtudiantNP("SAYAD","Youcef");

        // System.out.print(".");
        
        Etudiant e41=new EtudiantNP("SID ATHMANE","Fares");
        Etudiant e42=new EtudiantNP("SOUSSI ","Yuness");




        AbstractGroupeFactory agf = new AbstractGroupeFactoryNP("BUT2 FI", 15, 92);
        AbstractChangementFactory acf = new AbstractChangementFactoryNP(agf);   
        

        agf.addToGroupe(agf.getPromotion(),e1);
        agf.addToGroupe(agf.getPromotion(),e2);
        agf.addToGroupe(agf.getPromotion(),e3);
        agf.addToGroupe(agf.getPromotion(),e4);
        agf.addToGroupe(agf.getPromotion(),e5);
        agf.addToGroupe(agf.getPromotion(),e6);
        agf.addToGroupe(agf.getPromotion(),e7);
        agf.addToGroupe(agf.getPromotion(),e8);
        agf.addToGroupe(agf.getPromotion(),e9);
        agf.addToGroupe(agf.getPromotion(),e10);

        // System.out.print(".");

        agf.addToGroupe(agf.getPromotion(),e11);
        agf.addToGroupe(agf.getPromotion(),e12);
        agf.addToGroupe(agf.getPromotion(),e13);
        agf.addToGroupe(agf.getPromotion(),e14);
        agf.addToGroupe(agf.getPromotion(),e15);
        agf.addToGroupe(agf.getPromotion(),e16);
        agf.addToGroupe(agf.getPromotion(),e17);
        agf.addToGroupe(agf.getPromotion(),e18);
        agf.addToGroupe(agf.getPromotion(),e19);
        agf.addToGroupe(agf.getPromotion(),e20);

        // System.out.print(".");
        
        agf.addToGroupe(agf.getPromotion(),e21);
        agf.addToGroupe(agf.getPromotion(),e22);
        agf.addToGroupe(agf.getPromotion(),e23);
        agf.addToGroupe(agf.getPromotion(),e24);
        agf.addToGroupe(agf.getPromotion(),e25);
        agf.addToGroupe(agf.getPromotion(),e26);
        agf.addToGroupe(agf.getPromotion(),e27);
        agf.addToGroupe(agf.getPromotion(),e28);
        agf.addToGroupe(agf.getPromotion(),e29);
        agf.addToGroupe(agf.getPromotion(),e30);
        agf.addToGroupe(agf.getPromotion(),e31);
        agf.addToGroupe(agf.getPromotion(),e32);
        agf.addToGroupe(agf.getPromotion(),e33);
        agf.addToGroupe(agf.getPromotion(),e34);
        agf.addToGroupe(agf.getPromotion(),e35);
        agf.addToGroupe(agf.getPromotion(),e36);
        agf.addToGroupe(agf.getPromotion(),e37);
        agf.addToGroupe(agf.getPromotion(),e38);
        agf.addToGroupe(agf.getPromotion(),e39);

        // System.out.print(".");
        
        agf.addToGroupe(agf.getPromotion(),e40);
        agf.addToGroupe(agf.getPromotion(),e41);
        agf.addToGroupe(agf.getPromotion(),e42);
        agf.addToGroupe(agf.getPromotion(),e43);

        agf.createPartition(agf.getPromotion(), "TD",3);

        Groupe racineDeLaPartition = agf.getPromotion().getSousGroupes().iterator().next();



        Iterator<Groupe> itgr = racineDeLaPartition.getSousGroupes().iterator();
        Groupe A = itgr.next(); // premier sous-groupe
        Groupe B = itgr.next(); // second sous-groupe
        
        B = itgr.next(); 
        Etudiant e = A.getEtudiants().iterator().next();
        acf.createChangement(A,e,B);
        Iterator<Changement> itch = acf.getAllChangements().iterator();
        Changement c = itch.next();
        System.out.println(c.monPrint());
        acf.applyChangement(c);
        for(Groupe g : racineDeLaPartition.getSousGroupes()){
            System.out.println(g.monPrint());
        }

        itgr = racineDeLaPartition.getSousGroupes().iterator();
        A = itgr.next(); // premier sous-groupe
        B = itgr.next(); // second sous-groupe
        Etudiant etu1 = A.getEtudiants().iterator().next(); // premier étudiant du premier sous-groupe.
        Etudiant etu2 = B.getEtudiants().iterator().next(); // premier étudiant du premier sous-groupe.
        acf.createChangement(A,etu1,B);
        acf.createChangement(B,etu2,A);
        itch = acf.getAllChangements().iterator();
        c = itch.next();

        TestTexteMNP.Look();
            


        try (Scanner sc = new Scanner(System.in)){
            while(true) {
                System.out.println("\n\nQuelle application voulez-vous lancer ?\n\n\tAdministrateur : 0\n\n\t Enseignant : 1\n\n\t Etudiant : 2\n\n");
                int a = sc.nextInt();
                
                switch(a){
                    case 0 : new FrameAdmin(acf);
                            break;

                    case 1 : new FrameEns(acf);
                            break;
                    
                    case 2 : new FrameEtu(acf);
                            break;
                    default : break;
                }

            }
        }
        catch(Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    
    public static void Look(){
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VueEtuparGroupe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(VueEtuparGroupe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(VueEtuparGroupe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(VueEtuparGroupe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
