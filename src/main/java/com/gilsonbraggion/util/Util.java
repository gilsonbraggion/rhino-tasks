package com.gilsonbraggion.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	public static String getDateString(Date data) {

		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

		return DATE_FORMAT.format(data);
	}

}
