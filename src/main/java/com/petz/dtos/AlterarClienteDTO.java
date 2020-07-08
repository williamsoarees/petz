package com.petz.dtos;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AlterarClienteDTO {

	@NotBlank(message = "Informe o nome")
	private String nome;
	
	@NotNull(message = "Informe o nascimento")
	private Date nascimento;
	
	@NotBlank(message = "Informe o numero de celular")
	private String celular;
	
	@Email(message = "Este e-mail não é válido")
	@NotBlank(message = "Informe o email")
	private String email;
	
	@NotNull
	@Valid
	private EnderecoDTO endereco;
	
}
