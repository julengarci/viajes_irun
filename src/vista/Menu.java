package vista;

public class Menu {
	
	public final static int SALIR = 0;
	public static final int DAR_DE_ALTA = 1;
	public static final int MODIFICAR_CLIENTE = 2;
	public static final int BORRAR_CLIENTE = 3;
	public static final int VER_CLIENTES = 4;
    
	public static final int VER_RESERVAS = 5;
	public static final int DAR_DE_ALTA_RESERVA = 6;
    public static final int DAR_DE_BAJA_RESERVA = 7;
    public static final int DAR_DE_ALTA_HOTEL = 8;
	
	public Menu() {
		
	}
	
	public static void menuPrincimpal() {
        System.out.println("MENU");
        System.out.println(DAR_DE_ALTA + ". Dar de alta cliente");
        System.out.println(MODIFICAR_CLIENTE + ". Modificar cliente");
        System.out.println(BORRAR_CLIENTE + ". Borrar cliente");
        System.out.println(VER_CLIENTES + ". Ver clientes");

        System.out.println(VER_RESERVAS + ". Ver Reservas");
        System.out.println(DAR_DE_ALTA_RESERVA + ". Dar de alta reserva");
        System.out.println(DAR_DE_BAJA_RESERVA + ". Dar de baja reserva");
        System.out.println(DAR_DE_ALTA_HOTEL + ". Dar de alta Hotel");
        System.out.println(SALIR + ". Salir");
        System.out.print("Seleccione una opción: ");
		
	}
}
