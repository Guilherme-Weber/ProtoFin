package com.dev.ProtoFin.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToDouble implements Converter<String, Double> {

	@Override
	public Double convert(String source) {

		source = source.trim();

		if (source.length() > 0) {
			return Double.parseDouble(source.replace(".", "").replace(",", "."));
		}

		return 0.;
	}

}
