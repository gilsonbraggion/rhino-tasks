package com.gilsonbraggion.util;

import java.text.DecimalFormat;
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

	public static double getPercentualEsperado(Date dataInicio, Date dataFinal) {

		if (dataInicio == null || dataFinal == null) {
			return 0d;
		}

		long now = System.currentTimeMillis();
		long s = dataInicio.getTime();
		long e = dataFinal.getTime();

		if (now >= e) {
			return 100d;
		}

		if (now <= s) {
			return 0d;
		}

		return (long) (100 - ((e - now) * 100 / (e - s)));
	}

	public static double getPercentualReal(double avancoDesenv, double avancoEssencial, double avancoCompleto) {

		double total = avancoDesenv + avancoEssencial + avancoCompleto;
		
		if (total == 0) {
			return 0d;
		}

		double avanco = total / 3;

		return avanco;

	}
	
	public static String formatarValor(Double valor) {

		DecimalFormat decFormat = new DecimalFormat("#,###,##0.00");
		if (valor == null) {
			return decFormat.format(0D);
		}

		return decFormat.format(valor);

	}

	
}
