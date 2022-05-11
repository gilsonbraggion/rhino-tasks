package com.gilsonbraggion.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
public class OneOnOne {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.DATE)
	private Date data;

	private String horaInicio;
	private String horaFim;

	@Column(length = 400)
	private String introducao;

	@Column(length = 400)
	private String pontosPositivos;

	@Column(length = 400)
	private String melhorias;

	@Column(length = 400)
	private String feedBackReverso;

	@JoinColumn(name = "lideradoId")
	@ManyToOne(optional = false)
	private Liderado liderado;

	private boolean realizado;

	@Transient
	private Long idLiderado;

}
