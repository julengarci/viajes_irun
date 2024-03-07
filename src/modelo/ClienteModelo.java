package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import vista.Formulario;
import vista.Visor;

public class ClienteModelo extends Conector{

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
}
