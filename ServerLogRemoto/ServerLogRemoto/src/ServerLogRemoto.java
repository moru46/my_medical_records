import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.*;

public class ServerLogRemoto {
    
    private static int porta = 8080;
    private static String fileLog = "log.xml";
    
    public static void main(String[] args) throws IOException {
        
          System.out.println("Server avviato. \n");
          
          try(ServerSocket sockServer = new ServerSocket(porta)){
              
              while(true){
                  try(Socket socket = sockServer.accept();
                          ObjectInputStream oin = new ObjectInputStream(socket.getInputStream()); ) {
                             String log = (String) oin.readObject();
                             System.out.println(log);
                             salva(log, fileLog);
                  }
              }
              
          } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public static void salva(Object o, String file) { //1)
        try {
            Files.write((Paths.get("log.xml")), o.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

 //1) Funzione che si occupa di scrivere su file in modalità append.

