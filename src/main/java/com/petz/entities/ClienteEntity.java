package com.petz.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.Data;

@Data
@Entity
@Table(name = "cliente")
public class ClienteEntity {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "nascimento", nullable = false)
	private Date nascimento;
	
	@Column(name = "celular", nullable = false)
	private String celular;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@OneToOne
	@Cascade(CascadeType.ALL)
	private EnderecoEntity endereco;
	
	@OneToMany
	@Cascade(CascadeType.ALL)
	private List<PetEntity> pets;
	
}
