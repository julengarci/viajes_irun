package vista;

public class Menu {
	private final static int GESTOR_HOTEL = 3;
	private final static int SALIR = 0;
	
    private static final int DAR_DE_ALTA = 1;
    private static final int MODIFICAR_CLIENTE = 2;
    private static final int BORRAR_CLIENTE = 3;
    
    private static final int DAR_DE_ALTA_RESERVA = 1;
    private static final int DAR_DE_BAJA_RESERVA = 2;
	
	public Menu() {
		
	}
	
	private static void mostrarMenuPrincimpal() {
	    System.out.println("MENU");
	    System.out.println(GESTOR_HOTEL + ". Gestor Hotel");
	    System.out.println(SALIR + ". Salir");
	    System.out.print("Seleccione una opción: ");
	}


	public static void menuReserva() {
        System.out.println("MENU");
        System.out.println(DAR_DE_ALTA_RESERVA + ". Dar de alta reserva");
        System.out.println(DAR_DE_BAJA_RESERVA + ". Dar de baja reserva");
        System.out.println(SALIR + ". Salir");
        System.out.print("Seleccione una opción: ");
	}
	public static void menuClientes() {
        System.out.println("MENU");
        System.out.println(DAR_DE_ALTA + ". Dar de alta cliente");
        System.out.println(MODIFICAR_CLIENTE + ". Modificar cliente");
        System.out.println(BORRAR_CLIENTE + ". Borrar cliente");
        System.out.println(SALIR + ". Salir");
        System.out.print("Seleccione una opción: ");
	}
}
