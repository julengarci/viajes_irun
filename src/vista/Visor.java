package vista;

import java.util.ArrayList;

import modelo.Cliente;

public class Visor {

	public static void mostrarClientes(ArrayList<Cliente> clientes) {
		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}
	}

	public static void mostrarCliente(Cliente cliente) {
		System.out.println(cliente);
	}

}
