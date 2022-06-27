package com.gilsonbraggion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.gilsonbraggion.util.Util;

import lombok.Data;

@Entity
@Data
public class Liderado {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;

	private String cargo;

	private String observacoes;

	@Column(nullable = false)
	private Long idUsuario;

	@PrePersist
	public void prePersist() {
		idUsuario = Util.obterIdUsuarioLogado();
	}

	@PreUpdate
	public void preUpdate() {
		idUsuario = Util.obterIdUsuarioLogado();
	}

}
