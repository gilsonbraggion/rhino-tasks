package com.gilsonbraggion.bean;

import lombok.Data;

@Data
public class ProjetoCompartilhadoBean {

	private Long idProprietario;
	private String nomeProprietario;

	private Long idConvidado;
	private String nomeConvidado;

	private Long idSubProjeto;
	private String nomeSubProjeto;

}
