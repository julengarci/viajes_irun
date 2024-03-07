package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import vista.Formulario;
import vista.Menu;
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
			String cadenaIntroducida = Formulario.pedirStringTeclado();
			Visor.mostrarClientesqueContengan(clientes,cadenaIntroducida);
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
			String sql = "SELECT * FROM clientes WHERE clientes.dni = '" + dni + "'" ;
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
				reserva.setHabitacion(getHabitacionID(rst.getInt("id_habitacion")));
				reserva.setDesde(rst.getDate("desde"));
				reserva.setHasta(rst.getDate("hasta"));
				reserva.setCliente(getClienteByDni(rst.getString("dni")));
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

	private static Habitacion getHabitacionID(int id_habitacion) {
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
				habitacion.setHotel(getHotelById(rst.getInt("id_hotel")));
			}
			Conector.CERRAR();
			return habitacion;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	private static Hotel getHotelById(int id) {
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

	public static void darAltaReserva(Scanner scan) {
		try {
			Reserva reserva = new Reserva();
			String dni = Formulario.pedriDNI(scan);
			if (dni.equalsIgnoreCase(getClienteByDni(dni).getDni())) {

				int id_hotel = Formulario.pedirIdHotel(scan);
				Hotel hotel = getHotelById(id_hotel);
				hotel = Visor.mostarHabitacionHotel(scan,hotel);

				int id_habitacion = Formulario.pedirIdHabitacion(scan);
				reserva.setCliente(getClienteByDni(dni));
				reserva.setHabitacion(getHabitacionID(id_habitacion));
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

	private static Hotel getHotelByNombre(String nombreHotel) {
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

	public static void verHotelHabitaciones(Scanner scan) {
		ArrayList<Hotel> hoteles = new ArrayList();
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
				hotel.setHabitacion(getHabitacionID(rst.getInt("B.id")));
				hoteles.add(hotel);
			}
			con.close();
			Visor.mostrarHoteles(hoteles);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void editarHabitacion(Scanner scan) {
		int id = Formulario.pedirIdHabitacion(scan);
		Habitacion habitacion = new Habitacion();
		habitacion = getHabitacionID(id);
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

	public static Hotel verHabitacionesDeunHotel(Scanner scan, Hotel hotel) {
		try {
			String sql = "SELECT * FROM habitaciones WHERE habitaciones.id_hotel = ?";
			PreparedStatement prst = Conector.conectar().prepareStatement(sql);
			prst.setInt(1, hotel.getId());
			ResultSet rst = prst.executeQuery();
			while(rst.next()) {
				hotel.setHabitacion(getHabitacionID(rst.getInt("id")));
			}
			Visor.mostrHabitacion(hotel.getHabitaciones());
			return hotel;
		} catch (Exception e) {
			System.err.println(e);
		}
		return hotel;

	}

	public static void anularReserva(Scanner scan) {
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

	public static void darAltaHotel(Scanner scan) {
		Hotel hotel = new Hotel();
		hotel = Formulario.pedirNuevosDatorHotel(scan,hotel);
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
		int opciones = 0;
		do {
			Visor.mostrarOopcionesHotel();
			opciones = Formulario.pedirOpciones(scan);
			switch (opciones) {
			case 1:
				darAltaHabitacion(hotel,scan);
				break;
			default:
				break;
			}
		} while (opciones!=Menu.SALIR);
	}

	private static void darAltaHabitacion(Hotel hotel, Scanner scan) {
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

	public static void verClientesOrdenadosA() {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		try {
			String sql = "SELECT * FROM clientes";
			PreparedStatement prst = Conector.conectar().prepareStatement(sql);
			ResultSet rst = prst.executeQuery(sql);
			while(rst.next()) {
				Cliente cliente = new Cliente();
				cliente.setApellido(rst.getString("apellidos"));
				cliente.setDireccion(rst.getString("direccion"));
				cliente.setDni(rst.getString("dni"));
				cliente.setLocalidad(rst.getString("localidad"));
				cliente.setNombre(rst.getString("nombre"));
				clientes.add(cliente);
			}
			clientes.sort(new ComparadorApellidos());
			Visor.mostrarClientes(clientes);
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	public static void verCleintesOrdenadosN() {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		try {
			String sql = "SELECT * FROM clientes";
			PreparedStatement prst = Conector.conectar().prepareStatement(sql);
			ResultSet rst = prst.executeQuery();
			while(rst.next()) {
				Cliente cliente = new Cliente();
				cliente.setApellido(rst.getString("apellidos"));
				cliente.setDireccion(rst.getString("direccion"));
				cliente.setDni(rst.getString("dni"));
				cliente.setLocalidad(rst.getString("localidad"));
				cliente.setNombre(rst.getString("nombre"));
				clientes.add(cliente);
			}
			clientes.sort(new ComparadorNombre());
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public static void verReservasEndosFechas() {
		Scanner scan = new Scanner(System.in);
		Date fechaEntrada = Formulario.pedirFechaDesde(scan);
		Date fechaSalida = Formulario.pedirFechaHasta(scan);
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		try {
			String sql = "SELECT * FROM reservas";
			PreparedStatement prst = Conector.conectar().prepareStatement(sql);
			ResultSet rst = prst.executeQuery();
			while(rst.next()) {
				Reserva reserva = new Reserva();
				reserva.setCliente(getClienteByDni(rst.getString("dni")));
				reserva.setDesde(rst.getDate("desde"));
				reserva.setHabitacion(getHabitacionID(rst.getInt("id_Habitacion")));
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
		
		try {
			
			String sql = "SELECT * FROM reservas WHERE reservas.dni = ?";
			PreparedStatement prst = Conector.conectar().prepareStatement(sql);
			prst.setString(1, dni_Cliente);
			ResultSet rst = prst.executeQuery();
			while(rst.next()) {
				Reserva reserva = new Reserva();
				reserva.setCliente(getClienteByDni(rst.getString("dni")));
				reserva.setDesde(rst.getDate("desde"));
				reserva.setHabitacion(getHabitacionID(rst.getInt("id_habitacion")));
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
