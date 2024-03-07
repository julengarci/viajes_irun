package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import vista.Formulario;
import vista.Visor;

public class HabitacionModelo extends Conector{

	public Habitacion getHabitacionID(int id_habitacion) {
		HotelModelo hotelModelo = new HotelModelo();
		
		Habitacion habitacion = new Habitacion();
		try {
			String sql = "SELECT * FROM habitaciones WHERE habitaciones.id = " + id_habitacion;
			Connection conexion = Conector.conectar();
			Statement st = conexion.createStatement();
			ResultSet rst = st.executeQuery(sql);
			while(rst.next()) {
				habitacion.setId(rst.getInt("id"));
				habitacion.setNumero(rst.getInt("numero"));
				habitacion.setDescripcion(rst.getString("descripcion"));
				habitacion.setPrecio(rst.getInt("precio"));
				habitacion.setHotel(hotelModelo.getHotelById(rst.getInt("id_hotel")));
			}
			Conector.CERRAR();
			return habitacion;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}
	
	public void editarHabitacion(Scanner scan) {
		HabitacionModelo habitacionModelo = new HabitacionModelo();
		
		int id = Formulario.pedirIdHabitacion(scan);
		Habitacion habitacion = new Habitacion();
		habitacion = habitacionModelo.getHabitacionID(id);
		habitacion = Formulario.modificarHabitacion(scan,habitacion);
		try {
			String sql = "UPDATE habitaciones SET numero=?,descripcion=?,precio=?";
			PreparedStatement prst = Conector.conectar().prepareStatement(sql);
			prst.setInt(1, habitacion.getNumero());
			prst.setString(2, habitacion.getDescripcion());
			prst.setInt(3, habitacion.getPrecio());
			prst.executeUpdate();
			prst.close();
			Visor.mostrHabitacion(habitacion);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void borrarHabitacion(Scanner scan) {
		int id_habitacion = Formulario.pedirIdHabitacion(scan);
		try {
			String sql = "DELETE FROM habitaciones WHERE habitaciones.id = ?";
			PreparedStatement prst = Conector.conectar().prepareStatement(sql);
			prst.setInt(1, id_habitacion);
			prst.executeUpdate();
			prst.close();
			System.out.println("Habitacion Borrada Exitosamente");
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	public void darAltaHabitacion(Hotel hotel, Scanner scan) {
		hotel.setHabitacion(Formulario.pedirNuevosDatosHabitacion(scan,hotel.getHabitaciones()));
		try {
			String sql = "INSERT INTO habitaciones(id_hotel,numero,descripcion,precio) VALUES (?,?,?,?)";
			PreparedStatement prst = Conector.conectar().prepareStatement(sql);
			prst.setInt(1, hotel.getId());
			prst.setInt(2, hotel.getHabitaciones().getNumero());
			prst.setString(3,  hotel.getHabitaciones().getDescripcion());
			prst.setInt(4,  hotel.getHabitaciones().getPrecio());
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	
}
