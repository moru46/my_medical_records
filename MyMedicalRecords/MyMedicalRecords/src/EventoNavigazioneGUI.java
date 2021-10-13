import java.io.*;
import com.thoughtworks.xstream.XStream;
import java.text.*;
import java.util.*;


public class EventoNavigazioneGUI implements Serializable {
    
    private final String nomeEvento;
    private final String indirizzoIp;
    private final String timeStamp;
    
    public EventoNavigazioneGUI(String evento, String ip) {
        
        nomeEvento = evento;
        indirizzoIp = ip;
        timeStamp = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(new Date());
        
    }
    
     public String trasformaInStringa() { //1)
        XStream xstream = new XStream();
        xstream.useAttributeFor(EventoNavigazioneGUI.class, "nomeEvento");
        xstream.useAttributeFor(EventoNavigazioneGUI.class, "indirizzoIp");
        xstream.useAttributeFor(EventoNavigazioneGUI.class, "timeStamp");
        return xstream.toXML(this) + "\n";
    }
}

// 1) crea un xstream e ritorna una stringa XML 
    
    

