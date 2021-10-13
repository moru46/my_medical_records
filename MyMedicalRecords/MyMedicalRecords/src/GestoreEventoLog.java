import java.net.*;
import java.io.*;
import javax.xml.*;
import javax.xml.parsers.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.validation.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class GestoreEventoLog {
    
    private static void invio(EventoNavigazioneGUI log, String ipServer, int portaServer){
         try (Socket socket = new Socket(ipServer, portaServer);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());) {
            if (valida(log.trasformaInStringa(), "log.xsd")) {
                out.writeObject(log.trasformaInStringa());
            } else {
                System.out.println("errore nel file xml");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    
     public static boolean valida(String xml, String file) { //2)
        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Document d;
            if(file.compareTo("config.xsd") == 0){
                d = db.parse(new File(xml));
            }
            else{
                d = db.parse(new InputSource(new StringReader(xml)));
            }
            Schema s = sf.newSchema(new StreamSource(new File(file)));
            s.newValidator().validate(new DOMSource(d));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            if (e instanceof SAXException) {
                System.err.println("Errore di validazione");
            }
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }
     
     public static void creaLog(String tipo, ParametriDiConfigurazione config) {
        invio(new EventoNavigazioneGUI(tipo, config.getIpClient()), config.getIpServer(), config.getPortaServer());
    }
     
}

/*
1) Funzione che si occupa di inviare la linea di log al server, solo se valida
2) Funzione che si occupa di validare la stringa xml o il file di configurazione
*/
    
    
    
    

