import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class HiloLibreria implements Runnable{
	private Thread hilo;
	private static int numeroCliente = 0;
	private Socket socketAlCliente;	
	
	public HiloLibreria(Socket socketAlCliente) {
		numeroCliente++;
		hilo = new Thread(this, "Cliente "+numeroCliente);
		this.socketAlCliente = socketAlCliente;
		hilo.start();
	}
	
	@Override
	public void run() {
		System.out.println("Estableciendo comunicacion con " + hilo.getName());
		PrintStream salida = null;
		InputStreamReader entrada = null;
		BufferedReader bf = null;
        
		
        try {	
			salida = new PrintStream(socketAlCliente.getOutputStream());
			entrada = new InputStreamReader(socketAlCliente.getInputStream());
			bf = new BufferedReader(entrada);
            boolean continuar = true;
			while(continuar){		
				String opcionCliente = bf.readLine();	
				System.out.println("El cliente ha elegido la opción: " + opcionCliente);
				
                switch (opcionCliente) {
                    case "1":
                        salida.println("Servidor: Escriba el ISBN");
                        String isbnCliente = bf.readLine();	
				        System.out.println("El cliente ha mandado el ISBN: " + isbnCliente);
                    break;
                    case "2":
                        salida.println("Servidor: Escriba el título");
                        String tituloCliente = bf.readLine();	
				        System.out.println("El cliente ha mandado el ISBN: " + tituloCliente);
                    break;
                    case "3":
                        salida.println("Servidor: Ha salido de la aplicacion");
                        socketAlCliente.close();
                    break;
                
                    default:
                        salida.println("Servidor: Tiene que elegir las opciones 1,2 o 3");
                        opcionCliente = bf.readLine();	
				        System.out.println("El cliente ha elegido la opción: " + opcionCliente + ", solicitando otra respuesta");
                        break;
                }

			}
            socketAlCliente.close();
		} catch (IOException e) {
			System.err.println("HiloContadorLetras: Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("HiloContadorLetras: Error");
			e.printStackTrace();
		}
	}
}
