package com.gilsonbraggion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Reuniao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String assunto;
	private String envolvidos;
	private boolean projeto;
	
	@Column(length = 500)
	private String observacoes;

}
