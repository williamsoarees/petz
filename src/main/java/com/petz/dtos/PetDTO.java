package com.petz.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PetDTO {

	private Integer id;
	
	@NotEmpty(message = "Informe o nome")
	private String nome;
	
	@NotNull(message = "Informe a classe")
	private ClasseAnimal classe;
	
	@NotEmpty(message = "Informe o animal")
	private String animal;
	
	@NotEmpty(message = "Informe a raça")
	private String raca;
	
}
