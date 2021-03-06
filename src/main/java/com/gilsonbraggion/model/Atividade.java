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
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.gilsonbraggion.util.Util;

import lombok.Data;

@Entity
@Data
public class Atividade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 500)
	private String texto;

	@Column(name = "dataCriacao", updatable = false)
	@Temporal(TemporalType.DATE)
	private Date dataCriacao;

	@Temporal(TemporalType.DATE)
	private Date dataExecucao;

	@Temporal(TemporalType.DATE)
	private Date dataFinalizacao;

	private String envolvidos;

	@Column(length = 500)
	private String observacoes;

	private Boolean finalizado;

	@JoinColumn(name = "tipoAtividadeId")
	@ManyToOne(optional = false)
	private TipoAtividade tipoAtividade;

	@Column(nullable = false)
	private Long idUsuario;

	@Transient
	private Long idTipoAtividade;

	@Transient
	private String estiloLinha;

	@Transient
	private boolean fromHome;

	public String getEstiloLinha() {
		return Util.getEstiloLinha(this);
	}

	@PrePersist
	public void prePersist() {
		dataCriacao = new Date();
		idUsuario = Util.obterIdUsuarioLogado();
	}

	@PreUpdate
	public void preUpdate() {
		idUsuario = Util.obterIdUsuarioLogado();
	}

}
