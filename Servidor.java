import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    
    public static final int PUERTO = 4000;
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println("      Servidor      ");
        System.out.println("----------------------------------");
    
        int peticion = 0;

        try (ServerSocket serverSocket = new ServerSocket()){    
            InetSocketAddress direccion = new InetSocketAddress(PUERTO);        
            serverSocket.bind(direccion);
            
			System.out.println("Esperando peticion por el puerto " + PUERTO);
            
            while (true) {
                Socket socketAlCliente = serverSocket.accept();
                System.out.println("Se ha recibido la petición " + ++peticion);
                new HiloLibreria(socketAlCliente);
            }    
        } catch (IOException e) {
            System.err.println("Error de entrada/salida");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error -> " + e);
            e.printStackTrace();
        }
    }
}