package Dominio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Jugador {
	private int numero;
	private String nombre;
	private Posicion posicion;

	public Jugador(int numero, String nombre, Posicion posicion) {
		this.numero = numero;
		this.nombre = nombre;
		this.posicion = posicion;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Posicion getPosicion() {
		return posicion;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public static List<Jugador> cargarJugadores(String rutaArchivo) throws IOException {
		List<Jugador> jugadores = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] datos = linea.split(";");
				int numero = Integer.parseInt(datos[0]);
				String nombre = datos[1];
				Posicion posicion = Posicion.valueOf(datos[2]);
				jugadores.add(new Jugador(numero, nombre, posicion));
			}
		}
		return jugadores;
	}
}
