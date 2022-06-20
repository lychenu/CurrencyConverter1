package com.fdmgroup.CurrencyConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TransactionProcessor {
	
	private String name;
	private String fromCurrency;
	private String toCurrency;
	private double amount;
	private HashMap<String, Users> userList;
	private Users[] userInput;
	private File txtFile = new File ("resources/transactions.txt");
	private File userJson = new File("resources/users.json");
	
	public TransactionProcessor(File txtFile, Users[] userInput ) throws StreamReadException, DatabindException, IOException {
		this.userInput = usersInput(userJson);
		this.txtFile = txtFile;
		this.userList = new HashMap<String, Users>();
	}
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	// read transactions.txt into string array
	public String[] readTransactions(File txtFile) throws FileNotFoundException {
		List<String> listOfStrings  = new ArrayList<String>();
		
		Scanner sc = new Scanner(txtFile).useDelimiter(",\\s");
		String str ;
				
		while ( sc.hasNext()) {
			str = sc.nextLine();
			listOfStrings.add(str);
		}
		sc.close();
		// convert any arraylist to array
        String[] array = listOfStrings.toArray(new String[0]);
  
		return array;
	}
	
	public Users[] usersInput(File file) throws StreamReadException, DatabindException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Users[] users = null;
		users = mapper.readValue(userJson, Users[].class);
		return users;
	}
	
	public void usersMap() throws IOException {
		for(int i = 0; i < userInput.length; i++) {
			userList.put(userInput[i].getName(), userInput[i]);
		}
		System.out.println("usermap: "+name);
	}

	// update users input
	public void updateUsers(File txtFile) throws IOException {
		String[] strArray = readTransactions(txtFile);
		for(int i = 0; i < strArray.length; i++) {
			String users = strArray[i];
			String[] usersArray = users.split(" ");
			this.name=usersArray[0];
			this.fromCurrency=usersArray[1];
			this.toCurrency=usersArray[2];
			this.amount=Double.valueOf(usersArray[3]);
		}		
		usersMap();
		System.out.println("updatedUsers, userList"+userList);
	}
	
	// executeConversion from transactions.txt
	public double executeConversion(String[] name) throws StreamReadException, DatabindException, IOException {
			updateUsers(txtFile);
			double convertAmount = 0;
			Converter converter1 = new Converter(fromCurrency, toCurrency, amount);
			convertAmount = converter1.converterAmount();
		
		return convertAmount;
	}
	
	
	

	public List<Users> getUpadtedUsers() throws IOException {
		usersMap();
		
		List<Users> updatedList = new ArrayList<>();
//		System.out.print(userList.get(name).getWallet());
		System.out.println("test for userList1111"+userList.get(name).getName());
		return updatedList;
	}


	

	
	
	
	
}

