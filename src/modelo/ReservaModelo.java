package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import vista.Formulario;
import vista.Visor;

public class ReservaModelo extends Conector{

	public void verReservas(Scanner scan) {
		ClienteModelo clienteModelo = new ClienteModelo();
		HabitacionModelo habitacionModelo = new HabitacionModelo();
		
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		int id_hotel = Formulario.pedirIdHotel(scan);
		try {
			String sql = "SELECT * FROM reservas A JOIN habitaciones B on A.id_habitacion=B.id";
			Connection conexion = Conector.conectar();
			Statement st = conexion.createStatement();
			ResultSet rst = st.executeQuery(sql);
			while(rst.next()) {
				SimpleDateFormat format = new SimpleDateFormat("");
				Reserva reserva = new Reserva();
				reserva.setId(rst.getInt("id"));
				reserva.setHabitacion(habitacionModelo.getHabitacionID(rst.getInt("id_habitacion")));
				reserva.setDesde(rst.getDate("desde"));
				reserva.setHasta(rst.getDate("hasta"));
				reserva.setCliente(clienteModelo.getClienteByDni(rst.getString("dni")));
				reservas.add(reserva);
			}
			for (Reserva reserva : reservas) {
				if(reserva.getHabitacion().getHotel().getId() == id_hotel) {
					Visor.mostrarReserva(reserva);
				}
			}
			Conector.CERRAR();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public static void darAltaReserva(Scanner scan) {
		ClienteModelo clienteModelo = new ClienteModelo();
		HotelModelo hotelModelo = new HotelModelo();
		HabitacionModelo habitacionModelo = new HabitacionModelo();
		
		try {
			Reserva reserva = new Reserva();
			String dni = Formulario.pedriDNI(scan);
			if (dni.equalsIgnoreCase(clienteModelo.getClienteByDni(dni).getDni())) {

				int id_hotel = Formulario.pedirIdHotel(scan);
				Hotel hotel = hotelModelo.getHotelById(id_hotel);
				hotel = Visor.mostarHabitacionHotel(scan,hotel);

				int id_habitacion = Formulario.pedirIdHabitacion(scan);
				reserva.setCliente(clienteModelo.getClienteByDni(dni));
				reserva.setHabitacion(habitacionModelo.getHabitacionID(id_habitacion));
				reserva.setDesde(Formulario.pedirFechaDesde(scan));
				reserva.setHasta(Formulario.pedirFechaHasta(scan));

				String sql = "INSERT INTO reservas (id_habitacion, dni, desde, hasta) VALUES (?,?,?,?)";
				Connection con = Conector.conectar();
				PreparedStatement prst = con.prepareStatement(sql);
				prst.setInt(1, reserva.getHabitacion().getId());
				prst.setString(2, reserva.getCliente().getDni());
				prst.setDate(3, reserva.getDesde());
				prst.setDate(4, reserva.getHasta());
				prst.executeUpdate();
				prst.close();
				Visor.mostrarResrvaAceptada(reserva);
			}else {
				Visor.mostrarError();
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void anularReserva(Scanner scan) {
		int id_reserva = Formulario.pedirIdReserva(scan); 
		try {
			String sql = "DELETE FROM reservas WHERE reservas.id = ?";
			PreparedStatement prst = Conector.conectar().prepareStatement(sql);
			prst.setInt(1, id_reserva);
			prst.executeUpdate();
			prst.close();
			Visor.mostrarStatementExitoso();
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	public static void verReservasEndosFechas() {
		Scanner scan = new Scanner(System.in);
		
		ClienteModelo clienteModelo = new ClienteModelo();
		HabitacionModelo habitacionModelo = new HabitacionModelo();
		
		Date fechaEntrada = Formulario.pedirFechaDesde(scan);
		Date fechaSalida = Formulario.pedirFechaHasta(scan);
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		try {
			String sql = "SELECT * FROM reservas";
			PreparedStatement prst = Conector.conectar().prepareStatement(sql);
			ResultSet rst = prst.executeQuery();
			while(rst.next()) {
				Reserva reserva = new Reserva();
				reserva.setCliente(clienteModelo.getClienteByDni(rst.getString("dni")));
				reserva.setDesde(rst.getDate("desde"));
				reserva.setHabitacion(habitacionModelo.getHabitacionID(rst.getInt("id_Habitacion")));
				reserva.setId(rst.getInt("id"));
				reserva.setHasta(rst.getDate("hasta"));
				reservas.add(reserva);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		for (Reserva reserva : reservas) {
			int fchentrada = reserva.getDesde().compareTo(fechaEntrada);
			int fchSalida = reserva.getHasta().compareTo(fechaSalida);
			if(reserva.getDesde().before(fechaSalida) && reserva.getHasta().after(fechaEntrada) || reserva.getDesde().after(fechaEntrada) && reserva.getDesde().before(fechaSalida)) {
				Visor.mostrarReserva(reserva);
			}
			else {
				//system.out.println(reserva + " \n no validas");
			}
		}
	}
	
public static void verReservasCliente() {
		
		String dni_Cliente = Formulario.pedirDniCliente();
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		
		ClienteModelo clienteModelo = new ClienteModelo();
		HabitacionModelo habitacionModelo = new HabitacionModelo();
		
		try {
			
			String sql = "SELECT * FROM reservas WHERE reservas.dni = ?";
			PreparedStatement prst = Conector.conectar().prepareStatement(sql);
			prst.setString(1, dni_Cliente);
			ResultSet rst = prst.executeQuery();
			while(rst.next()) {
				Reserva reserva = new Reserva();
				reserva.setCliente(clienteModelo.getClienteByDni(rst.getString("dni")));
				reserva.setDesde(rst.getDate("desde"));
				reserva.setHabitacion(habitacionModelo.getHabitacionID(rst.getInt("id_habitacion")));
				reserva.setHasta(rst.getDate("hasta"));
				reserva.setId(rst.getInt("id"));
				reservas.add(reserva);
			}
			Visor.mostrarResrvas(reservas);
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
