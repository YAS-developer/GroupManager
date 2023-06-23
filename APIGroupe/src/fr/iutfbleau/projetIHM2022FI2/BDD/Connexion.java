package fr.iutfbleau.projetIHM2022FI2.BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Connexion {

    private static Connexion cnx ;

    private Connection connect;
    private ResultSet rs;
    private PreparedStatement ps; 

    private Connexion(){
            
        String url = "jdbc:mariadb://dwarves.iut-fbleau.fr/hamrouni";
        String user = "hamrouni";
        String passwd = "Yutyut54321.";
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            this.connect = DriverManager.getConnection(url, user, passwd);
            System.out.println("successful connection!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static Connexion getInstance(){
        if(Connexion.cnx == null){
            Connexion.cnx = new Connexion();
        }
        
        return Connexion.cnx;
    }

    public Connection getConnection(){
        return this.connect;
    }

    public PreparedStatement getPrepare(){
        return this.ps;
    }
    
    public ResultSet getResultSet(){
        return this.rs;
    }
}
