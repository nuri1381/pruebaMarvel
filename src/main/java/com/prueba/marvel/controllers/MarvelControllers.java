package com.prueba.marvel.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.prueba.marvel.config.GenericModelMapper;
import com.prueba.marvel.modelsDTO.CharactersDTO;
import com.prueba.marvel.modelsDTO.ComicsDTO;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/marvel")
public class MarvelControllers {

	/**
	 * mapea las entidades
	 */
	@Autowired
	GenericModelMapper mapper;
	
	/**
	 * 
	 * @param caractersBody
	 * @return lista de characters mapeado CharactersDTO
	 */
	@PostMapping("/characters")
	public ResponseEntity<List<CharactersDTO>> convertCharacters(@RequestBody List<Object> caractersBody) {

		try {
			List<CharactersDTO> list = new ArrayList<CharactersDTO>();
			for (Object ob : caractersBody) {
				CharactersDTO ch = new CharactersDTO();
				ch = mapper.mapToCharactersDto(ob);
				list.add(ch);
			}
			// Page<? extends CharactersDTO> page = (Page<? extends CharactersDTO>) list;
			// HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,
			// "/api/characters");
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * 
	 * @param caractersBody
	 * @param tarea
	 * @return
	 */

	@PostMapping("/comics")
	public ResponseEntity<List<ComicsDTO>> convertComics(@RequestBody List<Object> caractersBody) {

		try {
			List<ComicsDTO> listComics = new ArrayList<ComicsDTO>();
			for (Object ob : caractersBody) {
				ComicsDTO comics = new ComicsDTO();
				comics = mapper.mapToComicsDto(ob);
				listComics.add(comics);

			}
			return new ResponseEntity<>(listComics, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

