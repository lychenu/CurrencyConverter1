package com.fdmgroup.CurrencyConverter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
	
	public static Users[] readFromUsersJSONFile (File file) throws StreamReadException, DatabindException, IOException {
		System.out.println("---reading from users.json file---\n");
		ObjectMapper mapper = new ObjectMapper();
		Users[] users = null;
		users = mapper.readValue(file, Users[].class);
		return users;
	}
	
	public static void updateUsersJSONFile(File file, List<Users> users) throws StreamWriteException, DatabindException, IOException {
		System.out.println("\n---Updating users to new json file---\n");
		ObjectMapper mapper = new ObjectMapper();
		Users theUsers = null;
		for(Users instance : users) {
			theUsers = instance;
			System.out.println("write: "+ theUsers.getName() + theUsers.getWallet());
		}
		mapper.writeValue(file, users);
	}
	
	public static void main(String[]args) throws StreamReadException, DatabindException, IOException {
		// read from users.json file
		File usersJson = new File("resources/users.json");
		Users[] users = readFromUsersJSONFile(usersJson);
		Users theUsers = null;
//		System.out.println(theUsers.getName());
		for(Users instance : users) {
			theUsers = instance;
			System.out.println(theUsers.getName() + theUsers.getWallet() +  "\n");
		}
				
		
		// transactions read
		File textFile = new File("resources/transactions.txt");
		TransactionProcessor tp = new TransactionProcessor(textFile, users);
//		tp.updateUsers(textFile);
//
//		tp.executeTransactions();
//		
		String[] str = new String[] {"Bob", "cad" ,"usd", "100"};
		tp.executeConversion(str);

		
		// update to updatedUsers.json file
		File updatedUsers = new File("resources/updatedUsers.json");
		updateUsersJSONFile(updatedUsers, tp.getUpadtedUsers());
	}

}
