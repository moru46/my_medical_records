import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javafx.scene.chart.XYChart;

public class ArchivioPrestazioni {

 private static Connection connessioneDB;
 private static PreparedStatement selezionaDatiTabella;
 private static PreparedStatement inserisciNuovaPrestazione;
 private static PreparedStatement rimuoviRiga;
 private static PreparedStatement selezionaDatiGraficoEsami;
 private static PreparedStatement selezionaDatiGraficoVisite;

 static{
     try{ //istanzio la connessione con il db
         
        connessioneDB = DriverManager.getConnection("jdbc:mysql://" + ParametriDiConfigurazione.getIpDataBase() + ":" + ParametriDiConfigurazione.getPortaDataBase() + "/MyMedicalRecords", ParametriDiConfigurazione.getUtenteDataBase(), ParametriDiConfigurazione.getPasswordDataBase());
        selezionaDatiTabella = connessioneDB.prepareStatement("SELECT * from prestazioni WHERE nomeUtente = ? and data between ? and date_add(?, interval 7 day) order by data desc limit ?;");
        inserisciNuovaPrestazione = connessioneDB.prepareStatement("INSERT into prestazioni (idPrestazione,nomeUtente,prestazione,reparto,data,ora,luogo) values(0,?,?,?,?,?,?)");
        rimuoviRiga = connessioneDB.prepareStatement("DELETE p.* FROM prestazioni p where p.idPrestazione = ?");
        selezionaDatiGraficoEsami = connessioneDB.prepareStatement("SELECT reparto, count(*)  FROM prestazioni WHERE nomeUtente = ? and prestazione = 'esame' and data between ? and ? GROUP BY reparto");
        selezionaDatiGraficoVisite = connessioneDB.prepareStatement("SELECT reparto, count(*)  FROM prestazioni WHERE nomeUtente = ? and prestazione = 'visita' and data between ? and ? GROUP BY reparto");

     }catch(SQLException e){
        System.err.println(e.getMessage());
     }
}
 
 public static List<Prestazioni> ottieniDatiTabella(String utente, String inizio, String fine){
    
     List<Prestazioni> listaPrestazioni = new ArrayList<>();
     try{
         selezionaDatiTabella.setString(1,utente); //seleziono l'utente
          selezionaDatiTabella.setString(2,inizio);
           selezionaDatiTabella.setString(3,fine);
         selezionaDatiTabella.setInt(4, ParametriDiConfigurazione.getNumeroRigheTabella());
         ResultSet risu = selezionaDatiTabella.executeQuery();
         while(risu.next()){
             
             //conversione da date a stringa e time a stringa
             Date data;
             data = risu.getDate("data");
             String dataPres = data.toString();
                          
             Time ora;
             ora = risu.getTime("ora");
             String oraPres = ora.toString();
             
             listaPrestazioni.add(new Prestazioni(risu.getString("prestazione"), risu.getString("reparto"), dataPres, oraPres, risu.getString("luogo"), risu.getInt("idPrestazione")));
         }   
     } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
     
     return listaPrestazioni;
 }
 
 public static java.sql.Date convertiData(Prestazioni pres){
     java.sql.Date sqlDate = null;
    try{ SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = df.parse(pres.getData());

            sqlDate = new java.sql.Date(date.getTime());
            
 }catch (ParseException px) {  System.err.println(px.getMessage());}
    return sqlDate;
 }
 
 
public static void aggiungiPrestazione(Prestazioni prestazione, String nome){
         try{          
              
            inserisciNuovaPrestazione.setString(1, nome);
            inserisciNuovaPrestazione.setString(2, prestazione.getPrestazione());
            inserisciNuovaPrestazione.setString(3, prestazione.getReparto());
            inserisciNuovaPrestazione.setTime(5, java.sql.Time.valueOf(prestazione.getOra()));
            inserisciNuovaPrestazione.setString(6, prestazione.getLuogo());
            inserisciNuovaPrestazione.setDate(4,convertiData(prestazione));
            int rs = inserisciNuovaPrestazione.executeUpdate();
        } catch (SQLException e) {
            System.err.println("errore inserimento");
            System.err.println(e.getMessage());
            
        }
}

public static void rimuoviRiga(int id) {
        try {
            rimuoviRiga.setInt(1, id);
            int risultato = rimuoviRiga.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
         
public static List<XYChart.Data> ottieniDatiGraficoVisite(String utente, String dataInizio, String dataFine){

    List<XYChart.Data> lista = new ArrayList<>();
    
    try{
        selezionaDatiGraficoVisite.setString(1,utente);
        selezionaDatiGraficoVisite.setString(2, dataInizio);
        selezionaDatiGraficoVisite.setString(3, dataFine);
        ResultSet risu = selezionaDatiGraficoVisite.executeQuery();
        
        while(risu.next()){
            
            lista.add(new XYChart.Data(risu.getString("reparto"), risu.getDouble("count(*)")));
            
        }
    } catch (SQLException e){
         System.err.println(e.getMessage());
        }
    
    return lista;
    
}

public static List<XYChart.Data> ottieniDatiGraficoEsami(String utente, String dataInizio, String dataFine){

    List<XYChart.Data> lista = new ArrayList<>();
    
    try{
        selezionaDatiGraficoEsami.setString(1,utente);
        selezionaDatiGraficoEsami.setString(2, dataInizio);
        selezionaDatiGraficoEsami.setString(3, dataFine);
        ResultSet risu = selezionaDatiGraficoEsami.executeQuery();
        
        while(risu.next()){
            
            lista.add(new XYChart.Data(risu.getString("reparto"), risu.getDouble("count(*)")));
            
        }
    } catch (SQLException e){
         System.err.println(e.getMessage());
        }
    
    return lista;
    
	}
  
}
