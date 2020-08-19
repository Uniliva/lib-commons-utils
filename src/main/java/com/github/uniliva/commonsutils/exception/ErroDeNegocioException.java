package com.github.uniliva.commonsutils.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErroDeNegocioException extends BaseException {
	private static final long serialVersionUID = 1L;

	public ErroDeNegocioException(final String mensagem, final Object detalhe) {
		super(mensagem, detalhe);
	}

	public ErroDeNegocioException(final String message) {
		super(message);
	}

}
