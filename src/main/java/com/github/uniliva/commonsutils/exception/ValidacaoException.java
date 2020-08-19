package com.github.uniliva.commonsutils.exception;

public class ValidacaoException extends BaseException {
	private static final long serialVersionUID = 1L;

	public ValidacaoException(final String mensagem, final Object detalhe) {
		super(mensagem, detalhe);
	}

	public ValidacaoException(final String message) {
		super(message);
	}

}
