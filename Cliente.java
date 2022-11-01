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

    //Da comienzo al programa
    try (Scanner sc = new Scanner(System.in);){
    //Se establece la conexión con el servidor
    System.out.println("Estableciendo conexión con el servidor");
    Socket socketAlServidor = new Socket();
    socketAlServidor.connect(direccionServidor);
    
    int opcion ;
    int isbn = 0;
    String titulo;
    String autor;
    String datosLibro;
    
   //Salida y entrada de mensajes del cliente-servidor
    InputStreamReader entrada = new InputStreamReader(socketAlServidor.getInputStream());
    BufferedReader bf = new BufferedReader(entrada);
    PrintStream salida = new PrintStream(socketAlServidor.getOutputStream());

    //Aparece una lista con las 5 opciones posibles del programa
    do {
    System.out.println("-----Consulta de libros-----");
    System.out.println("1- Consultar libro por ISBN");
    System.out.println("2- Consultar libro por titulo");
    System.out.println("3- Consultar libros por autor");
    System.out.println("4- Añadir Libro");
    System.out.println("5- Salir de la aplicación");
    System.out.println("-----------------------------");
    //Recoge la opcion del cliente y la manda al servidor
    opcion = sc.nextInt();
    salida.println(opcion);
    System.out.println("Conexion establecida... a " + IP_SERVER + " por el puerto " + PUERTO);
    
    //Envia el ISBN al servidor
    if (opcion == 1){
        String mensajeServidor = bf.readLine();
	    System.out.println(mensajeServidor);
        isbn = sc.nextInt();
        salida.println(isbn);
        mensajeServidor = bf.readLine();
        System.out.println(mensajeServidor);
    //Envia el titulo al servidor
    }else if (opcion == 2) {
        String mensajeServidor = bf.readLine();
	    System.out.println(mensajeServidor);
	    sc.nextLine();
        titulo = sc.nextLine();
        salida.println(titulo);
        mensajeServidor = bf.readLine();
        System.out.println(mensajeServidor);
    //Envia el titulo al servidor
    }else if (opcion == 3) {
    	String mensajeServidor = bf.readLine();
	    System.out.println(mensajeServidor);
	    sc.nextLine();
        autor = sc.nextLine();
        salida.println(autor);
        mensajeServidor = bf.readLine();
        System.out.println(mensajeServidor);
    //Envia la información completa de un libro para añadirlo al servidor
    }else if(opcion == 4) {
    	String mensajeServidor = bf.readLine();
        System.out.println(mensajeServidor);
        mensajeServidor = bf.readLine();
        System.out.println(mensajeServidor);
    	 sc.nextLine();
         datosLibro = sc.nextLine();
         salida.println(datosLibro);
         mensajeServidor = bf.readLine();
         System.out.println(mensajeServidor);
         mensajeServidor = bf.readLine();
	    System.out.println(mensajeServidor);
    //Cierra la conexión
    }else if (opcion == 5){
        String mensajeServidor = bf.readLine();
	    System.out.println(mensajeServidor);
    }else {
        String mensajeServidor = bf.readLine();
        System.out.println(mensajeServidor);
    }
    //Mantiene la conexión activa siempre que la opción no sea 5
    }while (opcion != 5); 
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