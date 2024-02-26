package controlador;

import java.util.Scanner;

import vista.Menu;

public class GestorHotel {
    public static void main(String[] args) {
        int opcion = 0;
        Scanner scan = new Scanner(System.in);
        
        do {
        	Menu.menuPrincimpal();
			opcion = Integer.parseInt(scan.nextLine());
			switch (opcion) {
			case Menu.DAR_DE_ALTA:
				dardeAltaClientye();
				break;
			default:
				break;
			}
		} while (opcion!=Menu.SALIR);
    }

	private static void dardeAltaClientye() {
		// TODO Auto-generated method stub
		
	}

}
