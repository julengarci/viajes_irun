package controlador;

import java.util.Scanner;

import modelo.ClienteModelo;
import modelo.GestorBBDD;
import vista.Formulario;
import vista.Menu;

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
		GestorBBDD.darAltaHotel(scan);

	}

	private static void anularHabitacion(Scanner scan) {
		GestorBBDD.borrarHabitacion(scan);

	}

	private static void anularReserva(Scanner scan) {
		GestorBBDD.anularReserva(scan);

	}

	private static void editarHabitacion(Scanner scan) {
		GestorBBDD.editarHabitacion(scan);

	}

	private static void verHotelHabitaciones(Scanner scan) {
		GestorBBDD.verHotelHabitaciones(scan);

	}

	private static void darAltaReserva(Scanner scan) {
		GestorBBDD.darAltaReserva(scan);
	}

	private static void verReservas(Scanner scan) {
		Menu menu = new Menu();
		int opcion = 0;
		do {
			menu.mostrarMenuReservas();
			opcion = Formulario.pedirOpciones(scan);
			switch (opcion) {
			case Menu.CONSULTA_RESERVAS_EN_DOS_FECHAS:
				GestorBBDD.verReservasEndosFechas();
				break;
			case Menu.CONSULTAR_RESERVAS_DE_UN_CLIENTE:
				GestorBBDD.verReservasCliente();
				break;
			case Menu.CONSULTAR_RESERVA_POR_HOTEL:
				GestorBBDD.verReservas(scan);
				break;
			default:
				break;
			}
		} while (opcion!=Menu.SALIR);
	}

	private static void borrarCliente(Scanner scan) {
		GestorBBDD.borraCliente(scan);
	}

	private static void darAltaCliente(Scanner scan) {
		GestorBBDD.darAtltaCliente(scan);

	}

	//	private static void modificarClientes(Scanner scan) {
	//		GestorBBDD.modificarCliente(scan);
	//	}

	private static void verClientes(Scanner scan) {
		Menu menu = new Menu();
		int opcion = 0;
		do {
			menu.mostrarMenuClientes();
			opcion = Formulario.pedirOpciones(scan);
			switch (opcion) {
			case Menu.VER_CLIENTES_ORDENADOS_POR_APELLIDO:
				GestorBBDD.verClientesOrdenadosA();
				break;
			case Menu.VER_CLIENTES_ORDENADOS_POR_NOMBRE:
				GestorBBDD.verCleintesOrdenadosN();
				break;
			case Menu.VER_CLIENTES_QUE_CONTENGAN_CADENA:
				ClienteModelo.verClientes();
				break;
			default:
				break;
			}		} while (opcion!=Menu.SALIR);
		//GestorBBDD.verClientes();
	}
}
