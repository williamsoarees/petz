package com.petz.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.petz.dtos.ClasseAnimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pet")
public class PetEntity {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "classe", nullable = false)
	private ClasseAnimal classe;
	
	@Column(name = "animal", nullable = false)
	private String animal;
	
	@Column(name = "raca", nullable = false)
	private String raca;
	
}
