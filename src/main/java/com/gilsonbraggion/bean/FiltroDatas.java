package com.gilsonbraggion.bean;

import lombok.Data;

@Data
public class FiltroDatas {
	
	private int mes;
	private String nomeMes;
	private int ano;
	
	public FiltroDatas(int mes, int ano) {
		
		super();
		this.mes = mes;
		this.ano = ano;
		
	}
	
}
