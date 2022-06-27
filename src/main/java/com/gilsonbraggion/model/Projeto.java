package com.gilsonbraggion.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.gilsonbraggion.util.Util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Projeto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nomeProjeto;

	private Date dataInicio;
	private Date dataFim;

	private String responsavel;

	private boolean finalizado;

	@Column(length = 500)
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
