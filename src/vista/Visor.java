package vista;

import java.util.ArrayList;
import java.util.Scanner;

import modelo.Cliente;
import modelo.Habitacion;
import modelo.HabitacionModelo;
import modelo.Hotel;
import modelo.HotelModelo;
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

	public static void mostrarReserva(Reserva reserva) {
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
		HotelModelo hotelModelo = new HotelModelo();
		return hotelModelo.verHabitacionesDeunHotel(scan,hotel);
	}

	public static void mostrHabitacion(ArrayList<Habitacion> habitaciones) {
		for (Habitacion habitacion : habitaciones) {
			System.out.println(habitacion);
		}

	}

	public static void mostrarStatementExitoso() {
		System.out.println("Reserva Eliminada Correctamente");

	}

	public static void mostrarOopcionesHotel() {
		System.out.println("0. Salir");
		System.out.println("1. Crear Habitacion");

	}

	public static void mostrarClientesqueContengan(ArrayList<Cliente> clientes, String cadenaIntroducida) {
		for (Cliente cliente : clientes) {
			if(cliente.getNombre().toLowerCase().contains(cadenaIntroducida) || cliente.getApellido().toLowerCase().contains(cadenaIntroducida)) {
				System.out.println(cliente);
			}
		}
	}

	public static void mostrarResrvas(ArrayList<Reserva> reservas) {
		for (Reserva reserva : reservas) {
			System.out.println(reserva);
		}
	}
}
