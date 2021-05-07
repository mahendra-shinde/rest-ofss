package com.mahendra;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;


public class App {

	static String BASE_URL = "http://localhost:8080/customer";
	
	public static void main(String[] args) {
		ClientBuilder builder = ClientBuilder.newBuilder();
		Client client = builder.build();
		Customer result = client.target(BASE_URL+"/1")
						.request() // Preparing a request
						.accept("application/json") //Set Accept Type
						.get(Customer.class); //HTTP GET
		
		System.out.println("Response : ");
		
			System.out.println(result.getFirstName()+" "+result.getLastName());
		
		client.close();
	}

}
