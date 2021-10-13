import javafx.scene.control.*;
import javafx.scene.layout.*;

public class FormIscrizione {
    
    public TextField campoEmail;
    private GridPane form;
    
    public FormIscrizione(){
        
        campoEmail = new TextField();
        form = new GridPane();
        form.add(campoEmail,4,1);

    }
    
    public Prestazioni ottieniDatiInseriti(Prestazioni pres){ //1
        Prestazioni prestazione;
        String tipoPres;
        if(pres.getPrestazione() == "prestazione"){
            tipoPres = "esame";
        }else{
            tipoPres = pres.getPrestazione();
        }
        String reparto;
        if(pres.getReparto() == "reparto"){
            reparto = "medicina generale";
        }else{
            reparto = pres.getReparto();
        }
        String data;
        if(pres.getData() == "yyyy-mm-dd"){
            data = "2021-01-01";
        }else{
            data = pres.getData();
        }
        String ora;
        if(pres.getOra() == "hh:mm:ss"){
            ora = "12:00:00";
        }else{
            ora = pres.getOra();
        }
        String luogo;
        if(pres.getLuogo() == "luogo"){
            luogo = "livorno";
        }else{
            luogo = pres.getLuogo();
        }
        
        prestazione = new Prestazioni(tipoPres,reparto,data,ora,luogo,0);
        return prestazione;
        
        }

        public String ottieniEmailUtente(){
            String email = campoEmail.getText();
            return email;
        }

        public void svuotaCampi(){
            campoEmail.clear();
        }

        public GridPane getForm(){
            return form;
        }

         public TextField getEmailUtenteTextField() {
            return campoEmail;
        }

        public void caricaUtente(String nomeUtente){
            campoEmail.setText(nomeUtente);
        }
    
}

//1) metodo per recuperare i dati inseriti nell'ultima riga della tabella
