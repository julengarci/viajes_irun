package controlador;

import java.util.Scanner;

import modelo.GestorBBDD;
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
			case Menu.MODIFICAR_CLIENTE:
				modificarClientes(scan);
				break;
			case Menu.DAR_DE_ALTA:
				darAltaCliente(scan);
				break;
			case Menu.BORRAR_CLIENTE:
				borrarCliente(scan);
				break;
			case Menu.VER_RESERVAS:
				verReservas(scan);
				break;
			case Menu.DAR_DE_ALTA_RESERVA:
				darAltaReserva(scan);
				break;
			default:
				break;
			}
		} while (opcion!=Menu.SALIR);
    }

	private static void darAltaReserva(Scanner scan) {
		// TODO Auto-generated method stub
		
	}

	private static void verReservas(Scanner scan) {
		GestorBBDD.verReservas(scan);
	}

	private static void borrarCliente(Scanner scan) {
		GestorBBDD.borraCliente(scan);
	}

	private static void darAltaCliente(Scanner scan) {
		GestorBBDD.darAtltaCliente(scan);
		
	}

	private static void modificarClientes(Scanner scan) {
		GestorBBDD.modificarCliente(scan);
	}

	private static void verClientes(Scanner scan) {
		GestorBBDD.verClientes();
	}
}
