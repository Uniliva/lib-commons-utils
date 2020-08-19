package com.github.uniliva.commonsutils.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "dt_inclusao", updatable = false)
	protected LocalDateTime dataInclusao;

	@Column(name = "dt_alteracao")
	protected LocalDateTime dataAlteracao;

	public BaseEntity() {
		dataInclusao = LocalDateTime.now();
		dataAlteracao = LocalDateTime.now();
	}

}
