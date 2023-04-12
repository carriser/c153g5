package ap.utn.edu.ar;

import java.util.ArrayList;
import java.util.List;

public class Ronda {
	
	private String nroRonda;
	private List<Partido> partidos;
	
	public Ronda(String nroRonda) {
		this.nroRonda = nroRonda;
		this.partidos = new ArrayList<Partido>();
	}

	public String getNroRonda() {
		return nroRonda;
	}

	public List<Partido> getPartidos() {
		return partidos;
	}

	public void agregarRonda(String ronda) {
		this.nroRonda = ronda;
	}
	
	public void agregarPartidos(Partido partido) {
		this.partidos.add(partido);
	}

}
