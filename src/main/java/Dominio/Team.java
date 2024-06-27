package Dominio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

	public void actualizarJugador(Jugador jugadorActualizado) {
		for (int i = 0; i < jugadores.size(); i++) {
			if (jugadores.get(i).getNumero() == jugadorActualizado.getNumero()) {
				jugadores.set(i, jugadorActualizado);
				break;
			}
		}
	}

	public static List<Team> cargarEquipos(String rutaArchivoEquipos, String rutaDirectorioJugadores) throws IOException {
		List<Team> equipos = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivoEquipos))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] datos = linea.split(";");
				String nombre = datos[1];
				int ranking = Integer.parseInt(datos[2]);
				String rutaBandera = datos[3];
				String rutaArchivoJugadores = rutaDirectorioJugadores + datos[0] + ".txt";
				List<Jugador> jugadores = Jugador.cargarJugadores(rutaArchivoJugadores);
				equipos.add(new Team(nombre, ranking, rutaBandera, jugadores));
			}
		}
		return equipos;
	}
}
