import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	

	public static final int PUERTO = 4000;
	public static final String IP_SERVER = "localhost";
	
	public static void main(String[] args) {
        System.out.println("-----------------------------------");
		System.out.println("        Su aplicación está cargando         ");
		System.out.println("-----------------------------------");

    InetSocketAddress direccionServidor = new InetSocketAddress(IP_SERVER, PUERTO);

    try (Scanner sc = new Scanner(System.in);){

    System.out.println("Estableciendo conexión con el servidor");
    Socket socketAlServidor = new Socket();
    socketAlServidor.connect(direccionServidor);

    int opcion = 0;
    int isbn = 0;
    String titulo = null;

    InputStreamReader entrada = new InputStreamReader(socketAlServidor.getInputStream());
    BufferedReader bf = new BufferedReader(entrada);
    PrintStream salida = new PrintStream(socketAlServidor.getOutputStream());

    do {
    System.out.println("-----Consulta de libros-----");
    System.out.println("1- Consultar libro por ISBN");
    System.out.println("2- Consultar libro por titulo");
    System.out.println("3- Salir de la aplicación");
    System.out.println("-----------------------------");

    opcion = sc.nextInt();
    salida.println(opcion);
    System.out.println("Conexion establecida... a " + IP_SERVER + " por el puerto " + PUERTO);
    

    if (opcion == 1){
        String mensajeServidor = bf.readLine();
	    System.out.println(mensajeServidor);
        isbn = sc.nextInt();
    }else if (opcion == 2) {
        String mensajeServidor = bf.readLine();
	    System.out.println(mensajeServidor);
        titulo = sc.nextLine();
    }else if (opcion == 3){
        String mensajeServidor = bf.readLine();
	    System.out.println(mensajeServidor);
    }else {
        String mensajeServidor = bf.readLine();
        System.out.println(mensajeServidor);
    }
    
    }while (opcion != 3); 
    socketAlServidor.close();
		} catch (UnknownHostException e) {
			System.err.println("No se puede conectar a:" + IP_SERVER);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Error -> " + e);
			e.printStackTrace();
		}
        
		System.out.println("El programa ha terminado");
	}

}