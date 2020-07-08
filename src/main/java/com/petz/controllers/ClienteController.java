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
import org.springframework.web.bind.annotation.RestController;

import com.petz.dtos.ClienteDTO;
import com.petz.dtos.ClientesDTO;
import com.petz.dtos.AlterarClienteDTO;
import com.petz.services.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("petz")
@Api(value = "Cliente do petz", tags = {"Cliente"})
public class ClienteController {

	@Autowired
	private ClienteService service;
	
	@ApiOperation(value = "Cria um novo cliente")
	@PostMapping(path = "/v1/cliente")
	public ResponseEntity<Void> criarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
		service.criarCliente(clienteDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Busca um cliente por id")
	@GetMapping(path = "/v1/cliente/{id}")
	public ResponseEntity<ClienteDTO> buscarCliente(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(service.buscarCliente(id));
	}
	
	@ApiOperation(value = "Busca todos os clientes cadastrados")
	@GetMapping(path = "/v1/clientes")
	public ResponseEntity<ClientesDTO> buscarClientes() {
		return ResponseEntity.ok(service.buscarClientes());
	}
	
	@ApiOperation(value = "Altera um cliente por id")
	@PutMapping(path = "/v1/cliente/{id}")
	public ResponseEntity<Void> alterarCliente(@PathVariable("id") Integer id, @Valid @RequestBody AlterarClienteDTO alterarClienteDTO) {
		service.alterarCliente(id, alterarClienteDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Deleta um cliente por id")
	@DeleteMapping(path = "/v1/cliente/{id}")
	public ResponseEntity<Void> deletarCliente(@PathVariable("id") Integer id) {
		service.deletarCliente(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
