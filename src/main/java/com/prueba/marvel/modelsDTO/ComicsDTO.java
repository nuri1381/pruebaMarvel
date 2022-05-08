package com.prueba.marvel.modelsDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder 
@AllArgsConstructor
public class ComicsDTO {
	
	String title;
	String description;
	String thumbnailExtension;
	String thumbnailPath;
	
	public ComicsDTO() {}
}

