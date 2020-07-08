package com.petz.execoes;

import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RespostaExcecao {

	private static final long serialVersionUID = 1L;

	private String codigo;
	private String mensagem;
	private List<String> detalhes;

	public RespostaExcecao(final CodigosDeErros codigosDeErros, String details) {
		this.codigo = codigosDeErros.name();
		this.mensagem = codigosDeErros.getMensagem();
		this.detalhes = Collections.singletonList(details);
	}

	public RespostaExcecao(CodigosDeErros codigosDeErros, List<String> details) {
		this.codigo = codigosDeErros.name();
		this.mensagem = codigosDeErros.getMensagem();
		this.detalhes = details;
	}
	
}
