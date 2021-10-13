import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class InterfacciaMedical {

    private Label titoloApp;   
    private Button bottoneCarica;
    private Label emailEtichetta;
    private Button bottoneAggiungi;
    private Button bottoneRimuovi;  
    private Label titoloGrafico;
    private HBox boxTitolo;
 
    
public InterfacciaMedical(ParametriDiConfigurazione config){ 

    bottoneCarica  = new Button("Carica");
    bottoneRimuovi = new Button("Rimuovi");
    bottoneAggiungi = new Button("Aggiungi");
    
    emailEtichetta = new Label();
    emailEtichetta.setText("Email Utente");    
    titoloApp = new Label();
    titoloApp.setText("My Medical Records");
    
    titoloGrafico = new Label();
    titoloGrafico.setText("Report Prestazioni");
    
    settaPosizioni();
    settaFont(config);
    
}


public void settaFont(ParametriDiConfigurazione config){
    titoloApp.setStyle("-fx-alignment:center; -fx-font-size:"  + (config.getDimensioneFont()) 
	+ "pt;  -fx-font-family:" + config.getFont() + "; -fx-text-fill: green; -fx-font-weight: bold;");
     titoloGrafico.setStyle("-fx-alignment:center; -fx-font-size:"  + (config.getDimensioneFont() + 10) 
	 +  "pt;  -fx-font-family:" + config.getFont() + "; -fx-font-size: 20px; -fx-text-fill: green; -fx-font-weight: bold;-fx-padding: 0 0 0 50; ");
}

public void settaPosizioni() {
        boxTitolo = new HBox(titoloApp);
        boxTitolo.setAlignment(Pos.CENTER);
        boxTitolo.setMinWidth(600);
    }

public void settaGraficaGenerale(HBox infoUtente, VBox vbTabella, VBox scena, HBox bottoniPrestazione, VBox vbGrafico, HBox periodo, GridPane form){
    
    infoUtente.setAlignment(Pos.CENTER);
    infoUtente.setStyle("-fx-padding: 10 10 10 10; -fx-spacing:10; ");
    form.setStyle(" -fx-padding: 100 0 100 0; -fx-alignment:center; -fx-font: 20px Arial;");
    bottoniPrestazione.setAlignment(Pos.CENTER);
    bottoniPrestazione.setStyle("-fx-padding: 10 40 10 0; -fx-spacing:80;");

    vbTabella.setAlignment(Pos.CENTER);
    vbTabella.setStyle("-fx-padding: 0 20 0 0; ");
    vbGrafico.setAlignment(Pos.CENTER);
    vbGrafico.setStyle("-fx-padding: 0 20 0 0; -fx-height: 50 ");
    periodo.setAlignment(Pos.CENTER);
    periodo.setStyle("-fx-padding: -730 0 0 250;");
   
    scena.setStyle("-fx-padding: 0 25 0 50;");
}

    public HBox getTitoloApp(){
        return boxTitolo;
    }

    public Label getEmailUtente(){
        return emailEtichetta;
    }

    public Button getBottoneAggiungi() {
            return bottoneAggiungi;
        }

    public Button getBottoneCarica() {
        return bottoneCarica;
    }

    public Button getBottoneRimuovi() {
        return bottoneRimuovi;
    }
    
    public Label getTitoloGrafico() {
        return titoloGrafico;
    }
    
}
