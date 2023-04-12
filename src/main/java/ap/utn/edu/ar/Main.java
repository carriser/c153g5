package ap.utn.edu.ar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		
		// propuesta del tutor
		
		// leer los partidos, para extraer los datos para guardarlos
		Collection<Partido> partidos = new ArrayList<Partido>();
		
		// leer archivo resultados.csv en modo test
		// Path archResultados = Paths.get("E:\\Proyectos Java\\Curso\\tp-pronosticos\\src\\test\\resources\\resultados_test1.csv");
		
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
				// primer línea: Argentina,1,2,Arabia Saudita
				// cuando leo las lineas creamos el String[] con cada campo de cada línea
				String[] campos = linea.split(",");
				// al leer la línea, algunos datos los utilizo para instanciar objetos
				Equipo equipo1 = new Equipo(campos[0]);
				Equipo equipo2 = new Equipo(campos[3]);
				Partido partido = new Partido(equipo1,equipo2);
				partido.setGolesEquipo1(Integer.parseInt(campos[1]));
				partido.setGolesEquipo2(Integer.parseInt(campos[2]));
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
				// primer línea: Argentina,X,,,Arabia Saudita
				// cuando leo las lineas creamos el String[] con cada campo de cada línea
				String[] campos = linea.split(",");
				// al leer la línea, algunos datos los utilizo para instanciar objetos
				Equipo equipo1 = new Equipo(campos[0]);
				Equipo equipo2 = new Equipo(campos[4]);
				
				// ahora necesitamos un partido, los equipos y el resultado para construir un pronóstico
				Partido partido = null;
				
				// para saber si el partido con los resultados coincide con el partido del pronóstico
				// recorremos la colección partidos y lo comparamos con el de pronóstico
				for(Partido partidoCol : partidos) {
					if(partidoCol.getEquipo1().getNombre().equals(equipo1.getNombre()) &&
						partidoCol.getEquipo2().getNombre().equals(equipo2.getNombre())){
						// cuando coincide el partido de la colección con el partido del pronóstico hago
						partido = partidoCol;
					}
				}
				// creamos equipo y resultado
				Equipo equipo = null;
				EnumResultados resultado = null;
				
				// preguntamos si la apuesta coincide con el resultado del partido
				if("X".equals(campos[1])) {
					equipo = equipo1;
					resultado = EnumResultados.GANADOR;
				}
				
				if("X".equals(campos[2])) {
					equipo = equipo1;
					resultado = EnumResultados.EMPATE;
				}
				
				if("X".equals(campos[3])) {
					equipo = equipo1;
					resultado = EnumResultados.PERDEDOR;
				}
				
				Pronostico pronostico = new Pronostico(partido, equipo, resultado );
				
				// al pasarle los parámetros a pronostico utilizamos el método puntos()
				// para sumar puntos
				puntos += pronostico.puntos();
			}
		}
		
		System.out.println("-----------");
		
		// finalmente mostramos los puntos
		System.out.println("Los puntos obtenidos por la apuesta del usuario es: " + puntos);
		
		/* hecho por mí
		Equipo equipo1 = new Equipo("Argentina");
		Equipo equipo2 = new Equipo("Arabia Saudita");
		Equipo equipo3 = new Equipo("Polonia");
		Equipo equipo4 = new Equipo("Mexico");		
		
		Map<String, Equipo> equipos = new HashMap<String, Equipo>();
		equipos.put("Argentina", equipo1);
		equipos.put("Arabia Saudita", equipo2);
		equipos.put("Polonia", equipo3);
		equipos.put("Mexico", equipo4);

		Path archResultados = Paths.get(args[0]); // paso la ruta del archivo como argumento del Main
		Path archPronostico = Paths.get(args[1]);
				
		try {
			List<String> lineasArchResultados = Files.readAllLines(archResultados);
			boolean primero = true;
			for(String lineaResultados : lineasArchResultados) {
				if(primero) {
					primero = false;
				} else {
					if(!lineaResultados.isBlank()) {		//todo lo incluido en este if el IDE nos ayuda a transformarlo en una función
						procesarLinea(equipos, lineaResultados);
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Falló la apertura del archivo");
			System.exit(1);
		}*/


	}

	/* hecho por mí
	private static void procesarLinea(Map<String, Equipo> equipos, String lineaResultados) {
		
		System.out.println(lineaResultados);
		String[] split = lineaResultados.split(",");	
		String equipo1 = split[0];
		int cantGoles1 = Integer.parseInt(split[1]);
		int cantGoles2 = Integer.parseInt(split[2]);
		String equipo2 = split[3];
		
	}*/

}
