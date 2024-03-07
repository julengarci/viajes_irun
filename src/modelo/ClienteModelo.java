package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import vista.Formulario;
import vista.Visor;

public class ClienteModelo extends Conector{

	public void verClientes(){
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
	
	public void darAtltaCliente(Scanner scan) {
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
	
	public void borraCliente(Scanner scan) {
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
	
	public Cliente getClienteByDni(String dni) {
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

	public static void updateCliente(Cliente cliente) {
		
		
	}
}
