package com.fdmgroup.CurrencyConverter;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Users {
	private String name;
	private Map<String, Double> wallet;
	
	@JsonCreator
	public Users(@JsonProperty("name")String name, 
						@JsonProperty("wallet")Map<String, Double> wallet) {
		super();
		this.name = name;
		this.wallet = wallet;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Double> getWallet() {
		return wallet;
	}

	public void setWallet(Map<String, Double> wallet) {
		this.wallet = wallet;
	}

	@Override
	public String toString() {
		return "Users [name=" + name + ", wallet=" + wallet + "]";
	}
	
	
	
}
