package com.gilsonbraggion.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.gilsonbraggion.util.Util;

import lombok.Data;

@Entity
@Data
public class AtividadeSubProjeto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JoinColumn(name = "subProjetoId")
	@ManyToOne(optional = false)
	private SubProjeto subProjeto;

	private String nomeAtividade;

	private Date dataInicio;
	private Date dataFim;

	private double percentualDesenvolvimento;

	private double percentualTesteEssencial;

	private double percentualTesteCompleto;

	private String responsavel;

	private boolean esteiraGerada;

	@Transient
	private Long idSubProjeto;

	@Transient
	private double percentualEsperado;

	@Transient
	private double percentualReal;
	
	public double getPercentualEsperado() {
		return Util.getPercentualEsperado(this.getDataInicio(), this.getDataFim());
	}

	public double getPercentualReal() {
		return Util.getPercentualReal(this.getPercentualDesenvolvimento(), this.getPercentualTesteEssencial(), this.getPercentualTesteCompleto());
	}

}
