package com.petz.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petz.dtos.PetDTO;
import com.petz.dtos.PetsDTO;
import com.petz.services.PetService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("petz")
@Api(value = "Cliente do petz", tags = {"Pet"})
public class PetController {

	@Autowired
	private PetService petService;
	
	@ApiOperation(value = "Adicionar um pet a um cliente")
	@PostMapping(path = "/v1/pet/cliente/{id}")
	public ResponseEntity<Void> criarPet(@PathVariable("id") Integer clienteId, @Valid @RequestBody PetDTO petDTO) {
		petService.criarPet(clienteId, petDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Busca um ou vários pets")
	@GetMapping(path = "/v1/pet")
	public ResponseEntity<PetsDTO> buscarPet(@RequestParam(value = "idCliente", required = false) Integer idClient,
			@RequestParam(value = "idPet", required = false) Integer idPet, @RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "classe", required = false) String classe, @RequestParam(value = "animal", required = false) String animal) {
		return ResponseEntity.ok(petService.buscarPet(idClient, idPet, nome, classe, animal));
	}
	
	@ApiOperation(value = "Altera o pet de um cliente")
	@PutMapping(path = "/v1/pet/{id}")
	public ResponseEntity<Void> alterarPet(@PathVariable("id") Integer id, @Valid @RequestBody PetDTO petDTO) {
		petService.alterarPet(id, petDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Deleta um pet de um cliente")
	@DeleteMapping(path = "/v1/pet/{id}")
	public ResponseEntity<Void> deletarAnimal(@PathVariable("id") Integer id){
		petService.deletarAnimal(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
