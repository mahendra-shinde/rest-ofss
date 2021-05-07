package com.mahendra;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class App {

	static String BASE_URL = "https://jsonplaceholder.typicode.com";
	
	public static void main(String[] args) {
		ClientBuilder builder = ClientBuilder.newBuilder();
		Client client = builder.build();
		String result = client.target(BASE_URL+"/users/1")
						.request() // Preparing a request
						.accept("application/json") //Set Accept Type
						.get(String.class); //HTTP GET
		
		System.out.println("Response : ");
		System.out.println(result);
		client.close();
	}

}
