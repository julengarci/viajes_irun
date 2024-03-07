package controlador;

import java.util.Scanner;

import modelo.ClienteModelo;
import modelo.HabitacionModelo;
import modelo.Hotel;
import modelo.HotelModelo;
import modelo.ReservaModelo;
import vista.Formulario;
import vista.Menu;
import vista.Visor;

public class GestorHotel {
	public static void main(String[] args) {
		int opcion = 0;
		Scanner scan = new Scanner(System.in);

		do {
			Menu.menuPrincimpal();
			opcion = Integer.parseInt(scan.nextLine());
			switch (opcion) {
			case Menu.VER_CLIENTES:
				verClientes(scan);
				break;
			case Menu.DAR_DE_ALTA_CLIENTE:
				darAltaCliente(scan);
				break;
			case Menu.VER_HOTEL_HABITACIONES:
				verHotelHabitaciones(scan);
				break;
			case Menu.DAR_DE_ALTA_RESERVA:
				darAltaReserva(scan);
				break;
			case Menu.EDITAR_HABITACION:
				editarHabitacion(scan);
				break;
			case Menu.VER_RESERVAS:
				verReservas(scan);
				break;
			case Menu.BORRAR_CLIENTE:
				borrarCliente(scan);
				break;
			case Menu.DAR_DE_BAJA_RESERVA:
				anularReserva(scan);
				break;
			case Menu.DAR_DE_BAJA_HABITACION:
				anularHabitacion(scan);
				break;
			case Menu.ALTA_HOTEL:
				darAltaHotel(scan);
				break;
			default:
				break;
			}
		} while (opcion!=Menu.SALIR);
	}

	private static void darAltaHotel(Scanner scan) {
		Hotel hotel = new Hotel();
		hotel = Formulario.pedirNuevosDatorHotel(scan,hotel);
		HotelModelo hotelModelo = new HotelModelo();
		hotelModelo.darAltaHotel(hotel);
		
		HabitacionModelo habitacionModelo = new HabitacionModelo();
		
		int opciones = 0;
		do {
			Visor.mostrarOopcionesHotel();
			opciones = Formulario.pedirOpciones(scan);
			switch (opciones) {
			case 1:
				habitacionModelo.darAltaHabitacion(hotel,scan);
				break;
			default:
				break;
			}
		} while (opciones!=Menu.SALIR);

	}

	private static void anularHabitacion(Scanner scan) {
		HabitacionModelo.borrarHabitacion(scan);

	}

	private static void anularReserva(Scanner scan) {
		ReservaModelo reservaModelo = new ReservaModelo();
		reservaModelo.anularReserva(scan);
	}

	private static void editarHabitacion(Scanner scan) {
		HabitacionModelo habitacionModelo = new HabitacionModelo();
		habitacionModelo.editarHabitacion(scan);

	}

	private static void verHotelHabitaciones(Scanner scan) {
		HotelModelo.verHotelHabitaciones(scan);

	}

	private static void darAltaReserva(Scanner scan) {
		ReservaModelo reservaModelo = new ReservaModelo();
		reservaModelo.darAltaReserva(scan);
	}

	private static void verReservas(Scanner scan) {
		Menu menu = new Menu();
		ReservaModelo reservaModelo = new ReservaModelo();
		int opcion = 0;
		do {
			menu.mostrarMenuReservas();
			opcion = Formulario.pedirOpciones(scan);
			switch (opcion) {
			case Menu.CONSULTA_RESERVAS_EN_DOS_FECHAS:
				reservaModelo.verReservasEndosFechas();
				break;
			case Menu.CONSULTAR_RESERVAS_DE_UN_CLIENTE:
				reservaModelo.verReservasCliente();
				break;
			case Menu.CONSULTAR_RESERVA_POR_HOTEL:
				reservaModelo.verReservas(scan);
				break;
			default:
				break;
			}
		} while (opcion!=Menu.SALIR);
	}

	private static void borrarCliente(Scanner scan) {
		ClienteModelo clienteModelo = new ClienteModelo();
		clienteModelo.borraCliente(scan);
	}

	private static void darAltaCliente(Scanner scan) {
		ClienteModelo clienteModelo = new ClienteModelo();
		clienteModelo.darAtltaCliente(scan);

	}

	//	private static void modificarClientes(Scanner scan) {
	//		GestorBBDD.modificarCliente(scan);
	//	}

	private static void verClientes(Scanner scan) {
		Menu menu = new Menu();
		ClienteModelo clienteModelo = new ClienteModelo();
		int opcion = 0;
		do {
			menu.mostrarMenuClientes();
			opcion = Formulario.pedirOpciones(scan);
			switch (opcion) {
			case Menu.VER_CLIENTES_ORDENADOS_POR_APELLIDO:
				clienteModelo.verClientesOrdenadosA();
				break;
			case Menu.VER_CLIENTES_ORDENADOS_POR_NOMBRE:
				clienteModelo.verCleintesOrdenadosN();
				break;
			case Menu.VER_CLIENTES_QUE_CONTENGAN_CADENA:
				clienteModelo.verClientes();
				break;
			default:
				break;
			}		} while (opcion!=Menu.SALIR);
		//GestorBBDD.verClientes();
	}
}
