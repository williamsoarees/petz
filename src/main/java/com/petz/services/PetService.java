package com.petz.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petz.dtos.PetDTO;
import com.petz.dtos.PetsDTO;
import com.petz.entities.ClienteEntity;
import com.petz.entities.PetEntity;
import com.petz.execoes.CodigosDeErros;
import com.petz.execoes.PetExcecao;
import com.petz.repository.ClienteRepository;
import com.petz.repository.PetRepository;

@Service
public class PetService {

	@Autowired
	private PetRepository petRepository;
	
	@Autowired 
	private ClienteRepository clienteRepository;
	
	@Autowired 
	private ClienteService clienteService;
	
	@Autowired
	private ModelMapper mapper;
	
	public void criarPet(Integer clienteId, PetDTO petDTO) {
		
		ClienteEntity cliente = clienteService.buscarClientePorId(clienteId);
		
		cliente.getPets().add(mapper.map(petDTO, PetEntity.class));
		clienteRepository.save(cliente);
	}
	
	public PetsDTO buscarPet(Integer idClient, Integer idPet, String nome, String classe, String animal) {
		PetsDTO pets = new PetsDTO();
		Optional<PetEntity> pet;
		
		if (idPet != null) {
			pet = petRepository.findById(idPet);
			
			if (pet.isPresent()) {
				pets.adicionarPet(petEntityParaPetDTO(pet.get()));
			}
			
			return pets;
		}
		
		if (idClient != null) {
			Optional<ClienteEntity> clienteEntity = clienteRepository.findById(idClient);
			
			if (clienteEntity.isPresent()) {
				clienteEntity.get().getPets().forEach(petDoClient -> pets.adicionarPet(petEntityParaPetDTO(petDoClient)));
			}
			
			return pets;
		}
		
		if (nome != null) {
			List<PetEntity> petsEntity = petRepository.buscarPetEntityPeloNome(nome);
			
			if (!petsEntity.isEmpty()) {
				petsEntity.forEach(petEntity -> pets.adicionarPet(petEntityParaPetDTO(petEntity)));
				
				if (classe != null)
					pets.getPets().removeIf(p -> !p.getClasse().name().equalsIgnoreCase(classe));
				
				if (animal != null)
					pets.getPets().removeIf(p -> !p.getAnimal().equalsIgnoreCase(animal));
			}
			
			return pets;
		}
		
		return petIterableParaPetsDTO(petRepository.findAll());
	}
	
	public void alterarPet(Integer id, PetDTO petDTO) {
		PetEntity petEntity = buscarPetPorId(id);
		
		petRepository.save(PetEntity.builder().id(petEntity.getId()).nome(petDTO.getNome())
				.animal(petDTO.getAnimal()).classe(petDTO.getClasse()).raca(petDTO.getRaca()).build());
	}
	
	public void deletarAnimal(Integer id) {
		petRepository.deleteById(buscarPetPorId(id).getId());
	}
	
	private PetEntity buscarPetPorId(Integer id) {
		Optional<PetEntity> petEntity = petRepository.findById(id);
		
		if (!petEntity.isPresent()) {
			throw new PetExcecao(CodigosDeErros.PET_NAO_EXISTE.getMensagem());
		}
		
		return petEntity.get();
	}
	
	private PetsDTO petIterableParaPetsDTO(Iterable<PetEntity> petsEntity) {
		PetsDTO pets = new PetsDTO();
		petsEntity.forEach(petEntity -> pets.adicionarPet(petEntityParaPetDTO(petEntity)));
		return pets;
	}
	
	private PetDTO petEntityParaPetDTO(PetEntity petEntity) {
		return mapper.map(petEntity, PetDTO.class);
	}
	
}
