package com.gilsonbraggion.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gilsonbraggion.formatter.FormatadorData;
import com.gilsonbraggion.formatter.FormatadorValor;

@Configuration
public class FormatterConfig implements WebMvcConfigurer {
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		FormatadorValor formatadorValor = new FormatadorValor();
		FormatadorData formatadorData = new FormatadorData();

		registry.addFormatter(formatadorData);
		registry.addFormatter(formatadorValor);
	}
	
}