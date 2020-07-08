package com.petz.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PetsDTO {

	private List<PetDTO> pets = new ArrayList<>();
	
	public void adicionarPet(PetDTO pet) {
		pets.add(pet);
	}
	
}
