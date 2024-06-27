package Dominio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Jugador {
	private SimpleIntegerProperty numero;
	private SimpleStringProperty nombre;
	private SimpleStringProperty posicion;

	public Jugador(int numero, String nombre, Posicion posicion) {
		this.numero = new SimpleIntegerProperty(numero);
		this.nombre = new SimpleStringProperty(nombre);
		this.posicion = new SimpleStringProperty(posicion.name());
	}

	public int getNumero() {
		return numero.get();
	}

	public void setNumero(int numero) {
		this.numero.set(numero);
	}

	public String getNombre() {
		return nombre.get();
	}

	public void setNombre(String nombre) {
		this.nombre.set(nombre);
	}

	public Posicion getPosicion() {
		return Posicion.valueOf(posicion.get());
	}

	public void setPosicion(Posicion posicion) {
		this.posicion.set(posicion.name());
	}

	public SimpleIntegerProperty numeroProperty() {
		return numero;
	}

	public StringProperty nombreProperty() {
		return nombre;
	}

	public StringProperty posicionProperty() {
		return posicion;
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
