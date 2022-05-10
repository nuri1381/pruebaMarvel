package com.prueba.marvel.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.stereotype.Service;

@Service
public class MarvelService {

	private static String PUBLIC_KEY_MARVEL = "809fa665718b6a0b765e9c4589701d56";
	private static String HASH = "2e01940c39ceaa249200c98d8ba1a9fd";
	private static String URL_API_COMICS = "https://gateway.marvel.com:443/v1/public/comics?ts=1&apikey="
			+ PUBLIC_KEY_MARVEL + "&hash=" + HASH;
	private static String URL_API_CHARACTERS = "https://gateway.marvel.com:443/v1/public/characters?ts=1&apikey="
			+ PUBLIC_KEY_MARVEL + "&hash=" + HASH;

	public static String peticionHttpGetCharacters() throws Exception {
		// Esto es lo que vamos a devolver
		StringBuilder resultado = new StringBuilder();
		// Crear un objeto de tipo URL
		URL url = new URL(URL_API_CHARACTERS);
		// Abrir la conexión e indicar que será de tipo GET
		HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		conexion.setRequestMethod("GET");
		// Búferes para leer
		BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
		String linea;
		// Mientras el BufferedReader se pueda leer, agregar contenido a resultado
		while ((linea = rd.readLine()) != null) {
			resultado.append(linea);
		}
		// Cerrar el BufferedReader
		rd.close();
		// Regresar resultado, pero como cadena, no como StringBuilder

		return resultado.toString();
	}

	public static String peticionHttpGetComics() throws Exception {
		// Esto es lo que vamos a devolver
		StringBuilder resultado = new StringBuilder();
		// Crear un objeto de tipo URL
		URL url = new URL(URL_API_COMICS);

		// Abrir la conexión e indicar que será de tipo GET
		HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		conexion.setRequestMethod("GET");
		// Búferes para leer
		BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
		String linea;
		// Mientras el BufferedReader se pueda leer, agregar contenido a resultado
		while ((linea = rd.readLine()) != null) {
			resultado.append(linea);
		}
		// Cerrar el BufferedReader
		rd.close();
		// Regresar resultado, pero como cadena, no como StringBuilder
		return resultado.toString();
	}

}
