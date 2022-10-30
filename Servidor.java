import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    Libro l1 = new Libro("Odisea", "Homero", 15, 78153012);
    Libro l2 = new Libro("Don Quijote de la Mancha", "Miguel de cervantes", 20, 10347860);
    Libro l3 = new Libro("Diario", "Ana Frank", 13, 45896301);
    Libro l4 = new Libro("Codigo Da Vinci", "Dan Brown", 22, 13461973);
    Libro l5 = new Libro("Arte de la Guerra", "Sun Tzu", 8, 38792540);
    
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