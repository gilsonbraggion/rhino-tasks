package com.gilsonbraggion.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import com.gilsonbraggion.model.Atividade;

public class Util {

	public static String getDateString(Date data) {

		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

		return DATE_FORMAT.format(data);
	}
	
	public static String getEstiloLinha(Atividade atividade) {

		Date dataAtualPlus5 = DateUtils.addDays(new Date(), 5);
		
		if (atividade.getDataExecucao() == null) {
			return "atividadeFutura";
		}
		
		boolean contaVencida = atividade.getDataExecucao().compareTo(dataAtualPlus5) < 0;

		if (atividade.getFinalizado() != null && atividade.getFinalizado()) {
			return "atividadeConcluida";
		} else if (contaVencida) {
			return "atividadeVencida";
		} else {
			return "atividadeFutura";
		}

	}

}
