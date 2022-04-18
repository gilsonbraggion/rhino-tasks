package com.gilsonbraggion.formatter;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

public class FormatadorData implements Formatter<Date> {

	private static final String DATA_TELA = "dd/MM/yyyy";

	@Override
	public String print(Date data, Locale locale) {
    	if (data == null) {
    		return "";
    	}
    	String dataFormatada = new SimpleDateFormat(DATA_TELA).format(data);
		return dataFormatada;
	}

	@Override
	public Date parse(String data, Locale locale) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat(DATA_TELA);
		Date date = formatter.parse(data);

		return date;
	}

}