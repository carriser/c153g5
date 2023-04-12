package ap.utn.edu.ar;

public class Pronostico {

	private Partido partido;
	private Equipo equipo;
	private EnumResultados resultadoApuesta;
	private int puntos;
	
	
	public Pronostico(Partido partido, Equipo equipo, EnumResultados resultadoApuesta) {
		super();
		this.partido = partido;
		this.equipo = equipo;
		this.resultadoApuesta = resultadoApuesta;
		this.puntos = puntos;
	}

	public Partido getPartido() {
		return partido;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public EnumResultados getEnumResultados() {
		return resultadoApuesta;
	}

	public int getPuntaje() {
		return puntos;
	}
	
	// método que suma puntos dependiendo del resultado que me trae de Partido
	public int puntos() {
		EnumResultados resultadoPartido = partido.resultado2(equipo);  // variable con el resultado del partido
		if(resultadoApuesta.equals(resultadoPartido)) {		   // pregunto si la apuesta coincide con resultado partido
			return 1;
		} else {
			return 0;	
		}
	}
}
