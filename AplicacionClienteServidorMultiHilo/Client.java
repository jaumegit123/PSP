
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static final String host = "localhost";
    private static final int portNumber = 9090;

    private String userName;
    private String serverHost;
    private int serverPort;

    public static void main(String[] args){
        String readName = null;
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduce un nombre de usuario:");
        while(readName == null || readName.trim().equals("")){
            // Nulo, vacío o espacios en blanco no permitidos.
            readName = scan.nextLine();
            if(readName.trim().equals("")){
                System.out.println("Inválido. Introduce otro:");
            }
        }

        Client client = new Client(readName, host, portNumber);
        client.startClient(scan);
    }

    private Client(String userName, String host, int portNumber){
        this.userName = userName;
        this.serverHost = host;
        this.serverPort = portNumber;
    }

    private void startClient(Scanner scan){
        try{
            Socket socket = new Socket(serverHost, serverPort);
            Thread.sleep(1000); // Esperando a la comunicación de red

            ServerThread serverThread = new ServerThread(socket, userName);
            Thread serverAccessThread = new Thread(serverThread);
            serverAccessThread.start();
            while(serverAccessThread.isAlive()){
                if(scan.hasNextLine()){
                    serverThread.addNextMessage(scan.nextLine());
                }
            }
        }catch(IOException ex){
            System.err.println("Error fatal de conexión!\n");
            ex.printStackTrace();
        }catch(InterruptedException ex){
            System.out.println("Conexión interrumpida\n");
            ex.printStackTrace();
        }
    }
}