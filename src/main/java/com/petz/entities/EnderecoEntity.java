package com.petz.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "endereco")
public class EnderecoEntity {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name = "cep", nullable = false)
	private String cep;
	
	@Column(name = "cidade", nullable = false)
	private String cidade;
	
	@Column(name = "bairro", nullable = false)
	private String bairro;
	
	@Column(name = "rua", nullable = false)
	private String rua;
	
	@Column(name = "numero", nullable = false)
	private String numero;
	
}
