package com.gilsonbraggion.bean;

import java.util.List;

import lombok.Data;

@Data
public class PainelBean {
	private Integer iterador;
	private List<TipoAtividadeBean> listaTipoAtividade;
}
