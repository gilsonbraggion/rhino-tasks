package com.gilsonbraggion;

import java.util.Date;

import com.gilsonbraggion.model.Atividade;

public class Util {

	public static String getEstiloLinha(Atividade atividade) {

		boolean contaVencida = atividade.getDataExecucao().compareTo(new Date()) < 0;

		if (atividade.getDataFinalizacao() != null) {
			return "atividadeConcluida";
		} else if (contaVencida) {
			return "atividadeVencida";
		} else {
			return "atividadeFutura";
		}

	}

}
