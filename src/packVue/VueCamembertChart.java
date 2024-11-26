package packVue;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import packModele.Etudiant;
import packModele.Promotion;

public class VueCamembertChart extends AbstractVue {

    private Camembert camemb;
    private Promotion promo;

    public VueCamembertChart(Promotion promo) {
        this.promo = promo;
        
        camemb = new Camembert(promo);
        this.setContentPane(camemb);
        this.pack();
    }
    
// internal class
    public class Camembert extends ChartPanel implements Observer{
        
        private Promotion promo;
        
        public Camembert(Promotion promo) {
            super(null);
            
            this.promo = promo;
            
            this.promo.addObserver(this);
            
            this.setPreferredSize(new Dimension(450, 350));
            final PieDataset dataset = createSampleDataset();
            final JFreeChart chart = createChart(dataset);
            final ChartPanel chartPanel = new ChartPanel(chart);
            setContentPane(chartPanel);
            this.setChart(chart);
        }

        private PieDataset createSampleDataset() {
            final DefaultPieDataset result = new DefaultPieDataset();
            
            ArrayList<String> departements = new ArrayList<>();
            ArrayList<Integer> counts = new ArrayList<>();

            // Parcourir les étudiants pour compter les départements
            for (Etudiant etudiant : promo.getListeEtudiant()) {
                String departement = etudiant.getDepartement();
                
                int index = departements.indexOf(departement);
                if (index == -1) {
                    // Nouveau département
                    departements.add(departement);
                    counts.add(1);
                } else {
                    // Département existant, incrémenter le compteur
                    counts.set(index, counts.get(index) + 1);
                }
            }

            // Ajouter les données au dataset
            for (int i = 0; i < departements.size(); i++) {
                result.setValue(departements.get(i), counts.get(i));
            }
            
            return result;
        }

        private JFreeChart createChart(final PieDataset dataset) {
            final JFreeChart chart = ChartFactory.createPieChart3D(
                    "Répartition Géographique", // chart title
                    dataset, // data
                    true, // include legend
                    true,
                    false
            );
            final PiePlot3D plot = (PiePlot3D) chart.getPlot();
            plot.setStartAngle(290);
            plot.setDirection(Rotation.CLOCKWISE);
            plot.setForegroundAlpha(0.5f);
            plot.setNoDataMessage("No data to display");
            return chart;
        }

        @Override
        public void update(Observable o, Object arg) {
            camemb = new Camembert(promo);
        }
    }
}
