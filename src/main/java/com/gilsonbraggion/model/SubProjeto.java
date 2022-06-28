package com.gilsonbraggion.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import com.gilsonbraggion.util.Util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubProjeto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JoinColumn(name = "projetoId")
	@ManyToOne(optional = false)
	private Projeto projeto;

	private String nomeSubProjeto;

	private Date dataInicio;
	private Date dataFim;

	private String responsavel;

	private boolean finalizado;

	private boolean atrasado;

	@Column(length = 500)
	private String observacoes;

	@Transient
	private Long idProjeto;

	@Column(nullable = false, updatable = false)
	private Long idUsuario;

	@PrePersist
	public void prePersist() {
		idUsuario = Util.obterIdUsuarioLogado();
	}

}
