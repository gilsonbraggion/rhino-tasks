package com.gilsonbraggion.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Atividade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String texto;
	private String observacoes;

	private Date dataCriacao;
	private Date dataExecucao;
	private Date dataFinalizacao;

	private String envolvidos;

}
