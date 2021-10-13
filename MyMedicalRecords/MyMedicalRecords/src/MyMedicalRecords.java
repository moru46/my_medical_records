import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.chart.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;


public class MyMedicalRecords extends Application {
    
    private FormIscrizione formIscrizione;
    private InterfacciaMedical interfaccia;
    private TabellaPrestazioni tabella;
    private String utente;
    private ArchivioPrestazioni archivio;
    private GraficoPrestazioni grafico;
    private SelezionePeriodo periodo;
    private Cache cache;
    private ParametriDiConfigurazione config;

    public void start(Stage stage) {
       
        CategoryAxis xAxis2 = new CategoryAxis();
        xAxis2.setLabel("Reparti");
        NumberAxis yAxis2 = new NumberAxis();
        yAxis2.setLabel("Numero Prestazioni");
        xAxis2.setCategories(FXCollections.<String> observableArrayList(Arrays.asList(
                "ortopedia","cardiologia","dermatologia","urologia","oculistica","neurologia")));
        grafico = new GraficoPrestazioni(xAxis2, yAxis2);
        grafico.setPrefHeight(350);
        
        config = new ParametriDiConfigurazione((GestoreEventoLog.valida("config.xml", "config.xsd"))?carica("config.xml"):null);
        GestoreEventoLog.creaLog("Apertura applicazione", config);
        
        interfaccia = new InterfacciaMedical(config);      
        formIscrizione = new FormIscrizione();
        tabella = new TabellaPrestazioni();
        tabella.setEditable(true);
        periodo = new SelezionePeriodo();
        periodo.setPeriodoDefault();
       
        stage.setOnCloseRequest((WindowEvent ev) -> {
            cache.salvaDatiBin(formIscrizione, periodo,tabella.getSelectionModel().getSelectedIndex());
            GestoreEventoLog.creaLog("Chiusura applicazione", config);
        });
        
        cache.prelevaDatiBin(formIscrizione, periodo, tabella);
        utente = formIscrizione.ottieniEmailUtente();

        aggiornaElementi();
        
        eventi(); 
              
        GridPane formTot = formIscrizione.getForm();
        formTot.add(interfaccia.getTitoloApp(),0,0);
        
        HBox infoUtente = new HBox(interfaccia.getEmailUtente(), formIscrizione.getEmailUtenteTextField(),interfaccia.getBottoneCarica());
        HBox bottoniPrestazione = new HBox(interfaccia.getBottoneAggiungi(),interfaccia.getBottoneRimuovi());
        
        VBox vbTabella = new VBox(tabella);
        VBox vbGrafico = new VBox(grafico);    
        VBox scena = new VBox(interfaccia.getTitoloApp(),infoUtente, tabella, bottoniPrestazione, interfaccia.getTitoloGrafico(), vbGrafico, periodo.barraPeriodo );
        
        interfaccia.settaGraficaGenerale(infoUtente, vbTabella, scena, bottoniPrestazione, vbGrafico, periodo.barraPeriodo, formTot);
        
        Group root = new Group(scena);
        Scene scene = new Scene(root);
        scene.setFill(Paint.valueOf(config.getColoreBackground()));
        stage.setResizable(false);
        stage.setTitle("My Medical Records");
        stage.setScene(scene);
        stage.show();
    }
    
    public String carica(String file) { // 1)
        try {
            return new String(Files.readAllBytes((Paths.get(file))));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    public void eventi(){ //2 
        interfaccia.getBottoneCarica().setOnAction((ActionEvent av) -> {
            if(!utente.equals(formIscrizione.ottieniEmailUtente()))
               periodo.setPeriodoDefault();
            utente = formIscrizione.ottieniEmailUtente();
            aggiornaElementi();
             GestoreEventoLog.creaLog("Bottone Carica premuto", config); 
        });
        
        tabella.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {   
             GestoreEventoLog.creaLog("Selezione riga numero " + 
                     tabella.getSelectionModel().getSelectedIndex() + " della tabella", config);
            }
        });
        
        interfaccia.getBottoneAggiungi().setOnAction((ActionEvent av) -> {
            Prestazioni prestazione = tabella.getSelectionModel().getSelectedItem().ottieniDatiInseriti(); 
            archivio.aggiungiPrestazione(prestazione, utente);
            aggiornaElementi();
            GestoreEventoLog.creaLog("Bottone Aggiungi premuto", config);
        });
        
        interfaccia.getBottoneRimuovi().setOnAction((ActionEvent av) -> {
            int indiceRiga = tabella.getSelectionModel().getSelectedItem().getIndice();
            archivio.rimuoviRiga(indiceRiga);
            aggiornaElementi();
            GestoreEventoLog.creaLog("Bottone Rimuovi premuto", config);
        });     
    }
    
    public void aggiornaElementi(){//3
        tabella.aggiornaTabella(archivio.ottieniDatiTabella(utente, periodo.ottieniDataInizio(), periodo.ottieniDataFine()));
        grafico.aggiornaGrafico(archivio.ottieniDatiGraficoEsami(utente, periodo.ottieniDataInizio(), periodo.ottieniDataFine()),archivio.ottieniDatiGraficoVisite(utente, periodo.ottieniDataInizio(), periodo.ottieniDataFine()));
        tabella.getItems().add(new Prestazioni("prestazione","reparto","yyyy-mm-dd","hh:mm:ss","luogo",0));    

    }
}

/*
1) funzione che si occupa di caricare il file di configurazione
2) funzione per settare gli eventi collegati ai bottoni
3) funzione per aggiornare i vari elementi come tabella e grafico, risparmiando codice ridondante
*/
