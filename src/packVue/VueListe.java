package packVue;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import packControleur.ControleurDeleteList;
import packModele.Etudiant;
import packModele.Promotion;


public class VueListe extends AbstractVue implements Observer{

    private final JList liste;
    private final JButton btSuppr = new JButton("Supprimer");
    

    private ArrayList<Etudiant> listeEtudiants = Promotion.getListeEtudiant();
    
    private Promotion promo;
    
    private ControleurDeleteList controleurSuppressionListe;

    
    public VueListe(Promotion promo) {
       
        this.promo = promo;
        
        this.promo.addObserver(this);
        
        this.controleurSuppressionListe = new ControleurDeleteList(promo);
        
        liste = new JList();
        liste.setLayoutOrientation(JList.VERTICAL);
        
        
   //     liste.setVisibleRowCount(getHeight());
        JScrollPane scrollPane = new JScrollPane(liste);
        
        liste.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = 0;
        this.add(scrollPane, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        this.add(btSuppr, gc);
        //liste.setPreferredSize(new Dimension (150,500));
       
        remplissageListe();
        this.pack();
        liste.setVisibleRowCount(this.getHeight()/8);
        this.pack();
        
        btSuppr.addActionListener(new EcouteurSuppr());
    }

    private void remplissageListe() {
        liste.removeAll();

        this.pack();

        ArrayList<String> dataStudent = new ArrayList<>();
       
        for (Etudiant etudiant: listeEtudiants) {
            dataStudent.add(etudiant.getNum() +
            " - " +
            etudiant.getNom() +
            " " + etudiant.getPrenom() +
            " (" + etudiant.getDepartement()+ ")");
        }

        liste.setListData(dataStudent.toArray());
    }

    
    @Override
    public void update(Observable o, Object arg) {
        remplissageListe();
    }
    
    private class EcouteurSuppr implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<String> listValue = liste.getSelectedValuesList();

            ArrayList<String> numEtu = new ArrayList<>();
            
            for (String value : listValue) {
                
                numEtu.add(extractNum(value));
                        
            }
            

            controleurSuppressionListe.supprimerEtudiant(numEtu);
            
            
        }
    }
    
    private String extractNum(String value) {
        String[] parts = value.split(" - ");
        return parts[0];
    }
    
}
