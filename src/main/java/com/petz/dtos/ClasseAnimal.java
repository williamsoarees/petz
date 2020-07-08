package com.petz.dtos;

import lombok.Getter;

@Getter
public enum ClasseAnimal {
	
	MAMIFERO("Memifero"),
	AVES("Aves"),
	REPTEIS("Repteis"),
	ANFIBIOS("Anfibios"),
	PEIXES("Peixes"),
	INVERTEBRADOS("Invertebrados");
	
	private String classe;
	
	private ClasseAnimal(String classe) {
		this.classe = classe;
	}
	
	public static ClasseAnimal parse(String classe) {
		for (ClasseAnimal classeAnimal : ClasseAnimal.values()) {
			if (classe.equals(classeAnimal.getClasse())) {
				return classeAnimal;
			}
		}
		
		return null;
	}
	
}
