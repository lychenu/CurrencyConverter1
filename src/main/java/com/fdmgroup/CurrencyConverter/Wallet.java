package com.fdmgroup.CurrencyConverter;

public class Wallet {
	private String currency;
	private double amount;

	public Wallet() {
		super();
	}

	public Wallet(String currency, double amount) {
		super();
		this.currency = currency;
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
