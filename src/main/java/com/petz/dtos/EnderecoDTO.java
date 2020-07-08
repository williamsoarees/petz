package com.petz.dtos;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class EnderecoDTO {

	@NotBlank(message = "Informe o cep")
	private String cep;
	
	@NotBlank(message = "Informe a cidade")
	private String cidade;
	
	@NotBlank(message = "Informe o bairro")
	private String bairro;
	
	@NotBlank(message = "Informe a rua")
	private String rua;
	
	@NotBlank(message = "Informe o numero")
	private String numero;
	
}
