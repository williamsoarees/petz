package com.petz.handler;

import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.petz.execoes.ClienteExcecao;
import com.petz.execoes.CodigosDeErros;
import com.petz.execoes.PetExcecao;
import com.petz.execoes.RespostaExcecao;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	public ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		RespostaExcecao respostaExcecao = new RespostaExcecao(status.name(), status.getReasonPhrase(), Arrays.asList(ex.getLocalizedMessage()));

		return ResponseEntity.status(status).body(respostaExcecao);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

		RespostaExcecao respostaExcecao = new RespostaExcecao(CodigosDeErros.INTERNAL_SERVER_ERROR, ex.getMessage());

		request.getDescription(false);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respostaExcecao);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {

		RespostaExcecao respostaExcecao = new RespostaExcecao(CodigosDeErros.INVALID_REQUEST, ex.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respostaExcecao);

	}
	
	@ExceptionHandler(ClienteExcecao.class)
	public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(ClienteExcecao ex) {

		RespostaExcecao respostaExcecao = new RespostaExcecao(CodigosDeErros.CLIENTE_NAO_EXISTE, ex.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respostaExcecao);

	}
	
	@ExceptionHandler(PetExcecao.class)
	public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(PetExcecao ex) {

		RespostaExcecao respostaExcecao = new RespostaExcecao(CodigosDeErros.PET_NAO_EXISTE, ex.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respostaExcecao);

	}
	
}
