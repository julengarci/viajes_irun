package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector {
	private final static String BBDD = "viajesirun";
	private final static String Usuario = "root";
	private final static String contraseña = "";
	private final static String HOST = "localhost";
	private static Connection con = null;
	
	 public static Connection conectar() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://" + HOST + "/" +BBDD,Usuario,contraseña);
			} catch (Exception e) {
				System.out.println(e);
		}
			return con;
	 }
	 
	 
	 public static void CERRAR() {
		    try {
		        if (con != null && !con.isClosed()) {
		            con.close();
//		            System.out.println("Conexión cerrada");
		        }
		    } catch (SQLException e) {
		        System.out.println("Error al cerrar la conexión: " + e.getMessage());
		    }
	 	}
}
