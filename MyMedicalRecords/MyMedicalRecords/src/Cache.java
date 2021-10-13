import java.io.*;
import java.time.LocalDate;
import javafx.application.Platform;


public class Cache {
    
    
    public static void salvaDatiBin(FormIscrizione form,SelezionePeriodo periodo, Integer indexTabella){ //1

        try(FileOutputStream fout = new FileOutputStream("./cache/salvataggio.bin");
                ObjectOutputStream oout = new ObjectOutputStream(fout);) {
            
            String email = form.ottieniEmailUtente();
            LocalDate dataInizio = periodo.importaDataInizio();
            LocalDate dataFine = periodo.importaDataFine();
            
            oout.writeObject(email);
            oout.writeObject(dataInizio);
            oout.writeObject(dataFine);    
            oout.writeObject(indexTabella); //anche se -1
                
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
     public static void prelevaDatiBin(FormIscrizione form,SelezionePeriodo periodo, TabellaPrestazioni tab) { //2 
        try (FileInputStream fin = new FileInputStream("./cache/salvataggio.bin");
                ObjectInputStream oin = new ObjectInputStream(fin);) {
            
            String utente = (String) oin.readObject();
            LocalDate dataInizio = (LocalDate) oin.readObject();
            LocalDate dataFine = (LocalDate) oin.readObject();
            int indexTabella = (int) oin.readObject();
            
            
            form.caricaUtente(utente);
            periodo.caricaDataInizio(dataInizio);
            periodo.caricaDataFine(dataFine);
            
            if(indexTabella != -1){ //1)
                Platform.runLater(() ->
                    {
                        tab.requestFocus();
                        tab.getSelectionModel().select(indexTabella);
                        
                    });
            }
            
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }
	
	/*
	1) Salva i dati prelevati dai campi del form su file binario
	2) Preleva i dati dal file binario e li immette nel form
	*/
     
     
}
