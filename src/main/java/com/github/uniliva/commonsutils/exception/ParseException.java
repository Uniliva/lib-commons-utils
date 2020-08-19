package com.github.uniliva.commonsutils.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ParseException extends BaseException {
	private static final long serialVersionUID = 1L;

	public ParseException(final String mensagem, final Object detalhe) {
		super(mensagem, detalhe);
	}

	public ParseException(final String message) {
		super(message);
	}

}
