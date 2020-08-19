package com.github.uniliva.commonsutils.exception;

import com.github.uniliva.commonsutils.dto.Erro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final Erro erro;

	public BaseException() {
		erro = new Erro(null, null);
	}

	public BaseException(final String mensagem, final Throwable causa) {
		super(mensagem, causa);
		erro = new Erro(mensagem, causa.getStackTrace());
	}

	public BaseException(final String mensagem) {
		this(mensagem, mensagem);
	}

	public BaseException(final String mensagem, final Object detalhe) {
		super(mensagem);
		erro = new Erro(mensagem, detalhe);
	}

}
