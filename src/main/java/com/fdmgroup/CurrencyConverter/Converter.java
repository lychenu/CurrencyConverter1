package com.fdmgroup.CurrencyConverter;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Converter {
	private String fromCurrency;
	private String toCurrency;
	private double amount;
	
	
	public Converter(String fromCurrency, String toCurrency, double amount) {
		super();
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
		this.amount = amount;
	}

	public String getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	// when converting from cad to other currency
	public double getRate(String toCurrency, String rates) throws StreamReadException, DatabindException, IOException {
		
		File ratesJson = new File("resources/rates.json");
		double rateOutput = 0;
		
		Map<String, Object> jsonFileAsMap = new ObjectMapper().readValue(ratesJson, new TypeReference<Map<String, Object>>(){});
		
		@SuppressWarnings("unchecked")
		Map<String, Object> ratesMap = (Map<String, Object>) jsonFileAsMap.get(toCurrency);
		
		rateOutput = (double) ratesMap.get(rates);
		
		return rateOutput;
		
	}
	
	public double converterAmount() throws StreamReadException, DatabindException, IOException {
		double converted = 0;
		Converter converter = new Converter(fromCurrency, toCurrency, amount);
		
		if(fromCurrency.equals("cad")) {
			converted = amount * converter.getRate(toCurrency, "rate");
			
			System.out.println("rate \n"+converter.getRate("eur", "rate"));
			
		}else if(fromCurrency.equals(toCurrency)) {
			;
		}
		
		else {
			converted = amount * converter.getRate(fromCurrency, "inverseRate") * converter.getRate(toCurrency, "rate");
		}
		
//		System.out.println("test for converting from " + fromCurrency+ " to " + toCurrency + " : " + converted);
		return converted;
		
	}

}
