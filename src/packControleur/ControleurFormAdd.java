/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package packControleur;

import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import packModele.Etudiant;
import packModele.Promotion;

/**
 *
 * @author thoma
 */
public class ControleurFormAdd {
    
    public static boolean ajouterEtudiant(ArrayList<String> listValue, Promotion promo) {
        
        if (listValue.contains("") || listValue.contains("- - -")) {
            JOptionPane.showMessageDialog(null, "Merci de remplir tous les champ de valeurs avant d'ajouter !");
            return false; 
        }
        
        String num = listValue.get(0);
        String lastName = listValue.get(1);
        String firstName = listValue.get(2);
        String bac = listValue.get(3);
        
        Etudiant student = promo.recherche(num);
        if (student != null) {
            JOptionPane.showMessageDialog(null, "Le numéro d'étudiant existe deja !.");
            return false;
        }
            
        if (!Pattern.matches("\\d+", num)) {
            JOptionPane.showMessageDialog(null, "Le numéro doit contenir uniquement des chiffres.");
            return false;
        }
        
        if (!Pattern.matches("[A-Za-z]+", lastName)) {
            JOptionPane.showMessageDialog(null, "Le nom doit contenir uniquement des lettres.");
            return false;
        }
        
        if (!Pattern.matches("[A-Za-z]+", firstName)) {
            JOptionPane.showMessageDialog(null, "Le prénom doit contenir uniquement des lettres.");
            return false;
        }
        
        String bacModified;

        switch (bac) {
            case "General":
                bacModified = "G";
                break;
            case "Techno":
                bacModified = "T";
                break;
            case "Pro":
                bacModified = "Pro";
                break;
            case "Autre":
                bacModified = "A";
                break;
            default:
                throw new AssertionError();
        }
        
        promo.ajouterEtudiant(listValue, bacModified);
        
        System.out.println("Etudiant ajouté !");
        
        return true;
    }
}
