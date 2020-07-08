package com.petz.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petz.dtos.AlterarClienteDTO;
import com.petz.dtos.ClienteDTO;
import com.petz.dtos.ClientesDTO;
import com.petz.entities.ClienteEntity;
import com.petz.execoes.ClienteExcecao;
import com.petz.execoes.CodigosDeErros;
import com.petz.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	public void criarCliente(ClienteDTO cliente) {
		clienteRepository.save(mapper.map(cliente, ClienteEntity.class));
	}
	
	public ClienteDTO buscarCliente(Integer id) {
		return clienteEntityParaClienteDTO(buscarClientePorId(id));
	}
	
	public ClientesDTO buscarClientes() {
		return clientesIterableParaClientesDTO(clienteRepository.findAll());
	}
	
	public void alterarCliente(Integer id, AlterarClienteDTO alterarClienteDTO) {
		ClienteEntity clienteEntity = buscarClientePorId(id);
		
		inserirAlterarClienteDTOEmClienteEntity(alterarClienteDTO, clienteEntity);
		
		clienteRepository.save(clienteEntity);
	}
	
	public void deletarCliente(Integer id) {
		clienteRepository.deleteById(buscarClientePorId(id).getId());
	}
	
	private ClientesDTO clientesIterableParaClientesDTO(Iterable<ClienteEntity> clientes) {
		ClientesDTO retorno = new ClientesDTO();
		clientes.forEach(cliente -> retorno.addCliente(clienteEntityParaClienteDTO(cliente)));
		return retorno;
	}
	
	private ClienteDTO clienteEntityParaClienteDTO(ClienteEntity clienteEntity) {
		return mapper.map(clienteEntity, ClienteDTO.class);
	}
	
	protected ClienteEntity buscarClientePorId(Integer id) {
		Optional<ClienteEntity> clienteEntity = clienteRepository.findById(id);
		
		if (!clienteEntity.isPresent()) {
			throw new ClienteExcecao(CodigosDeErros.CLIENTE_NAO_EXISTE.getMensagem());
		}
		
		return clienteEntity.get();
	}
	
	private void inserirAlterarClienteDTOEmClienteEntity(AlterarClienteDTO alterarClienteDTO, ClienteEntity clienteEntity) {
		clienteEntity.setCelular(alterarClienteDTO.getCelular());
		clienteEntity.setEmail(alterarClienteDTO.getEmail());
		clienteEntity.getEndereco().setBairro(alterarClienteDTO.getEndereco().getBairro());
		clienteEntity.getEndereco().setCep(alterarClienteDTO.getEndereco().getCep());
		clienteEntity.getEndereco().setCidade(alterarClienteDTO.getEndereco().getCidade());
		clienteEntity.getEndereco().setNumero(alterarClienteDTO.getEndereco().getNumero());
		clienteEntity.getEndereco().setRua(alterarClienteDTO.getEndereco().getRua());
		clienteEntity.setNascimento(alterarClienteDTO.getNascimento());
		clienteEntity.setNome(alterarClienteDTO.getNome());
	}
	
}
