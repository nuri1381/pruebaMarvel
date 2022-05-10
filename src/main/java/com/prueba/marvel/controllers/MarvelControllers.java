package com.prueba.marvel.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.marvel.config.GenericModelMapper;
import com.prueba.marvel.modelsDTO.CharactersDTO;
import com.prueba.marvel.modelsDTO.ComicsDTO;
import com.prueba.marvel.services.MarvelService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/marvel")
public class MarvelControllers {

	private static final String PUBLIC_KEY_MARVEL = "809fa665718b6a0b765e9c4589701d56";
	private static final String HASH = "2e01940c39ceaa249200c98d8ba1a9fd";
	private static final String URL_API_COMICS = "https://gateway.marvel.com:443/v1/public/comics?ts=1&apikey="
			+ PUBLIC_KEY_MARVEL + "&hash=" + HASH;
	private static final String URL_API_CHARACTERS = "https://gateway.marvel.com:443/v1/public/characters?ts=1&apikey="
			+ PUBLIC_KEY_MARVEL + "&hash=" + HASH;

	/**
	 * mapea las entidades
	 */
	@Autowired
	static GenericModelMapper mapper;

	MarvelService marvelService;
	public static final ObjectMapper JSON_MAPPER = new ObjectMapper();

	/**
	 * 
	 * 
	 * @return lista de characters mapeado CharactersDTO
	 */
	@PostMapping("/characters")
	public ResponseEntity<List<CharactersDTO>> convertCharacters()
			throws IOException {

		try {
			List<CharactersDTO> list = new ArrayList<CharactersDTO>();
			String str = MarvelService.peticionHttpGetCharacters();
			JSONObject jsonObject = new JSONObject(str);
			JSONObject data = jsonObject.getJSONObject("data");
			org.json.JSONArray the_json_array = data.getJSONArray("results");

			// parseamos a CharactersDTO
			for (int i = 0; i < the_json_array.length() - 1; i++) {
				CharactersDTO ch = new CharactersDTO();
				JSONObject gg = new JSONObject(the_json_array.get(i).toString());
				ch.setName(gg.get("name").toString());
				String thumbnailArray = gg.get("thumbnail").toString();
				JSONObject thumbnail = new JSONObject(thumbnailArray);
				Object path = thumbnail.get("path");
				Object extension = thumbnail.get("extension");
				ch.setThumbnailExtension(extension.toString());
				ch.setThumbnailPath(path.toString());
				ch.setDescription(gg.get("description").toString());
				list.add(ch);

			}

			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	/**
	 * 
	 * 
	 * @return listado de Comits mapeado
	 */

	@PostMapping("/comics")
	public ResponseEntity<List<ComicsDTO>> convertComics() {

		try {
			List<ComicsDTO> list = new ArrayList<ComicsDTO>();
			String str = MarvelService.peticionHttpGetComics();
			JSONObject jsonObject = new JSONObject(str);
			JSONObject data = jsonObject.getJSONObject("data");
			org.json.JSONArray the_json_array = data.getJSONArray("results");

			// parseamos a ComicsDTO
			for (int i = 0; i < the_json_array.length() - 1; i++) {
				ComicsDTO ch = new ComicsDTO();
				JSONObject gg = new JSONObject(the_json_array.get(i).toString());
				ch.setTitle(gg.get("title").toString());
				String thumbnailArray = gg.get("thumbnail").toString();
				JSONObject thumbnail = new JSONObject(thumbnailArray);
				Object path = thumbnail.get("path");
				Object extension = thumbnail.get("extension");
				ch.setThumbnailExtension(extension.toString());
				ch.setThumbnailPath(path.toString());
				ch.setDescription(gg.get("description").toString());
				list.add(ch);

			
			}
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
