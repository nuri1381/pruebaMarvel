package com.prueba.marvel.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.prueba.marvel.modelsDTO.CharactersDTO;
import com.prueba.marvel.modelsDTO.ComicsDTO;

@Configuration
public class GenericModelMapper {private final ModelMapper mapper = new ModelMapper();

private static GenericModelMapper instance;

public static GenericModelMapper singleInstance() {
	if (instance == null) {
		instance = new GenericModelMapper();
	}
	return instance;
}

public CharactersDTO mapToCharactersDto(Object object) {
	return mapper.map(object, CharactersDTO.class);
}

public ComicsDTO mapToComicsDto(Object object) {
	return mapper.map(object, ComicsDTO.class);
}

@Bean
public ModelMapper modelMapper() {
	return new ModelMapper();
}

/**
 * Configuración más ajustada.
 */
/*
 * @Bean public WebMvcConfigurer corsConfigurer() { return new
 * WebMvcConfigurer() {
 * 
 * @Override public void addCorsMappings(CorsRegistry registry) {
 * registry.addMapping("/producto/**") .allowedOrigins("http://localhost:9001")
 * .allowedMethods("GET", "POST", "PUT", "DELETE") .maxAge(3600); }
 * 
 * }; }
 */
}
