package com.gilsonbraggion.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "atividade")
@Data
public class Atividade {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

	private String texto;
	private String observacoes;

	private Date dataCriacao;
	private Date dataExecucao;
	private Date dataFinalizacao;

	private String envolvidos;

}
