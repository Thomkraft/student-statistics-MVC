/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package packModele;

/**
 *
 * @author thoma
 */
public class Etudiant {
    private String num; 
    private String nom; 
    private String prenom; 
    private String bac; 
    private String departement;

    public Etudiant(String num, String nom, String prenom, String bac, String departement) {
        this.num = num;
        this.nom = nom;
        this.prenom = prenom;
        this.bac = bac;
        this.departement = departement;
    }

    public String getNum() {
        return num;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getBac() {
        return bac;
    }

    public String getDepartement() {
        return departement;
    }

    @Override
    public String toString() {
        return num + " - " + nom + prenom + "(" + departement + ")";
    }
    
    
}
