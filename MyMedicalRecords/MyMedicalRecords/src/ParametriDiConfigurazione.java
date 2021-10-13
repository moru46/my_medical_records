import com.thoughtworks.xstream.XStream;

public class ParametriDiConfigurazione {

    public final String ipClient;
    public final String ipServer;
    public final int portaServer;
    public final String font;
    public final double dimensioneFont;
    public final String coloreBackground;
    public final int numeroRigheTabella;
    public static int numeroRigheTabellaStatic;
    public final int periodo;
    public static int periodoStatic;
    public final String ipDB;
    public static String ipDBStatic;
    public final String utenteDB;
    public static String utenteDBStatic;
    public final String passwordDB;
    public static String passwordDBStatic;
    public final String portaDB;
    public static String portaDBStatic;

    public ParametriDiConfigurazione(String fileXML) {
        if (fileXML == null) { //1
            ipClient = "127.0.0.1";
            ipServer = "127.0.0.1";
            portaServer = 8080;
            font = "Arial";
            dimensioneFont = 20.0;
            coloreBackground = "WHITE";
            numeroRigheTabella = 5;
            numeroRigheTabellaStatic = numeroRigheTabella;
            periodo = 365;
            periodoStatic = periodo;
            ipDB = "127.0.0.1";
            ipDBStatic = ipDB;
            utenteDB = "root";
            utenteDBStatic = utenteDB;
            passwordDB = "";
            passwordDBStatic = passwordDB;
            portaDB = "3306";
            portaDBStatic = portaDB;
        } else {
            ParametriDiConfigurazione parametri = (ParametriDiConfigurazione)creaXStream().fromXML(fileXML);
            ipClient = parametri.ipClient;
            ipServer = parametri.ipServer;
            portaServer = parametri.portaServer;
            font = parametri.font;
            dimensioneFont = parametri.dimensioneFont;
            coloreBackground = parametri.coloreBackground;
            numeroRigheTabella = parametri.numeroRigheTabella;
            numeroRigheTabellaStatic = numeroRigheTabella;
            periodo = parametri.periodo;
            periodoStatic = periodo;
            ipDB = parametri.ipDB;
            ipDBStatic = ipDB;
            utenteDB = parametri.utenteDB;
            utenteDBStatic = utenteDB;
            passwordDB = parametri.passwordDB;
            passwordDBStatic = passwordDB;
            portaDB = parametri.portaDB;
            portaDBStatic = portaDB;
        }
    }

    public final XStream creaXStream() {
        XStream xs = new XStream();
        xs.useAttributeFor(ParametriDiConfigurazione.class, "numeroRigheTabella");
        xs.useAttributeFor(ParametriDiConfigurazione.class, "ipClient");
        xs.useAttributeFor(ParametriDiConfigurazione.class, "ipServer");
        xs.useAttributeFor(ParametriDiConfigurazione.class, "portaServer");
        return xs;
    }

    public String getIpClient() {
        return ipClient;
    }

    public String getIpServer() {
        return ipServer;
    }

    public int getPortaServer() {
        return portaServer;
    }
    
    public String getFont() {
        return font;
    }

    public double getDimensioneFont() {
        return dimensioneFont;
    }

    public String getColoreBackground() {
        return coloreBackground;
    }

    public static int getNumeroRigheTabella() {
        return numeroRigheTabellaStatic;
    }

    public static int getPeriodo() {
        return periodoStatic;
    }

    public static String getIpDataBase() {
        return ipDBStatic;
    }

    public static String getUtenteDataBase() {
        return utenteDBStatic;
    }

    public static String getPasswordDataBase() {
        return passwordDBStatic;
    }

    public static String getPortaDataBase() {
        return portaDBStatic;
    }
}

/*
1) Nel caso in cui il file di configurazione non sia valido attribuisco dei dai di default
*/