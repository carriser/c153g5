package ap.utn.edu.ar;

import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		// propuesta del tutor
		
		// leer los partidos, para extraer los datos para guardarlos
		Collection<Partido> partidos = new ArrayList<Partido>();
		
		// leer archivo resultados.csv en modo test
		//Path archResultados = Paths.get("E:\\Proyectos Java\\Curso\\tp-pronosticos\\src\\test\\resources\\resultados_test1.csv");
		
		// el enunciado del tp pide que los archivos sean pasados como parámetro
		Path archResultados = Paths.get(args[0]);
		List<String> lineasArchResultados = null;
		
		try {
			lineasArchResultados = Files.readAllLines(archResultados);
		} catch (IOException e) {
			System.err.println("Falló la apertura del archivo");
			System.exit(1);
		}
		
		// para saltear la primer línea del archivo creamos la variable primera
		boolean primera = true;
		
		for(String linea : lineasArchResultados) {
			if(primera) {
				primera = false; 
			} else {
				System.out.println(linea);
				// primer línea entrega 1: Argentina,1,2,Arabia Saudita
				// primer línea entrega 2: 1,Argentina,1,2,Arabia Saudita
				// cuando leo las lineas creamos el String[] con cada campo de cada línea
				String[] campos = linea.split(",");
				// al leer la línea, algunos datos los utilizo para instanciar objetos
				
				/* Entrega 1:
				Equipo equipo1 = new Equipo(campos[0]);
				Equipo equipo2 = new Equipo(campos[3]);
				Partido partido = new Partido(equipo1,equipo2);
				partido.setGolesEquipo1(Integer.parseInt(campos[1]));
				partido.setGolesEquipo2(Integer.parseInt(campos[2]));*/
				
				// Entrega 2:
				Ronda nroRonda = new Ronda(campos[0]);
				Equipo equipo1 = new Equipo(campos[1]);
				Equipo equipo2 = new Equipo(campos[4]);
				Partido partido = new Partido(nroRonda,equipo1,equipo2);
				partido.setGolesEquipo1(Integer.parseInt(campos[2]));
				partido.setGolesEquipo2(Integer.parseInt(campos[3]));
				// una vez leído el partido se guarda en una colección
				partidos.add(partido);
			}
		}
		
		// leer archivo pronostico.csv en modo test
		//Path archPronostico = Paths.get("E:\\Proyectos Java\\Curso\\tp-pronosticos\\src\\test\\resources\\pronostico_test1.csv");
		
		// el enunciado del tp pide que los archivos sean pasados como parámetro
		Path archPronostico = Paths.get(args[1]);
		List<String> lineasArchPronostico = null;
		
		try {
			lineasArchPronostico = Files.readAllLines(archPronostico);
		} catch (IOException e) {
			System.err.println("Falló la apertura del archivo");
			System.exit(1);
		}
		
		System.out.println("-----------");
		
		// cada vez que se procese un pronostico sumamos los puntos obtenidos en la variable puntos
		int puntos = 0;
		
		// procesamos el archivo
		// para saltear la primer línea del archivo utilizamos la variable primera ya creada
		primera = true;
		for(String linea : lineasArchPronostico) {
			if(primera) {
				primera = false; 
			} else {
				// para chequear la lectura del archivo lo imprimimos por consola
				System.out.println(linea);
				// primer línea entrega 1: Argentina,X,,,Arabia Saudita
				// primer línea entrega 2: Mariana,1,Argentina,X,,,Arabia Saudita
				// cuando leo las lineas creamos el String[] con cada campo de cada línea
				String[] campos = linea.split(",");
				// al leer la línea, algunos datos los utilizo para instanciar objetos
				
				/* Entrega 1:
				Equipo equipo1 = new Equipo(campos[0]);
				Equipo equipo2 = new Equipo(campos[4]);*/
				
				// Entrega 2:
				Persona persona1 = new Persona(campos[0]);
				Ronda nroRonda = new Ronda(campos[1]); 
				Equipo equipo1 = new Equipo(campos[2]);
				Equipo equipo2 = new Equipo(campos[6]);
				// ahora necesitamos un partido, los equipos y el resultado para construir un pronóstico
				Partido partido = null;
				
				// para saber si el partido con los resultados coincide con el partido del pronóstico
				// recorremos la colección partidos y lo comparamos con el de pronóstico
				
				/* Entrega 1:
				for(Partido partidoCol : partidos) {
					if(partidoCol.getEquipo1().getNombre().equals(equipo1.getNombre()) &&
						partidoCol.getEquipo2().getNombre().equals(equipo2.getNombre())){
						// cuando coincide el partido de la colección con el partido del pronóstico hago
						partido = partidoCol;
					}
				}*/
				
				// Entrega 2:
				for(Partido partidoCol : partidos) {
					if( //nroRonda.getNroRonda().equals(partidoCol.getNroRonda().getPartidos()) &&  //linea sugerida por eclipse
						partidoCol.getEquipo1().getNombre().equals(equipo1.getNombre()) &&
						partidoCol.getEquipo2().getNombre().equals(equipo2.getNombre())){
						// cuando coincide el partido de la colección con el partido del pronóstico hago
						partido = partidoCol;
					}
				}
				
				// creamos equipo y resultado
				Equipo equipo = null;
				EnumResultados resultado = null;
				
				// preguntamos si la apuesta coincide con el resultado del partido
				if("X".equals(campos[2])) {
					equipo = equipo1;
					resultado = EnumResultados.GANADOR;
				}
				
				if("X".equals(campos[3])) {
					equipo = equipo1;
					resultado = EnumResultados.EMPATE;
				}
				
				if("X".equals(campos[4])) {
					equipo = equipo1;
					resultado = EnumResultados.PERDEDOR;
				}
				
				Pronostico pronostico = new Pronostico(partido, equipo, resultado);
				
				// al pasarle los parámetros a pronostico utilizamos el método puntos() para sumar puntos
				puntos += pronostico.puntos();
				
				// Entrega 2: una vez que verifica aciertos o no, genero un archivo con el
				// puntaje obtenido por cada apostador
				exportarCSV(persona1, puntos);
				
			}
		}
		
		System.out.println("-----------");
		
		// finalmente mostramos los puntos
		System.out.println("Los puntos obtenidos por la apuesta del usuario es: " + puntos);
	}
	
	public static void exportarCSV(Persona persona1, int puntos) {
		
		String archPuntaje = "E:\\Proyectos Java\\Curso\\tp-pronosticos\\src\\test\\resources\\puntajes_test1.csv";
		
		if(!Files.exists(Paths.get(archPuntaje))) {
			try {
				Files.createFile(Paths.get(archPuntaje));
			} catch (IOException e) {
				System.err.println("Falló la creación del archivo");
				System.exit(1);
			}
		} else {
			try {
				Files.writeString(Paths.get(archPuntaje), "hola Susana", StandardOpenOption.APPEND);
				
			} catch (IOException e) {
				System.err.println("No se tuvo acceso al archivo");
				System.exit(1);
			}
		}
		
	}

}
