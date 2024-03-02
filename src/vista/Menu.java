package vista;

public class Menu {
	
	public final static int SALIR = 0;
	public static final int VER_CLIENTES = 1;
	public static final int DAR_DE_ALTA = 2;
	public static final int VER_HOTEL_HABITACIONES = 3;
	public static final int DAR_DE_ALTA_RESERVA = 4;
	public static final int EDITAR_HABITACION = 5;
//	public static final int MODIFICAR_CLIENTE = 100;
	public static final int VER_RESERVAS = 6;
    
	public static final int BORRAR_CLIENTE = 7;
    public static final int DAR_DE_BAJA_RESERVA = 8;
    public static final int DAR_DE_ALTA_HABITACION = 9;
	
	public Menu() {
		
	}
	
	public static void menuPrincimpal() {
        System.out.println("MENU");
        System.out.println(VER_CLIENTES + ". Ver clientes");
        System.out.println(DAR_DE_ALTA + ". Registrar cliente");
        System.out.println(VER_HOTEL_HABITACIONES + ". Ver hotel con sus habitaciones");
        System.out.println(DAR_DE_ALTA_RESERVA + ". Realizar reserva");
        System.out.println(EDITAR_HABITACION + ". Editar Habitacion");
        System.out.println(VER_RESERVAS + ". Ver Reservas del Hotel");
        
        System.out.println(DAR_DE_BAJA_RESERVA + ". Eliminar Reserva");
        System.out.println(BORRAR_CLIENTE + ". Eliminar Cliente");
        System.out.println(DAR_DE_ALTA_HABITACION + ". Eliminar Habitacion");

        System.out.println(SALIR + ". Salir");
        System.out.print("Seleccione una opción: ");
		
	}
}
