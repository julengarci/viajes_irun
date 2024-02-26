package modelo;

public class Habitacion {
	private int id;
	private int numero;
	private String descripcion;
	private int precio;
	private Hotel hotel;
	Reserva reserva;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public Reserva getReserva() {
		return reserva;
	}
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	@Override
	public String toString() {
		return "Habitacion [id=" + id + ", numero=" + numero + ", descripcion=" + descripcion + ", precio=" + precio
				+ ", hotel=" + hotel + ", reserva=" + reserva + "]";
	}
	
}
