package com.github.uniliva.commonsutils.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Erro extends BaseDTO {
	private static final long serialVersionUID = -6487111911459784100L;

	private String mensagem;
	private transient Object detalhe;

}
