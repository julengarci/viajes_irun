package vista;

import java.util.ArrayList;
import java.util.Scanner;

import modelo.Cliente;
import modelo.GestorBBDD;
import modelo.Habitacion;
import modelo.Hotel;
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

	public static void mostrarReservas(Reserva reserva) {
		System.out.println(reserva);
	}

	public static void mostrarHoteles(ArrayList<Hotel> hoteles) {
		for (Hotel hotel : hoteles) {
			System.out.println(hotel);
		}
	}

	public static void mostrarError() {
		System.out.println("Error Usuario No resgitrado");
		
	}

	public static void mostrarResrvaAceptada(Reserva reserva) {
		System.out.println(reserva + " :: realizada");
	}

	public static Hotel mostarHabitacionHotel(Scanner scan, Hotel hotel) {
		return GestorBBDD.verHabitacionesDeunHotel(scan,hotel);
	}

	public static void mostrHabitacion(Habitacion habitacion) {
		System.out.println(habitacion);
		
	}

}
