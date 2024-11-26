/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package packControleur;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import packModele.Etudiant;
import packModele.Promotion;

/**
 *
 * @author thoma
 */
public class ControleurModifiedStudent {
    
    public static boolean modifiedStudent(ArrayList<String> listValues, Promotion promo) {
        
        String num = Promotion.getNumEtuModified();
        Etudiant etu = promo.recherche(num);
        
        if (etu != null && Promotion.getNumEtuModified() != null) {
            if (listValues.get(0) == num) {
                JOptionPane.showMessageDialog(null, "Ce numéro d'étudiant existe déja !");
                return false;
            }
            etu.setNum(listValues.get(0));
            etu.setNom(listValues.get(1));
            etu.setPrenom(listValues.get(2));
            etu.setDepartement(listValues.get(4));

            String bacModified;

            switch (listValues.get(3)) {
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
            
            etu.setBac(bacModified);
            
            // Rafraîchir la liste et notifier les observateurs
            promo.setChangedStatement();
            promo.sortList();
            
            JOptionPane.showMessageDialog(null, "L'étudiant a été modifié avec succès !");
            Promotion.setNumEtuModified(null);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Erreur : Étudiant non trouvé.");
            return false;
        }
        
    }
    
}
