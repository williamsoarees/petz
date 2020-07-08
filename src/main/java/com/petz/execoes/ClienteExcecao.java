package com.petz.execoes;

public class ClienteExcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ClienteExcecao(String msg) {
		super(msg);
	}
	
}
