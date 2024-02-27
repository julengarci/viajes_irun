package vista;

import java.util.ArrayList;

import modelo.Cliente;
import modelo.Reserva;

public class Visor {

	public static void mostrarClientes(ArrayList<Cliente> clientes) {
		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}
	}

	public static void mostrarCliente(Cliente cliente) {
		System.out.println(cliente);
	}

	public static void mostrarReservas(ArrayList<Reserva> reservas) {
		for (Reserva reserva : reservas) {
			System.out.println(reserva);
		}
	}

}
