package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import vista.Formulario;
import vista.Visor;

public class GestorBBDD {

	
	public static void verClientes(){
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		try {
	        String sql = "SELECT * FROM clientes";
	        Connection conexion = Conector.conectar();
	        Statement st = conexion.createStatement();
	        ResultSet rst = st.executeQuery(sql);
	        while(rst.next()) {
				Cliente cliente = new Cliente();
				cliente.setDni(rst.getString("dni"));
				cliente.setApellido(rst.getString("apellidos"));
				cliente.setDireccion(rst.getString("direccion"));
				cliente.setLocalidad(rst.getString("localidad"));
				cliente.setNombre(rst.getString("nombre"));
				clientes.add(cliente);
			}
	        Visor.mostrarClientes(clientes);
	        Conector.CERRAR();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public static void modificarCliente(Scanner scan) {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		try {
	        String sql = "SELECT * FROM clientes";
	        Connection conexion = Conector.conectar();
	        Statement st = conexion.createStatement();
	        ResultSet rst = st.executeQuery(sql);
	        while(rst.next()) {
				Cliente cliente = new Cliente();
				cliente.setDni(rst.getString("dni"));
				cliente.setApellido(rst.getString("apellidos"));
				cliente.setDireccion(rst.getString("direccion"));
				cliente.setLocalidad(rst.getString("localidad"));
				cliente.setNombre(rst.getString("nombre"));
				clientes.add(cliente);
			}
	        Formulario.pedirModificarCliente(scan,clientes);
	        Conector.CERRAR();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public static void updateCliente(Cliente cliente) {
		try {
			String sql = "UPDATE clientes SET nombre=?, apellidos=?, direccion=?, localidad=? WHERE clientes.dni=?";
			Connection con =  Conector.conectar();
			PreparedStatement prts = con.prepareStatement(sql);
			prts.setString(1, cliente.getNombre());
			prts.setString(2, cliente.getApellido());
			prts.setString(3, cliente.getDireccion());
			prts.setString(4, cliente.getLocalidad());
			prts.setString(5, cliente.getDni());
			prts.executeUpdate();
			prts.close();
			System.out.println("Cliente con DNi" + cliente.getDni()  + "Actualizado");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void darAtltaCliente(Scanner scan) {
		try {
			String sql = "INSERT INTO clientes (dni,nombre,apellidos,direccion,localidad) VALUES (?,?,?,?,?)";
			Connection con = Conector.conectar();
			PreparedStatement prts = con.prepareStatement(sql);	
			Cliente cliente = new Cliente();
			cliente.setDni(Formulario.pedriDNI(scan));
			cliente = Formulario.modificarCliente(cliente, scan);
			prts.setString(1, cliente.getDni());
			prts.setString(2, cliente.getNombre());
			prts.setString(3, cliente.getApellido());
			prts.setString(4, cliente.getDireccion());
			prts.setString(5, cliente.getLocalidad());
			prts.executeUpdate();
			prts.close();
			Visor.mostrarCliente(cliente);
			System.out.println("Cliente " + cliente.getNombre() + " Registrado!");
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public static void borraCliente(Scanner scan) {
		try {
			String dni = Formulario.pedriDNI(scan);
			Cliente cliente =  getClienteByDni(dni);
			String sql = "DELETE FROM clientes WHERE clientes.dni = ?";
			Connection con = Conector.conectar();
			PreparedStatement prts = con.prepareStatement(sql);
			prts.setString(1, cliente.getDni());
			prts.executeUpdate();
			prts.close();
			System.out.println("Usuario con DNI " + dni + " Eliminado!");
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	private static Cliente getClienteByDni(String dni) {
		Cliente cliente = new Cliente();
		try {
	        String sql = "SELECT * FROM clientes WHERE clientes.dni = " + dni;
	        Connection conexion = Conector.conectar();
	        Statement st = conexion.createStatement();
	        ResultSet rst = st.executeQuery(sql);
	        while(rst.next()) {
				cliente.setDni(rst.getString("dni"));
				cliente.setApellido(rst.getString("apellidos"));
				cliente.setDireccion(rst.getString("direccion"));
				cliente.setLocalidad(rst.getString("localidad"));
				cliente.setNombre(rst.getString("nombre"));
			}
	        Conector.CERRAR();
	        return cliente;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public static void verReservas(Scanner scan) {
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		try {
	        String sql = "SELECT * FROM reservas";
	        Connection conexion = Conector.conectar();
	        Statement st = conexion.createStatement();
	        ResultSet rst = st.executeQuery(sql);
	        while(rst.next()) {
	            Reserva reserva = new Reserva();
	            reserva.setId(rst.getInt("id"));
	            reserva.setHabitacion(getHabitacionID(rst.getInt("id_habitacion")));
	            reserva.setDesde(rst.getDate("desde"));
	            reserva.setHasta(rst.getDate("hasta"));
	            reserva.setCliente(getClienteByDni(rst.getString("dni")));
	            reservas.add(reserva);
	        }
	        Visor.mostrarReservas(reservas);
	        Conector.CERRAR();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	private static Habitacion getHabitacionID(int id_habitacion) {
		Habitacion habitacion = new Habitacion();
		try {
	        String sql = "SELECT * FROM reservas WHERE reservas.id = " + id_habitacion;
	        Connection conexion = Conector.conectar();
	        Statement st = conexion.createStatement();
	        ResultSet rst = st.executeQuery(sql);
	        while(rst.next()) {
	            habitacion.setId(rst.getInt("id"));
	            habitacion.setNumero(rst.getInt("numero"));
	            habitacion.setDescripcion(rst.getString("descripcion"));
	            habitacion.setPrecio(rst.getInt("precio"));
	            habitacion.setHotel(getHotelById(rst.getInt("cif")));
			}
	        Conector.CERRAR();
	        return habitacion;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	private static Hotel getHotelById(int cif) {
		 Hotel hotel = new Hotel();
		try {
			 String sql = "SELECT * FROM hoteles WHERE hoteles.cif = ?";
	        Connection conexion = Conector.conectar();
	        Statement st = conexion.createStatement();
	        ResultSet rst = st.executeQuery(sql);
	        while(rst.next()) {
	            hotel.setId(rst.getInt("id"));
	            hotel.setCif(rst.getString("cif"));
	            hotel.setNombre(rst.getString("nombre"));
	            hotel.setGerente(rst.getString("gerente"));
	            hotel.setEntrella(rst.getInt("entrella"));
	            hotel.setCompania(rst.getString("compania"));
	            hotel.setHabitacion(getHabitacionID(cif));
			}
	        Conector.CERRAR();
	        return hotel;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}
}
