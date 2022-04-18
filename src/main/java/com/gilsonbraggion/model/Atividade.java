package com.gilsonbraggion.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
public class Atividade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String texto;

	@Temporal(TemporalType.DATE)
	private Date dataCriacao;
	
	@Temporal(TemporalType.DATE)
	private Date dataExecucao;
	
	@Temporal(TemporalType.DATE)
	private Date dataFinalizacao;
	
	private String envolvidos;

	private String observacoes;
	
	@ManyToOne
	private TipoAtividade tipoAtividade;
	
	@Transient
	private Long idTipoAtividade;

}
