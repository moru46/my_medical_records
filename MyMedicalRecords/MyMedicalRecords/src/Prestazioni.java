import javafx.beans.property.*;

public class Prestazioni {
    
    private final SimpleIntegerProperty indice;
    private final SimpleStringProperty prestazione;
    private final SimpleStringProperty reparto;
    private final SimpleStringProperty data;
    private final SimpleStringProperty ora;
    private final SimpleStringProperty luogo; 

    public Prestazioni(String pres, String rep, String dataPres, String oraPres, String luog, int index){
 
      indice = new  SimpleIntegerProperty(index);
      prestazione = new SimpleStringProperty(pres);
      reparto = new SimpleStringProperty(rep);
      luogo = new SimpleStringProperty(luog);
      data = new SimpleStringProperty(dataPres);
      ora = new SimpleStringProperty(oraPres);
   }
    
        public Prestazioni ottieniDatiInseriti(){
        Prestazioni nuovaPrest;
        String tipoPres;
        if( prestazione.getValue() == "prestazione"){
            tipoPres = "esame";
        }else{
            tipoPres = prestazione.getValue();
        }
        String tipoReparto;
        if(reparto.getValue()== "reparto"){
            tipoReparto = "medicina generale";
        }else{
            tipoReparto = reparto.getValue();
        }
        String dataValue;
        if(data.getValue() == "yyyy-mm-dd"){
            dataValue = "2021-01-01";
        }else{
            dataValue = data.getValue();
        }
        String oraValue;
        if(ora.getValue() == "hh:mm:ss"){
            oraValue = "12:00:00";
        }else{
            oraValue = ora.getValue();
        }
        String luogoValue;
        if(luogo.getValue() == "luogo"){
            luogoValue = "livorno";
        }else{
            luogoValue = luogo.getValue();
        }
        
        nuovaPrest = new Prestazioni(tipoPres,tipoReparto,dataValue,oraValue,luogoValue,0);
        return nuovaPrest;
        
    }
    
       public String getPrestazione() {
        return prestazione.get();
    }

    public String getReparto() {
        return reparto.get();
    }
    
    public void setLuogo(String luogo) {
        this.luogo.set(luogo);
    }
     public void setPrestazione(String prestazione) {
        this.prestazione.set(prestazione);
    }
      public void setReparto(String reparto) {
        this.reparto.set(reparto);
    } 
       public String getLuogo() {
        return luogo.get();
    }
       
    public int getIndice() {
        return indice.get();
    }
    
    public void setData(String data) {
        this.data.set(data);
    }
        public void setOra(String ora) {
        this.ora.set(ora);
        }
    
    public String getOra() {
        return ora.get();
    }
    
     public String getData() {
        return data.get();
    }
    
     
}
