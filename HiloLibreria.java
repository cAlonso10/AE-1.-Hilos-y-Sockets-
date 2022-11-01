import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

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
		boolean isbnMandado = false;
		boolean tituloMandado = false;
		
		
		ArrayList<Libro> listaLibros = new ArrayList<Libro>();
        Libro l1 = new Libro("Odisea", "Homero", 15, "78153012");
        Libro l2 = new Libro("Don Quijote de la Mancha", "Miguel de cervantes", 20, "10347860");
        Libro l3 = new Libro("Diario", "Ana Frank", 13, "45896301");
        Libro l4 = new Libro("Codigo Da Vinci", "Dan Brown", 22, "13461973");
        Libro l5 = new Libro("Arte de la Guerra", "Sun Tzu", 8, "38792540");
        Libro l6 = new Libro("Iliada", "Homero", 16, "78153565");
        listaLibros.add(l1);
        listaLibros.add(l2);
        listaLibros.add(l3);
        listaLibros.add(l4);
        listaLibros.add(l5);
        listaLibros.add(l6);

        try {	
			salida = new PrintStream(socketAlCliente.getOutputStream());
			entrada = new InputStreamReader(socketAlCliente.getInputStream());
			bf = new BufferedReader(entrada);
            boolean continuar = true;
			while(continuar){		
				System.out.println("Esperando que el " +hilo.getName() + " eliga una opción");
				String opcionCliente = bf.readLine();	
				System.out.println("El " + hilo.getName()+ " ha elegido la opción: " + opcionCliente);
				
                switch (opcionCliente) {
                    case "1":
                        salida.println("Servidor: Escriba el ISBN");
						System.out.println("Esperando que el " +hilo.getName() + " mande un Isbn");
                        String isbnCliente = bf.readLine();	
				        System.out.println("El " + hilo.getName() +  " cliente ha mandado el ISBN: " + isbnCliente);
						for (int i = 0; i < listaLibros.size(); i++) {
                            Libro libros = listaLibros.get(i);
                            if (isbnCliente.equals(libros.getIsbn())) {
								isbnMandado = true;
								System.out.println(libros);
								salida.println(libros);
							}
                        }
						if (isbnMandado == false) {
							salida.println("Servidor: No se encuentra el ISBN en la base de datos");
							System.out.println("No se encuentra el ISBN en la base de datos");
						}
                    break;
                    case "2":
					salida.println("Servidor: Escriba el titulo");
						System.out.println("Esperando que el " +hilo.getName() + " mande un titulo");
                        String tituloCliente = bf.readLine();	
				        System.out.println("El " + hilo.getName() +  " cliente ha mandado el titulo: " + tituloCliente);
						for (int i = 0; i < listaLibros.size(); i++) {
                            Libro libros = listaLibros.get(i);
                            if (tituloCliente.equals(libros.getTitulo())) {
								tituloMandado = true;
								System.out.println(libros);
								salida.println(libros);
							}
                        }
						if (tituloMandado == false) {
							salida.println("Servidor: No se encuentra el titulo en la base de datos");
							System.out.println("No se encuentra el titulo en la base de datos");
						}
                    break;
                    case "3":
    					salida.println("Servidor: Escriba el Autor");
    						System.out.println("Esperando que el " +hilo.getName() + " mande un Autor");
                            String autorCliente = bf.readLine();	
    				        System.out.println("El " + hilo.getName() +  " cliente ha mandado el titulo: " + autorCliente);
                            ArrayList<String> librosEncontrados = new ArrayList<String>();
                            librosEncontrados.clear();
                            //Recorremos el Array de libros buscando coincidencias y las añadimos a la lista de encontrados
    						for (int i = 0; i < listaLibros.size(); i++) {	
                                if (autorCliente.equals(listaLibros.get(i).getAutor())) {
    								librosEncontrados.add((listaLibros.get(i)).toString());
                            }
    						//Si al final de la búsqueda no se ha encontrado nada, librosEncontrados permanecerá vacío
    						}if (librosEncontrados.isEmpty()) {
    							salida.println("Servidor: No se encuentra el autor en la base de datos");
    							System.out.println("No se encuentra el autor en la base de datos");
    						
    						}else {
    						System.out.println(librosEncontrados);
							salida.println("Se ha encontrado:" + librosEncontrados);
    						}
                        break;
                    case "4":
                        salida.println("Servidor: Introduzca los datos del libro a añadir seguido de comas");
                        salida.println("Titulo, Autor, Precio, ISBN");
						System.out.println("Esperando que el " +hilo.getName() + " mande datos del nuevo libro");
                        String nuevoLibro = bf.readLine();
                        System.out.println("El" + hilo.getName() + " cliente ha mandado los siguientes datos: " + nuevoLibro);
                        salida.println("Añadiendo Libro....");
                        //Guardamos los datos introducidos y los separamos por comas
                        String[] datosLibro =  nuevoLibro.split(",[ ]*");
                        //Asignamos cada nuevo dato a una variable para mayor claridad
                        String nTitulo = datosLibro[0];
                        String nAutor = datosLibro[1];
                        int nPrecio = Integer.parseInt(datosLibro[2]);
                        String nIsbn = datosLibro[3];
                        //Añadimos un nuevo libro a la biblioteca con los datos dados
                        listaLibros.add(new Libro(nTitulo,nAutor,nPrecio,nIsbn));
                        salida.println("Se ha Añadido el Libro Correctamente");
                        //Mostrar Libro Añadido:
                        
                        	
                        
                    break;
                    case "5":
                        salida.println("Servidor: Ha salido de la aplicacion");
                        socketAlCliente.close();
                    break;
                
                    default:
                        salida.println("Servidor: Tiene que elegir las opciones por su número");
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
