package com.example.service.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dozer.DozerConverter;

public class CustomStringTimeConverter extends DozerConverter<String, Date> {

	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	
	public CustomStringTimeConverter() {
		super(String.class, Date.class);
	}

	@Override
	public Date convertTo(String source, Date destination) {
		try {
			return (Date) sdf.parse(source);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date(0L);
	}

	@Override
	public String convertFrom(Date source, String destination) {
		return sdf.format(source);
	}

}
