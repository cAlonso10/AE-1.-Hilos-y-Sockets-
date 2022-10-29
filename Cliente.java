

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
    int opcion = 0;
    int isbn = 0;
    String autor;
        
    do {
    System.out.println("-----Consulta de libros-----");
    System.out.println("1- Consultar libro por ISBN");
    System.out.println("2- Consultar libro por titulo");
    System.out.println("3- Salir de la aplicación");
    System.out.println("-----------------------------");
    opcion = sc.nextInt();
    
    Socket socketAlServidor = new Socket();
    if (opcion == 1){
        System.out.println("Escriba el ISBN:");
        isbn = sc.nextInt();
        System.out.println("Conectandose al serivdor...");
        socketAlServidor.connect(direccionServidor);			
        System.out.println("Conexion establecida... a " + IP_SERVER + " por el puerto " + PUERTO);
    }else if (opcion == 2) {
        System.out.println("Conectandose al serivdor...");
        socketAlServidor.connect(direccionServidor);			
        System.out.println("Conexion establecida... a " + IP_SERVER + " por el puerto " + PUERTO);
    }else if (opcion == 3){
        System.out.println("Cerrando aplicación");
    }else {
        System.out.println("Tiene que elegir una de las opciones");
    }
    
    
			
	PrintStream salida = new PrintStream(socketAlServidor.getOutputStream());
    salida.println(opcion);
    salida.println(isbn);
	InputStreamReader entrada = new InputStreamReader(socketAlServidor.getInputStream());
	BufferedReader bf = new BufferedReader(entrada);
			
	System.out.println("Esperando al resultado del servidor...");
	String mensajeServidor = bf.readLine();
				
	System.out.println("Mensaje del servidor: " + mensajeServidor);//7
	socketAlServidor.close();
    }while (opcion != 3); 
        
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

