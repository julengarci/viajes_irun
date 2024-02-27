package modelo;

import java.sql.Date;

public class Reserva {
	private int id;
	private Habitacion habitacion;
	private Hotel hotel;
	private Date desde;
	private Date hasta;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Habitacion getHabitacion() {
		return habitacion;
	}
	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public Date getDesde() {
		return desde;
	}
	public void setDesde(Date desde) {
		this.desde = desde;
	}
	public Date getHasta() {
		return hasta;
	}
	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}
	@Override
	public String toString() {
		return "Reserva [id=" + id + ", habitacion=" + habitacion + ", Hotel=" + hotel.getCif() + ", desde=" + desde
				+ ", hasta=" + hasta + "]";
	}
	
	
}
