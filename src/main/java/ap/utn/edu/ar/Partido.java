package ap.utn.edu.ar;

public class Partido {
 
	private Ronda nroRonda;
	private Equipo equipo1;
	private Equipo equipo2;
	private int golesEquipo1;
	private int golesEquipo2;
	
	public Partido(Ronda nroRonda, Equipo equipo1, Equipo equipo2) {
		this.nroRonda = nroRonda;
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
	}

	public Partido(Ronda rondaNro, Equipo equipo1, Equipo equipo2, int golesEquipo1, int golesEquipo2) {
		super();
		this.nroRonda = nroRonda;
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
		this.golesEquipo1 = golesEquipo1;
		this.golesEquipo2 = golesEquipo2;
	}

	public Ronda getNroRonda() {
		return nroRonda;
	}

	public int getGolesEquipo1() {
		return golesEquipo1;
	}

	public void setGolesEquipo1(int golesEquipo1) {
		this.golesEquipo1 = golesEquipo1;
	}

	public int getGolesEquipo2() {
		return golesEquipo2;
	}

	public void setGolesEquipo2(int golesEquipo2) {
		this.golesEquipo2 = golesEquipo2;
	}

	public Equipo getEquipo1() {
		return equipo1;
	}

	public Equipo getEquipo2() {
		return equipo2;
	}
	
	public String resultado(int goles1, int goles2) {  // el método que yo hice
		String quienGano = " ";
		if(golesEquipo1 > golesEquipo2) {
			quienGano = "equipo1";
		} else {
			if(golesEquipo1 < golesEquipo2) {
				quienGano = "equipo2";
			} else {
				quienGano = "empate";
			}
		}
		return quienGano;
	}
	
	public EnumResultados resultado2(Equipo equipo) { // método propuesto por el tutor 
		if(golesEquipo1 == golesEquipo2) {
			return EnumResultados.EMPATE;
		}
		if(equipo.getNombre().equals(equipo1.getNombre())) {
			if(golesEquipo1 > golesEquipo2) {
				return EnumResultados.GANADOR;
			} else {
				return EnumResultados.PERDEDOR;
			}
		} else {
			if(golesEquipo2 > golesEquipo1) {
				return EnumResultados.GANADOR;
			} else {
				return EnumResultados.PERDEDOR;
			}
		}
	}

}
