/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package packControleur;

import java.util.ArrayList;
import java.util.List;
import packModele.Etudiant;
import packModele.Promotion;

/**
 *
 * @author thoma
 */
public class ControleurDeleteList {

    private static Promotion promo;

    public ControleurDeleteList(Promotion promo) {
        this.promo = promo;
    }
   
    
    public static boolean supprimerEtudiant(ArrayList<String> numEtu) {
        
        if(numEtu.isEmpty()) {
            return false;
        }
        
        ArrayList<Integer> index = new ArrayList<>();
        ArrayList<Etudiant> etudiantlist = new ArrayList<>();

        for (String num : numEtu) {
            Etudiant etudiant = promo.recherche(num);

            index.add(Promotion.getListeEtudiant().indexOf(etudiant));

            etudiantlist.add(etudiant);
        }
        
        
        for (Etudiant etu : etudiantlist) {
        
            if (!Promotion.getListeEtudiant().contains(etu)) {
                etudiantlist.remove(etu);
            }
        
        }
        promo.supprimerEtudiant(etudiantlist);      
        return true;
    }
    
}
