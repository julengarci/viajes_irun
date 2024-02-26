package modelo;

public class Hotel {
	private int id;
	private String cif;
	private String nombre;
	private String gerente;
	private int entrella;
	private String compania;
	Habitacion habitacion;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getGerente() {
		return gerente;
	}
	public void setGerente(String gerente) {
		this.gerente = gerente;
	}
	public int getEntrella() {
		return entrella;
	}
	public void setEntrella(int entrella) {
		this.entrella = entrella;
	}
	public String getCompania() {
		return compania;
	}
	public void setCompania(String compania) {
		this.compania = compania;
	}
	public Habitacion getHabitacion() {
		return habitacion;
	}
	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}
	@Override
	public String toString() {
		return "Hotel [id=" + id + ", cif=" + cif + ", nombre=" + nombre + ", gerente=" + gerente + ", entrella="
				+ entrella + ", compania=" + compania + ", habitacion=" + habitacion + "]";
	}
	
}
