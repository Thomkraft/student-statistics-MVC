/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package packControleur;

import java.util.ArrayList;
import packModele.Etudiant;
import packModele.Promotion;

/**
 *
 * @author thoma
 */
public class ControleurFormDelete {
    
    public static boolean supprimerEtudiant(String numero , Promotion promo) {
        ArrayList<Etudiant> etu = new ArrayList<>();
        
        Etudiant student = promo.recherche(numero);
        
        if (student != null) {

            etu.add(student);
            
            promo.supprimerEtudiant(etu);
            
            return true;
        } else { 
            return false; 

        }
    }
    
}
