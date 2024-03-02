package vista;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import modelo.Cliente;
import modelo.GestorBBDD;
import modelo.Habitacion;

public class Formulario {

	public static void pedirModificarCliente(Scanner scan, ArrayList<Cliente> clientes) {
		String dni = pedriDNI(scan);
		for (Cliente cliente : clientes) {
			if (cliente.getDni().equals(dni)) {
				Visor.mostrarCliente(cliente);
				cliente =  modificarCliente(cliente,scan);
				GestorBBDD.updateCliente(cliente);
			}
		}
	}

	public static Cliente modificarCliente(Cliente cliente, Scanner scan) {
	        System.out.println("Introduce el nombre:");
	        String nombre = scan.nextLine();
	        cliente.setNombre(nombre);
	        System.out.println("Introduce el apellido:");
	        String apellido = scan.nextLine();
	        cliente.setApellido(apellido);
	        System.out.println("Introduce la dirección:");
	        String direccion = scan.nextLine();
	        cliente.setDireccion(direccion);
	        System.out.println("Introduce la localidad:");
	        String localidad = scan.nextLine();
	        cliente.setLocalidad(localidad);		
	        return cliente;
	}

	public static String pedriDNI(Scanner scan) {
		System.out.println("Introduzca el DNI del cliente");
		String dni =  scan.nextLine();
		return dni;
	}

	public static int pedirIdHabitacion(Scanner scan) {
		System.out.println("Introduzca el Id de la Habitacion");
		int id =  Integer.parseInt(scan.nextLine());
		return id;
	}

	public static Date pedirFechaDesde(Scanner scan) {
		System.out.println("Introduzca la fecha de entrada de la Reserva con el formato" + "\t año-mes-dia");
		Date fecha = Date.valueOf(scan.nextLine());
		return fecha;
	}

	public static Date pedirFechaHasta(Scanner scan) {
		System.out.println("Introduzca la fecha de salida de la Reserva con el formato"  + "\t año-mes-dia");
		Date fecha = Date.valueOf(scan.nextLine());
		return fecha;
	}

	public static String pedirNombreHotel(Scanner scan) {
		System.out.println("Introduzca el nombre del Hotel");
		String nHotel = scan.nextLine();
		return nHotel;
	}

	public static int pedirIdHotel(Scanner scan) {
		System.out.println("Introduzca el id del Hotel");
		int id_hotel = Integer.parseInt(scan.nextLine());
		return id_hotel;
	}

	public static Habitacion modificarHabitacion(Scanner scan, Habitacion habitacion) {
		System.out.println("Introduzca Los nuevos valores para la descripcion");
		habitacion.setDescripcion(scan.nextLine());
		System.out.println("Introduzca Los nuevos valores para el numero de habitacion");
		habitacion.setNumero(Integer.parseInt(scan.nextLine()));
		System.out.println("Introduzca Los nuevos valores para el precio");
		habitacion.setPrecio(Integer.parseInt(scan.nextLine()));
		return habitacion;
	}
}
