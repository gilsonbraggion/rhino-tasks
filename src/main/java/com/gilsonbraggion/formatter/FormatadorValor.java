package com.gilsonbraggion.formatter;


import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.Formatter;

public class FormatadorValor implements Formatter<Double> {

	@Override
	public String print(Double valor, Locale locale) {
		NumberFormat nf = new DecimalFormat ("#,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR")));
		String valorFormatado = nf.format(valor);
		return valorFormatado;
	}

	@Override
	public Double parse(String valor, Locale locale) throws ParseException {
		if (StringUtils.isBlank(valor)) {
			return 0D;
		}
		
		NumberFormat format = NumberFormat.getInstance(new Locale ("pt", "BR"));
	    Number number = format.parse(valor);
	    double valorFormatado = number.doubleValue();
		return valorFormatado;
	}

}