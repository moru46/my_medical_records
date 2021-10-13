
import javafx.collections.FXCollections;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import java.util.*;
import javafx.collections.*;


public class GraficoPrestazioni extends BarChart {
    
    private ObservableList<XYChart.Data<String,Number>> datiGrafico1;
    private ObservableList<XYChart.Data<String,Number>> datiGrafico2;
    

    public GraficoPrestazioni(CategoryAxis xAxis, NumberAxis yAxis) {
        
        super(xAxis, yAxis);
        
        CategoryAxis xAxis2 = new CategoryAxis();  
        xAxis2.setLabel("Reparti");
        
        NumberAxis yAxis2 = new NumberAxis();
        yAxis2.setLabel("Numero Prestazioni");
        
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(10);
   
    }
    
    public void aggiornaGrafico(List<XYChart.Data> listaEsami, List<XYChart.Data> listaVisite){ //1
        getData().clear();
        
        ObservableList<XYChart.Series<String,Number>> series1 = FXCollections.observableArrayList();
        ObservableList<XYChart.Series<String,Number>> series2 = FXCollections.observableArrayList();

        datiGrafico1 = FXCollections.observableArrayList();
        datiGrafico1.clear();
        
        for (XYChart.Data lista2 : listaEsami) {
            datiGrafico1.add(lista2);
        }
        series1.add(new XYChart.Series<>("esami", datiGrafico1));
        getData().addAll(series1.get(0));
        
        datiGrafico2 = FXCollections.observableArrayList();
        datiGrafico2.clear();
        
        for (XYChart.Data lista3 : listaVisite) {
            datiGrafico2.add(lista3);
        }
        series2.add(new XYChart.Series<>("visite", datiGrafico2));
        getData().addAll(series2.get(0));
 
    }
}

//1) metodo utilizzato per aggiornare il grafico delle prestazioni
