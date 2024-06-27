package Dominio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Team {
	private String nombre;
	private int ranking;
	private String rutaBandera;
	private List<Jugador> jugadores;

	public Team(String nombre, int ranking, String rutaBandera, List<Jugador> jugadores) {
		this.nombre = nombre;
		this.ranking = ranking;
		this.rutaBandera = rutaBandera;
		this.jugadores = jugadores;
	}

	public String getNombre() {
		return nombre;
	}

	public int getRanking() {
		return ranking;
	}

	public String getRutaBandera() {
		return rutaBandera;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void actualizarJugador(int numero, String nuevoNombre, Posicion nuevaPosicion) {
		Jugador jugador = buscarJugador(numero);
		if (jugador != null) {
			jugador.setNombre(nuevoNombre);
			jugador.setPosicion(nuevaPosicion);
		}
	}

	public void eliminarJugador(int numero) {
		jugadores.removeIf(jugador -> jugador.getNumero() == numero);
	}

	public Jugador buscarJugador(int numero) {
		for (Jugador jugador : jugadores) {
			if (jugador.getNumero() == numero) {
				return jugador;
			}
		}
		return null;
	}

	public static List<Team> cargarEquipos(String rutaArchivoEquipos, String rutaDirectorioJugadores) throws IOException {
		List<Team> equipos = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivoEquipos))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] datos = linea.split(";");
				String id = datos[0].toLowerCase();
				String nombre = datos[1];
				int ranking = Integer.parseInt(datos[2]);
				String rutaBandera = datos[3];
				String rutaArchivoJugadores = rutaDirectorioJugadores + id + ".txt";

				// Verificar si el archivo de jugadores existe
				if (!Files.exists(Paths.get(rutaArchivoJugadores))) {
					System.err.println("El archivo no existe: " + rutaArchivoJugadores);
					continue;
				}

				System.out.println("Cargando jugadores para el equipo " + nombre + " desde: " + rutaArchivoJugadores); // Depuración
				List<Jugador> jugadores = Jugador.cargarJugadores(rutaArchivoJugadores);
				equipos.add(new Team(nombre, ranking, rutaBandera, jugadores));
			}
		}
		return equipos;
	}
}

