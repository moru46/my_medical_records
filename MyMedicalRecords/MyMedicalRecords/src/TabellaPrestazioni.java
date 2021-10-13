import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.control.TableRow;
import javafx.event.EventHandler;
import javafx.util.Callback;


public class TabellaPrestazioni extends TableView<Prestazioni>{
    
    private ObservableList<Prestazioni> listaOsservabilePrestazioni;
    
    public TabellaPrestazioni(){
        setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        setMaxHeight(180);
        
        TableColumn colonna1 = colonnaTab("prestazione");
        TableColumn colonna2 = colonnaTab("reparto");
        TableColumn colonna3 = colonnaTab("data");
        TableColumn colonna4 = colonnaTab("ora");
        TableColumn colonna5 = colonnaTab("luogo");
          
        listaOsservabilePrestazioni = FXCollections.observableArrayList();
        setItems(listaOsservabilePrestazioni);
       
        colonna1.setCellFactory(TextFieldTableCell.forTableColumn());
        
        getColumns().addAll(colonna1,colonna2,colonna3,colonna4,colonna5);
        setPlaceholder(new Label("Nessun dato disponibile per questo utente"));
        
         setRowFactory((TableView<Prestazioni> tv) -> new TableRow<Prestazioni>() { //1)
            public void updateItem(Prestazioni item, boolean empty) {
                super.updateItem(item, empty);
 
                if (item == null) {
                    setStyle("");}
                else  {    
                    Date dataRecord = null;
                    LocalDate dataAttuale = LocalDate.now();
                    Date dataOra = java.sql.Date.valueOf(dataAttuale);
                    
                if(!item.getData().equals("yyyy-mm-dd")){
                     dataRecord = convertiData(item.getData());
                     if(dataRecord.compareTo(dataOra) <= 0){
                     setStyle("-fx-background-color: #e6b800;");}
                     else {
                         setStyle("-fx-background-color: #73ae7a ;");
                     }
                  }    
                }
            }
        });
        
        setMaxWidth(600);
    }
    
    public static java.util.Date convertiData(String data){
     java.util.Date defaultData = null;
    try{ SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = df.parse(data);

            defaultData = new java.sql.Date(date.getTime());
            
    }catch (ParseException ex) {  System.err.println(ex.getMessage());}
    return defaultData;
 }
    
    public void aggiornaTabella(List<Prestazioni> prestazione){
        listaOsservabilePrestazioni.clear();
        listaOsservabilePrestazioni.addAll(prestazione);
    } 
    
   public TableColumn colonnaTab(String tipo) {//2)
       Callback<TableColumn, TableCell> editableFactory = new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn p) {
                return new EditingCelleTabella();
            }
        };
        TableColumn colonna = new TableColumn(tipo);
        colonna.setMinWidth(100);
        colonna.setCellValueFactory(new PropertyValueFactory<Prestazioni, String>(tipo));
        colonna.setCellFactory(editableFactory);
        colonna.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Prestazioni, String>>() {
            public void handle(TableColumn.CellEditEvent<Prestazioni, String> t) {
               if("prestazione".equals(tipo))
                t.getRowValue().setPrestazione(t.getNewValue());
               if("reparto".equals(tipo))
                t.getRowValue().setReparto(t.getNewValue());
               if("luogo".equals(tipo))
                t.getRowValue().setLuogo(t.getNewValue());
               if("ora".equals(tipo))
                t.getRowValue().setOra(t.getNewValue());
               if("data".equals(tipo))
                t.getRowValue().setData(t.getNewValue());
               
            }
        }); 
        return colonna;  
   } 
}

/*
1)Cambio colore in base alla data della prestazione, in particolare gialla se la data è precedente a quella odierna, verde altrimenti
2)Metodo che richiama la classe EditingCelleTabella per rendere ogni cella della tabella editabile
*/