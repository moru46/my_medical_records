import java.time.LocalDate;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class SelezionePeriodo extends DatePicker { 
    private final Label da;
    private final Label a;
    
    public DatePicker dataInizio; 
    public DatePicker dataFine; 
    public HBox barraPeriodo;
    
    public SelezionePeriodo() {
        da = new Label("Da");
        dataInizio = new DatePicker();
        a = new Label("A");
        dataFine = new DatePicker();
        
        barraPeriodo = new HBox();
        barraPeriodo.getChildren().addAll(da, dataInizio, a, dataFine);
        barraPeriodo.setAlignment(Pos.CENTER);
        barraPeriodo.setSpacing(10);
        dataInizio.setPrefWidth(105);
        dataFine.setPrefWidth(105);
    }
    
    public void setPeriodoDefault(){
        dataInizio.setValue(LocalDate.now().minusDays(ParametriDiConfigurazione.getPeriodo()));
        dataFine.setValue(LocalDate.now());
    }
    
    public void svuotaCampi() { //1 
        
        dataInizio.setValue(null);
        dataFine.setValue(LocalDate.now());
        dataFine.getEditor().clear();
    }
    
    public String ottieniDataInizio(){ //2 
        LocalDate inizioString = LocalDate.now().minusDays(ParametriDiConfigurazione.getPeriodo());
        String inizio = inizioString.toString();
        if(dataInizio.getValue() != null)
          inizio = dataInizio.getValue().toString();
        return inizio;
    }
    
    public String ottieniDataFine(){ //3
        LocalDate fineString = LocalDate.now();
        String fine = fineString.toString();
        if(dataFine.getValue() != null)
          fine = dataFine.getValue().toString();
        return fine;
    }
    
     public LocalDate importaDataInizio(){ //4 
        LocalDate inizio = dataInizio.getValue();
        return inizio;
           
    }
     
      public LocalDate importaDataFine(){ //4    
        LocalDate fine = dataFine.getValue();
        return fine;
           
    }
      
      public void caricaDataInizio(LocalDate inizio){ //5
          if(inizio == null)
            dataInizio.setValue(LocalDate.now().minusDays(ParametriDiConfigurazione.getPeriodo()));
          else 
               dataInizio.setValue(inizio);
          
      }
       
      public void caricaDataFine(LocalDate fine){ //5
          if(fine == null)
            dataFine.setValue(LocalDate.now());
          else
               dataFine.setValue(fine);
        
      }

}

/*1) svuota i campi ogni cambio utente
2) data da passare alla query sql
3) data da passare alla query sql
4) data da passare alla cache
5) data passata dalla cache da caricare nel datePicker
*/

