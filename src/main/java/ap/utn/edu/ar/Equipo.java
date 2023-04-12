package ap.utn.edu.ar;

public class Equipo {
	private String nombre;
	private String descripcion;
	
	public Equipo(String nombre) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

}
