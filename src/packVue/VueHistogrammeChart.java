package packVue;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import packModele.Etudiant;
import packModele.Promotion;

public class VueHistogrammeChart extends AbstractVue {

    private Histogramme histo;
    
    private Promotion promo;

    public VueHistogrammeChart(Promotion promo) {
        this.promo = promo;
        
        histo = new Histogramme(promo);
        this.setContentPane(histo);
        this.pack();
    }


    // internal class
    public class Histogramme extends ChartPanel implements Observer{
        
        private Promotion promo;

        public Histogramme(Promotion promo) {
            super(null);
            
            this.promo = promo;
            
            this.promo.addObserver(this);
            
            this.setPreferredSize(new Dimension(285, 350));
            CategoryDataset dataset = createDataset();
            final JFreeChart chart = createChart(dataset);
            final ChartPanel chartPanel = new ChartPanel(chart);
            this.setChart(chart);
        }

        private CategoryDataset createDataset() {
            
            ArrayList<String> bacs = new ArrayList<>();
            ArrayList<Integer> count = new ArrayList<>();
            
            for (Etudiant etudiant : Promotion.getListeEtudiant()) {
                String bac = etudiant.getBac();
                int index = bacs.indexOf(bac);
                
                if (index == -1) {
                    bacs.add(bac);
                    count.add(1);
                } else {
                    
                    count.set(index, count.get(index) + 1);
                }  
            }

            final double[][] data = new double [bacs.size()][1];
            final String[] label = new String [bacs.size()];
            final String[] labelGeneral = {""};
            
            for (int i = 0 ; i < bacs.size(); i++) {
                data[i][0] = count.get(i);
                label[i] = bacs.get(i);
            }

            //final double[][] data = new double[][]{{10.0, 4.0, 15.0, 14.0}, {}};
            return DatasetUtilities.createCategoryDataset(label, labelGeneral, data);
        }

        private JFreeChart createChart(final CategoryDataset dataset) {
            final JFreeChart chart = ChartFactory.createBarChart3D(
                    "Séries de bac", // chart title
                    "Bacs", // domain axis label
                    "Nombre", // range axis label
                    dataset, // data
                    PlotOrientation.VERTICAL, // orientation
                    true, // include legend
                    true, // tooltips
                    false // urls
            );

            final CategoryPlot plot = chart.getCategoryPlot();
            final CategoryAxis axis = plot.getDomainAxis();
            axis.setCategoryLabelPositions(
                    CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 8.0)
            );
            final BarRenderer3D renderer = (BarRenderer3D) plot.getRenderer();
            renderer.setDrawBarOutline(false);

            return chart;

        }

        @Override
        public void update(Observable o, Object arg) {
            final CategoryDataset updatedDataset = createDataset();

            JFreeChart updatedChart = createChart(updatedDataset);
            this.setChart(updatedChart);
        }

    }

}
