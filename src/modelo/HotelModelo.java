package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import vista.Formulario;
import vista.Menu;
import vista.Visor;

public class HotelModelo extends Conector{
	
	public Hotel getHotelById(int id) {
		Hotel hotel = new Hotel();
		try {
			String sql = "SELECT * FROM hoteles WHERE hoteles.id = " + id;
			Connection conexion = Conector.conectar();
			Statement st = conexion.createStatement();
			ResultSet rst = st.executeQuery(sql);
			while(rst.next()) {
				hotel.setId(rst.getInt("id"));
				hotel.setCif(rst.getString("cif"));
				hotel.setNombre(rst.getString("nombre"));
				hotel.setGerente(rst.getString("gerente"));
				hotel.setEstrella(rst.getInt("estrellas"));
				hotel.setCompania(rst.getString("compania"));
			}
			Conector.CERRAR();
			return hotel;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}
	
	public Hotel getHotelByNombre(String nombreHotel) {
		try {
			String sql = "SELECT * FROM hoteles WHERE hoteles.nombre = ?";
			PreparedStatement psrt = Conector.conectar().prepareStatement(sql);
			psrt.setString(1, nombreHotel);
			ResultSet rst = psrt.executeQuery();
			Hotel hotel = new Hotel();
			while(rst.next()) {
				hotel.setId(rst.getInt("id"));
				hotel.setCif(rst.getString("cif"));
				hotel.setNombre(rst.getString("nombre"));
				hotel.setGerente(rst.getString("gerente"));
				hotel.setEstrella(rst.getInt("estrellas"));
				hotel.setCompania(rst.getString("compania"));
			}
			return hotel;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public void darAltaHotel(Hotel hotel) {
		
		try {
			String sql = "INSERT INTO hoteles(cif,nombre,gerente,estrellas,compania) VALUES (?,?,?,?,?)";
			PreparedStatement prst = Conector.conectar().prepareStatement(sql);
			prst.setString(1, hotel.getCif());
			prst.setString(2, hotel.getNombre());
			prst.setString(3, hotel.getGerente());
			prst.setInt(4, hotel.getEstrella());
			prst.setString(5, hotel.getCompania() );
			prst.executeUpdate();
			prst.close();
		} catch (Exception e) {
			System.err.println(e);
		}
		
	}
	
	public static Hotel verHabitacionesDeunHotel(Scanner scan, Hotel hotel) {
		
		HabitacionModelo habitacionModelo = new HabitacionModelo();
		
		try {
			String sql = "SELECT * FROM habitaciones WHERE habitaciones.id_hotel = ?";
			PreparedStatement prst = Conector.conectar().prepareStatement(sql);
			prst.setInt(1, hotel.getId());
			ResultSet rst = prst.executeQuery();
			while(rst.next()) {
				hotel.setHabitaciones(habitacionModelo.getHabitacionID(rst.getInt("id")));
			}
			Visor.mostrHabitacion(hotel.getHabitaciones());
			return hotel;
		} catch (Exception e) {
			System.err.println(e);
		}
		return hotel;

	}
	
	public static void verHotelHabitaciones(Scanner scan) {
		ArrayList<Hotel> hoteles = new ArrayList();
		
		HabitacionModelo habitacionModelo = new HabitacionModelo();
		
		try {
			String sql = "SELECT * FROM hoteles A JOIN habitaciones B ON A.id = B.id_hotel";
			Connection con = Conector.conectar();
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rst = st.executeQuery();
			while (rst.next()) {
				Hotel hotel = new Hotel();
				hotel.setId(rst.getInt("id"));
				hotel.setCif(rst.getString("cif"));
				hotel.setNombre(rst.getString("nombre"));
				hotel.setGerente(rst.getString("gerente"));
				hotel.setEstrella(rst.getInt("estrellas"));
				hotel.setCompania(rst.getString("compania"));
				hotel.setHabitaciones(habitacionModelo.getHabitacionID(rst.getInt("B.id")));
				hoteles.add(hotel);
			}
			con.close();
			Visor.mostrarHoteles(hoteles);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	
}
