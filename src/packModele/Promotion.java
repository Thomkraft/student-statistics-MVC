/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package packModele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Observable;
import java.util.Scanner;

/**
 *
 * @author thoma
 */
public class Promotion extends Observable{
    private static String numEtuModified;
    private static ArrayList<Etudiant> listeEtudiant= new ArrayList<>();

    public Promotion() {

    }
    

    public void loadFileCSV() {
        
        try{
            Scanner scan = new Scanner(new File("data/promoBUT.csv"));
            
           
            while (scan.hasNext()) {
                
                String line = scan.next();
                
                String[] parts = line.split(";");
                
                if (parts.length == 5) {
                    
                    if (Integer.parseInt(parts[3]) < 10) {
                        parts[3] = "0" + parts[3];
                    }
                    
                    Etudiant etudiant = new Etudiant(
                            parts[0],
                            parts[1],
                            parts[2], 
                            parts[4],
                            parts[3]);
                    
                    listeEtudiant.add(etudiant);
                }
            }
            
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
       
    }

    public void supprimerEtudiant(ArrayList<Etudiant> etudiant) {
        for (Etudiant etu : etudiant) {
            listeEtudiant.remove(etu);
        }
        setChanged();
        notifyObservers();
    }
    
    public void ajouterEtudiant(ArrayList<String> listValue,String bacModified) {
        listeEtudiant.add(new Etudiant(listValue.get(0),
                                                      listValue.get(1),
                                                      listValue.get(2),
                                                      bacModified,
                                                      listValue.get(4))
        );
        
        Collections.sort(listeEtudiant, new Comparator<Etudiant>() {
            @Override
            public int compare(Etudiant e1, Etudiant e2) {
                return e1.getNum().compareTo(e2.getNum());
            }
        });
        
        setChanged();
        notifyObservers();
        
    }
    
    public static ArrayList<Etudiant> getListeEtudiant() {
        return listeEtudiant;
    }
    
    
    public Etudiant recherche(String numero) {

        for (Etudiant etudiant : listeEtudiant) {

            if (etudiant.getNum().equals(numero)) {

                return etudiant;
            }
        }
    return null;
    }
    
    public void setChangedStatement(){
        setChanged();
        notifyObservers();
    }
    
    @Override
    public String toString() {
        return "Promotion{" + "listeEtudiant=" + listeEtudiant + '}';
    }

    public static void setNumEtuModified(String numEtuModified) {
        Promotion.numEtuModified = numEtuModified;
    }

    public static String getNumEtuModified() {
        return numEtuModified;
    }
    
    public void sortList(){
        Collections.sort(listeEtudiant, new Comparator<Etudiant>() {
            @Override
            public int compare(Etudiant e1, Etudiant e2) {
                return e1.getNum().compareTo(e2.getNum());
            }
        });
        setChanged();
        notifyObservers();

    }

}
