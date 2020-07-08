package com.petz.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ClientesDTO {

	private List<ClienteDTO> clientes = new ArrayList<>();
	
	public void addCliente(ClienteDTO cliente) {
		clientes.add(cliente);
	}
	
}
