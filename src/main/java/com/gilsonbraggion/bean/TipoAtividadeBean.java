package com.gilsonbraggion.bean;

import java.util.List;

import com.gilsonbraggion.model.Atividade;

import lombok.Data;

@Data
public class TipoAtividadeBean {

	private Long idTipoAtividade;
	private String nomeTipoAtividade;
	private List<Atividade> listaAtividades;
}
