package com.petz.execoes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CodigosDeErros {

	INTERNAL_SERVER_ERROR("Internal server error"),
	INVALID_REQUEST("Invalid request"),
	CLIENTE_NAO_EXISTE("Este cliente não existe!"),
	PET_NAO_EXISTE("Este pet não existe!");
	
	private final String mensagem;
	
}
