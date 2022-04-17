package comp1721.cwk1;
import java.awt.Font;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.GridLayout;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.swing.JFrame;

// class that create distribution image
public class Distribution {
    ChartPanel frame1;
    public Distribution(Map map){
        // get the date and transmit it to the obj of CategoryDataset
        CategoryDataset dataset = getDataSet(map);
        JFreeChart chart = ChartFactory.createBarChart3D(
                "History of Guess", //title
                "Word", // X-RATE
                "Guess Times", // Y-RATE
                dataset, // dataset
                PlotOrientation.VERTICAL, // direction of the img
                true, // legend
                false,  // tools
                false  // url
        );
        CategoryPlot plot=chart.getCategoryPlot();
        BarRenderer3D renderer = new BarRenderer3D();
        // set the max and min width of the bar
        renderer.setMaximumBarWidth(0.1);
        renderer.setMinimumBarLength(0.1);
        plot.setRenderer(renderer);
        // list in the horizontal level
        CategoryAxis domainAxis=plot.getDomainAxis();
        domainAxis.setLabelFont(new Font("Serif",Font.BOLD,14));         //X-RATE
        domainAxis.setTickLabelFont(new Font("Sans-serif",Font.BOLD,12));  //Y-RATE
        ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状
        rangeAxis.setLabelFont(new Font("Serif",Font.BOLD,15));
        chart.getLegend().setItemFont(new Font("Serif", Font.BOLD, 15));
        chart.getTitle().setFont(new Font("Sans-serif",Font.BOLD,20));//set the font of title
        // create panel to show the img
        frame1=new ChartPanel(chart,true);

    }
    // add the value to the data set
    private static CategoryDataset getDataSet(Map map) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //add the value to the data set
        Set key1 = map.keySet();
        Iterator iterator = key1.iterator();
        while(iterator.hasNext()){
            String key = (String) iterator.next();
            int value = (int) map.get(key);
            dataset.addValue(value, key, key);
        }
        return dataset;
    }
    // get the chart panel
    public ChartPanel getChartPanel(){
        return frame1;
    }
    //consider run as main
    public void run(Map map){
        JFrame frame=new JFrame("Wordle Distribution");
        frame.setLayout(new GridLayout(2,2,10,10));
        // create distribution map
        frame.add(new Distribution(map).getChartPanel());
        frame.setBounds(0, 0, 900, 800);
        frame.setVisible(true);
    }
}