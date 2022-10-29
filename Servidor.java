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
	
		InputStreamReader entrada = null;
		PrintStream salida = null;
		
		Socket socketAlCliente = null;
		
		InetSocketAddress direccion = new InetSocketAddress(PUERTO);
		
		try (ServerSocket serverSocket = new ServerSocket()){			
			serverSocket.bind(direccion);
			int peticion = 0;
			
			while(true){		
				
				System.out.println("Esperando una petición del puerto: " + PUERTO);
				socketAlCliente = serverSocket.accept();
				System.out.println("Se ha recibido la petición peticion " + ++peticion );
				
				entrada = new InputStreamReader(socketAlCliente.getInputStream());
				BufferedReader bf = new BufferedReader(entrada);	
				String opcionCliente = bf.readLine();	
				System.out.println("El cliente ha elegido la opción: " + opcionCliente);
		
				salida = new PrintStream(socketAlCliente.getOutputStream());
				salida.println("MENSAJE DE PRUEBA");	
				

				socketAlCliente.close();
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